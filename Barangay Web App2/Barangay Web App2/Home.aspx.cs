using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Web.UI.HtmlControls;

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
            string apiUrl = "https://barangayapp.x10.mx/api/routes/fetchEvents_web.php";
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.GetAsync(apiUrl);
                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();

                        // Deserialize into a wrapper object
                        var responseObject = JsonConvert.DeserializeObject<FetchEventsResponse>(jsonResponse);

                        if (responseObject != null && responseObject.Status == "success" && responseObject.Data.Count > 0)
                        {
                            EventsRepeater.DataSource = responseObject.Data;
                            EventsRepeater.DataBind();
                            noEventsMessage.Visible = false; // Hide "No events" message
                        }
                        else
                        {
                            EventsRepeater.DataSource = null;
                            EventsRepeater.DataBind();
                            noEventsMessage.Visible = true; // Show "No events" message
                        }
                    }
                    else
                    {
                        lblStatus.Text = "Failed to fetch events. Please try again later.";
                        noEventsMessage.Visible = true; // Show "No events" message
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error fetching events: {ex.Message}";
                noEventsMessage.Visible = true; // Show "No events" message
            }
        }

        // Wrapper class for deserializing the JSON response
        public class FetchEventsResponse
        {
            public string Status { get; set; }
            public List<Event> Data { get; set; }
        }

        // Event class for individual event objects
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



        protected async void BtnAddEvent_Click(object sender, EventArgs e)
        {
            string title = txtEventTitle.Text;
            string description = txtEventDescription.Text;
            string date = txtEventDate.Value; // Correct usage for HtmlInputGenericControl
            string time = txtEventTime.Value; // Correct usage for HtmlInputGenericControl
            string location = txtEventLocation.Text;

            string apiUrl = "https://barangayapp.x10.mx/api/routes/insertEvent_web.php";

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

                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.PostAsync(apiUrl, data);
                    if (response.IsSuccessStatusCode)
                    {
                        string apiResponse = await response.Content.ReadAsStringAsync();
                        var jsonResponse = JsonConvert.DeserializeObject<Dictionary<string, string>>(apiResponse);

                        lblStatus.Text = jsonResponse.ContainsKey("message")
                            ? jsonResponse["message"]
                            : "Event added successfully.";

                        // Clear input fields
                        txtEventTitle.Text = "";
                        txtEventDescription.Text = "";
                        txtEventDate.Value = ""; // Clear HtmlInputGenericControl
                        txtEventTime.Value = ""; // Clear HtmlInputGenericControl
                        txtEventLocation.Text = "";

                        await FetchAndDisplayEvents();
                    }
                    else
                    {
                        lblStatus.Text = "Failed to add the event. Please try again.";
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error adding event: {ex.Message}";
            }
        }
    }
}
