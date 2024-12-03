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
        private static readonly HttpClient HttpClient = new HttpClient();

        protected async void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await FetchRequests();
            }
        }

        private async Task FetchRequests(string searchQuery = "")
        {
            string apiUrl = "https://barangayapp.x10.mx/api/routes/fetch_requests_web.php"; // API URL for fetching requests

            try
            {
                // Construct URL with search query
                string url = string.IsNullOrWhiteSpace(searchQuery)
                             ? apiUrl
                             : $"{apiUrl}?search={Uri.EscapeDataString(searchQuery)}";

                HttpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage response = await HttpClient.GetAsync(url);

                if (response.IsSuccessStatusCode)
                {
                    string jsonResponse = await response.Content.ReadAsStringAsync();
                    var requests = JsonConvert.DeserializeObject<List<Request>>(jsonResponse);

                    if (requests != null && requests.Count > 0)
                    {
                        RequestsRepeater.DataSource = requests;
                        RequestsRepeater.DataBind();
                        RequestsContainer.Visible = true;
                        NoRequestsMessage.Visible = false;
                    }
                    else
                    {
                        RequestsContainer.Visible = false;
                        NoRequestsMessage.Visible = true;
                    }
                }
                else
                {
                    ShowErrorMessage($"Error fetching requests: {response.ReasonPhrase}");
                }
            }
            catch (Exception ex)
            {
                ShowErrorMessage($"Exception occurred: {ex.Message}");
            }
        }

        protected async void ApproveRequestAsync(object sender, EventArgs e)
        {
            if (sender is Button button && int.TryParse(button.CommandArgument, out int requestId))
            {
                await UpdateRequestStatusAsync(requestId, "Approved", "Request approved by admin.");
            }
        }

        protected async void RejectRequestAsync(object sender, EventArgs e)
        {
            if (sender is Button button && int.TryParse(button.CommandArgument, out int requestId))
            {
                await UpdateRequestStatusAsync(requestId, "Rejected", "Request rejected by admin.");
            }
        }

        private async Task UpdateRequestStatusAsync(int requestId, string status, string adminNotes)
        {
            string apiUrl = "https://barangayapp.x10.mx/api/routes/update_request_status.php"; // API URL for updating request status

            try
            {
                var payload = new { request_id = requestId, status, admin_notes = adminNotes };
                var content = new StringContent(JsonConvert.SerializeObject(payload), System.Text.Encoding.UTF8, "application/json");
                HttpResponseMessage response = await HttpClient.PostAsync(apiUrl, content);

                if (response.IsSuccessStatusCode)
                {
                    lblStatus.Text = $"Request {status.ToLower()} successfully.";
                    lblStatus.ForeColor = System.Drawing.Color.Green;
                }
                else
                {
                    string serverError = await response.Content.ReadAsStringAsync();
                    ShowErrorMessage($"Server error: {serverError}");
                }

                await FetchRequests();
            }
            catch (Exception ex)
            {
                ShowErrorMessage($"Exception occurred: {ex.Message}");
            }
        }

        protected async void BtnSearch_Click(object sender, EventArgs e)
        {
            string searchQuery = txtSearch.Text.Trim();
            await FetchRequests(searchQuery);
        }

        private void ShowErrorMessage(string message)
        {
            lblStatus.Text = message;
            lblStatus.ForeColor = System.Drawing.Color.Red;
        }
    }

    public class Request
    {
        public int RequestId { get; set; }
        public string RequestDetails { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string DocumentName { get; set; }
        public string Status { get; set; }
    }
}
