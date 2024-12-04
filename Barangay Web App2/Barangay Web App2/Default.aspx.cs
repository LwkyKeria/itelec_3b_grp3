using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace Barangay_Web_App2
{
    public partial class index : System.Web.UI.Page
    {
        protected async void Submit_Click(object sender, EventArgs e)
        {
            string username = txtName.Text;
            string password = txtPassword.Text;

            // Call the async method to validate the user
            bool isValidUser = await ValidateUserAsync(username, password);

            if (isValidUser)
            {
                // If the login is successful, redirect to Home.aspx
                Response.Redirect("Home.aspx");
            }
            else
            {
                // If login fails, show an error message
                lblResult.Text = "Invalid username or password.";
            }
        }

        private async Task<bool> ValidateUserAsync(string username, string password)
        {
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    string apiUrl = "https://barangayapp.x10.mx/api/routes/web_api.php";

                    // Prepare the form data for the API
                    var data = new FormUrlEncodedContent(new[]
                    {
                        new KeyValuePair<string, string>("username", username),
                        new KeyValuePair<string, string>("password", password)
                    });

                    // Call the API
                    HttpResponseMessage response = await client.PostAsync(apiUrl, data);
                    string responseBody = await response.Content.ReadAsStringAsync();

                    // Parse the API response
                    var result = JsonConvert.DeserializeObject<ApiResponse>(responseBody);

                    // Return true if the user is valid, otherwise false
                    return result?.IsValidUser ?? false;
                }
            }
            catch (Exception ex)
            {
                // Log the error for debugging
                Console.WriteLine("Error: " + ex.Message);
                lblResult.Text = "Error occurred during login.";
                return false;
            }
        }
    }

    // API response class
    public class ApiResponse
    {
        public bool IsValidUser { get; set; }
        public string Error { get; set; }
    }
}
