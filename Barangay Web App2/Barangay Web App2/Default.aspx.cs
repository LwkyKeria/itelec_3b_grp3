using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.UI;
using Newtonsoft.Json;

namespace Barangay_Web_App2
{
    public partial class index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected async void Submit_Click(object sender, EventArgs e)
        {
            // Call the API for login
            bool isValidUser = await ValidateUserAsync(txtName.Text, txtPassword.Text);

            if (isValidUser)
            {
                // Redirect to admin dashboard
                Response.Redirect("Home.aspx");
            }
            else
            {
                // Show an error message
                lblResult.Text = "Invalid username or password.";
            }
        }

        private async Task<bool> ValidateUserAsync(string username, string password)
        {
            using (HttpClient client = new HttpClient())
            {
                // Replace with your actual API URL
                string apiUrl = "https://barangayapp.x10.mx/public_html/api/routes/web_api.php" + username + "&password=" + password;
                var response = await client.GetStringAsync(apiUrl);

                // Deserialize JSON response
                var result = JsonConvert.DeserializeObject<ApiResponse>(response);

                // Assuming the API returns true or false for login success
                return result.IsValidUser; // Adjust according to your API response structure
            }
        }

        public class ApiResponse
        {
            public bool IsValidUser { get; set; }
        }
    }
}
