using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Web.UI.WebControls;

namespace Barangay_Web_App2
{
    public partial class Home : System.Web.UI.Page
    {
        protected async void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await FetchAndDisplayEvents();
            }
        }

        private async Task FetchAndDisplayEvents()
        {
            string apiUrl = "https://barangayapp.x10.mx/api/routes/fetchEvents_web.php"; // PHP API URL for fetching events

            try
            {
                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.GetAsync(apiUrl);
                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();
                        List<Event> events = JsonConvert.DeserializeObject<List<Event>>(jsonResponse);

                        EventsRepeater.DataSource = events;
                        EventsRepeater.DataBind();
                    }
                    else
                    {
                        lblStatus.Text = "Failed to fetch events.";
                        lblStatus.ForeColor = System.Drawing.Color.Red;
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error fetching events: {ex.Message}";
                lblStatus.ForeColor = System.Drawing.Color.Red;
            }
        }

        protected async void SubmitEvent_Click(object sender, EventArgs e)
        {
            string title = txtEventTitle.Text.Trim();
            string description = txtEventDescription.Text.Trim();
            string date = txtEventDate.Text.Trim();
            string time = txtEventTime.Text.Trim();
            string location = txtEventLocation.Text.Trim();

            string apiUrl = "https://barangayapp.x10.mx/api/routes/insertEvent_web.php"; // PHP API URL for inserting event

            try
            {
                var data = new FormUrlEncodedContent(new[]
                {
                    new KeyValuePair<string, string>("eventTitle", title),
                    new KeyValuePair<string, string>("eventDescription", description),
                    new KeyValuePair<string, string>("eventDate", date),
                    new KeyValuePair<string, string>("eventTime", time),
                    new KeyValuePair<string, string>("eventLocation", location)
                });

                using (HttpClient client = new HttpClient { Timeout = TimeSpan.FromSeconds(30) })
                {
                    HttpResponseMessage response = await client.PostAsync(apiUrl, data);
                    string apiResponse = await response.Content.ReadAsStringAsync();

                    var jsonResponse = JsonConvert.DeserializeObject<Dictionary<string, string>>(apiResponse);
                    if (jsonResponse.ContainsKey("message"))
                    {
                        lblStatus.Text = jsonResponse["message"];
                        lblStatus.ForeColor = System.Drawing.Color.Green;

                        // Clear input fields after successful submission
                        txtEventTitle.Text = "";
                        txtEventDescription.Text = "";
                        txtEventDate.Text = "";
                        txtEventTime.Text = "";
                        txtEventLocation.Text = "";
                    }

                    // Refresh event list after successful addition
                    await FetchAndDisplayEvents();
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error adding event: {ex.Message}";
                lblStatus.ForeColor = System.Drawing.Color.Red;
            }
        }
    }

    public class Event
    {
        public int Event_id { get; set; }
        public string Event_title { get; set; }
        public string Event_description { get; set; }
        public string Event_date { get; set; }
        public string Event_time { get; set; }
        public string Event_location { get; set; }
        public DateTime Created_time { get; set; }
    }
}
