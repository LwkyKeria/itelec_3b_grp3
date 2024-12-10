using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.Text;
using Newtonsoft.Json;

namespace Barangay_Web_App2
{
    public partial class index : System.Web.UI.Page
    {
        protected async void Submit_Click(object sender, EventArgs e)
        {
            string username = txtName.Text;
            string password = txtPassword.Text;

            // Validate input
            if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(password))
            {
                lblResult.Text = "Username and password are required.";
                return;
            }

            bool isValidUser = await ValidateUserAsync(username, password);
            if (isValidUser)
            {
                Response.Redirect("Home.aspx");
            }
            else
            {
                lblResult.Text = "Invalid username or password.";
            }
        }

        private string HashPassword(string password, string username)
        {
            using (SHA512 sha512 = SHA512.Create())
            {
                // Concatenate password and username (matches the MySQL query)
                string combined = password + username;
                byte[] bytes = Encoding.UTF8.GetBytes(combined);
                byte[] hash = sha512.ComputeHash(bytes);

                // Convert to hex string to match MySQL UNHEX(SHA2()) format
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < hash.Length; i++)
                {
                    builder.Append(hash[i].ToString("x2"));
                }
                return builder.ToString();
            }
        }

        private async Task<bool> ValidateUserAsync(string username, string password)
        {
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    string apiUrl = "https://barangayapp.x10.mx/api/routes/web_api.php";

                    // Hash the password before sending
                    string hashedPassword = HashPassword(password, username);

                    var data = new FormUrlEncodedContent(new[]
                    {
                        new KeyValuePair<string, string>("username", username),
                        new KeyValuePair<string, string>("password", hashedPassword)
                    });

                    HttpResponseMessage response = await client.PostAsync(apiUrl, data);
                    string responseBody = await response.Content.ReadAsStringAsync();
                    var result = JsonConvert.DeserializeObject<ApiResponse>(responseBody);

                    return result?.IsValidUser ?? false;
                }
            }
            catch (Exception ex)
            {
                // Log the error securely - avoid exposing error details to users
                System.Diagnostics.Debug.WriteLine($"Login error: {ex.Message}");
                lblResult.Text = "An error occurred during login. Please try again later.";
                return false;
            }
        }
    }

    public class ApiResponse
    {
        public bool IsValidUser { get; set; }
        public string Error { get; set; }
    }
}