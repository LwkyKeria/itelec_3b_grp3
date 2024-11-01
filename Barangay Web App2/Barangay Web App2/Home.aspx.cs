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
                feedbackLabel.Text = "Please fill in all fields.";
                return;
            }

            // API URL 
            string apiUrl = "https://barangayapp.x10.mx/api/routes/create_event_web.php";

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
                        feedbackLabel.Text = "Event posted successfully!";
                    }
                    else
                    {
                        feedbackLabel.Text = $"Error posting event: {apiResponse}";
                    }
                }
                catch (Exception ex)
                {
                    feedbackLabel.Text = $"Error: {ex.Message}";
                }
            }
        }
    }
}
