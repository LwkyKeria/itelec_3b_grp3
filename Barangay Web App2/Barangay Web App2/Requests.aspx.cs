using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Barangay_Web_App2
{
    public partial class Requests : Page
    {
        // API URLs for fetching and updating requests
        private readonly string fetchRequestsUrl = "https://barangayapp.x10.mx/api/routes/fetch_requests_web.php";
        private readonly string updateRequestStatusUrl = "https://barangayapp.x10.mx/api/routes/update_request_status.php";
        private static readonly HttpClient HttpClient = new HttpClient(); // Reusable HttpClient instance

        protected async void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await FetchRequests();
            }
        }

        /// <summary>
        /// Fetches requests from the API and binds them to the repeater control.
        /// </summary>
        private async Task FetchRequests(string searchQuery = "")
        {
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.GetAsync(fetchRequestsUrl);
                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();
                        List<Request> requests = JsonConvert.DeserializeObject<List<Request>>(jsonResponse);

                        // Filter results based on the search query if provided
                        if (!string.IsNullOrEmpty(searchQuery))
                        {
                            requests = requests.FindAll(r => r.RequestDetails.ToLower().Contains(searchQuery.ToLower()));
                        }

                        // Handle visibility of UI elements based on whether data exists
                        if (requests.Count > 0)
                        {
                            RequestsRepeater.DataSource = requests;
                            RequestsRepeater.DataBind();
                            RequestsContainer.Visible = true; // Show table
                            NoRequestsMessage.Visible = false; // Hide empty message
                        }
                        else
                        {
                            RequestsContainer.Visible = false; // Hide table
                            NoRequestsMessage.Visible = true; // Show empty message
                        }
                    }
                    else
                    {
                        lblStatus.Text = "Failed to fetch requests."; // Error if fetching fails
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error fetching requests: {ex.Message}";
            }
        }



        /// Handles the approve request button click event.

        protected async void ApproveRequestAsync(object sender, EventArgs e)
        {
            if (sender is Button button && int.TryParse(button.CommandArgument, out int requestId))
            {
                await UpdateRequestStatusAsync(requestId, "Approved", "Request approved by admin.");
            }
        }


        /// Handles the reject request button click event.

        protected async void RejectRequestAsync(object sender, EventArgs e)
        {
            if (sender is Button button && int.TryParse(button.CommandArgument, out int requestId))
            {
                await UpdateRequestStatusAsync(requestId, "Rejected", "Request rejected by admin.");
            }
        }

        
        /// Updates the status of a request using the API.

        private async Task UpdateRequestStatusAsync(int requestId, string status, string adminNotes)
        {
            try
            {
                var payload = new
                {
                    request_id = requestId,
                    status,
                    admin_notes = adminNotes
                };

                var content = new StringContent(JsonConvert.SerializeObject(payload), System.Text.Encoding.UTF8, "application/json");
                HttpResponseMessage response = await HttpClient.PostAsync(updateRequestStatusUrl, content);

                if (response.IsSuccessStatusCode)
                {
                    string apiResponse = await response.Content.ReadAsStringAsync();
                    var jsonResponse = JsonConvert.DeserializeObject<Dictionary<string, string>>(apiResponse);

                    lblStatus.Text = jsonResponse?.ContainsKey("message") == true
                        ? jsonResponse["message"]
                        : "Request status updated successfully.";
                }
                else
                {
                    ShowErrorMessage($"Failed to update request status. Status Code: {response.StatusCode} - {response.ReasonPhrase}");
                }

                // Refresh the list after updating status
                await FetchRequests();
            }
            catch (Exception ex)
            {
                ShowErrorMessage($"Error updating request status: {ex.Message}");
            }
        }

        /// 
        /// Handles the search button click event.
        /// 
        protected async void BtnSearch_Click(object sender, EventArgs e)
        {
            string searchQuery = txtSearch.Text.Trim();
            await FetchRequests(searchQuery);
        }


        /// Displays an error message on the UI.

        private void ShowErrorMessage(string message)
        {
            lblStatus.Text = message;
            lblStatus.ForeColor = System.Drawing.Color.Red;
        }
    }


    /// Model to represent a request.

    public class Request
    {
        public int RequestId { get; set; } // request_id from the database
        public string RequestDetails { get; set; } // Details of the request
        public string Status { get; set; } // Status of the request
        public string AdminNotes { get; set; } // Admin notes, if any
    }
}
