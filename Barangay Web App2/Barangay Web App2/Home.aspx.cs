using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace Barangay_Web_App2
{
    public partial class Home : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // Page load logic
        }

        protected async void SubmitEvent_Click(object sender, EventArgs e)
        {
            string title = eventTitle.Text.Trim();
            string description = eventDescription.Text.Trim();
            string date = eventDate.Text.Trim();
            string time = eventTime.Text.Trim();
            string location = eventLocation.Text.Trim();

            // Validate form fields
            if (string.IsNullOrEmpty(title) || string.IsNullOrEmpty(description) ||
                string.IsNullOrEmpty(date) || string.IsNullOrEmpty(time) || string.IsNullOrEmpty(location))
            {
                Response.Write("<script>alert('Please fill in all fields.');</script>");
                return;
            }

            // API URL (point to your actual PHP API)
            string apiUrl = "https://barangayapp.x10.mx/api/routes/create_event.php";

            // Send HTTP request to the PHP API
            using (HttpClient client = new HttpClient())
            {
                var formData = new MultipartFormDataContent
                {
                    { new StringContent(title), "eventTitle" },
                    { new StringContent(description), "eventDescription" },
                    { new StringContent(date), "eventDate" },
                    { new StringContent(time), "eventTime" },
                    { new StringContent(location), "eventLocation" }
                };

                try
                {
                    HttpResponseMessage response = await client.PostAsync(apiUrl, formData);
                    string apiResponse = await response.Content.ReadAsStringAsync();

                    if (response.IsSuccessStatusCode)
                    {
                        Response.Write("<script>alert('Event posted successfully!');</script>");
                    }
                    else
                    {
                        Response.Write($"<script>alert('Error posting event: {apiResponse}');</script>");
                    }
                }
                catch (Exception ex)
                {
                    Response.Write($"<script>alert('Error: {ex.Message}');</script>");
                }
            }
        }
    }
}
