using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.UI;
using System.Web.UI.WebControls;
using Newtonsoft.Json;

namespace Barangay_Web_App2
{
    public partial class Requests : Page
    {
        private readonly string apiUrl = "https://barangayapp.x10.mx/api/routes/manage_requests.php";

        protected async Task Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await FetchRequests();
            }
        }


        private async Task FetchRequests()
        {
            ShowLoading(true);
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.GetAsync(apiUrl);

                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();
                        dynamic result = JsonConvert.DeserializeObject(jsonResponse);

                        if (result != null && result.data != null)
                        {
                            RequestsRepeater.DataSource = result.data;
                            RequestsRepeater.DataBind();
                            lblStatus.Text = "";
                        }
                        else
                        {
                            DisplayError("No data found or an error occurred.");
                        }
                    }
                    else
                    {
                        DisplayError("Failed to fetch requests from the server.");
                    }
                }
            }
            catch (Exception ex)
            {
                DisplayError($"An error occurred: {ex.Message}");
            }
            finally
            {
                ShowLoading(false);
            }
        }

        protected async void ApproveRequest(object sender, EventArgs e)
        {
            Button button = (Button)sender;
            int requestId = Convert.ToInt32(button.CommandArgument);

            await UpdateRequestStatus(requestId, "Approved", "Request approved by admin.");
        }

        protected async void RejectRequest(object sender, EventArgs e)
        {
            Button button = (Button)sender;
            int requestId = Convert.ToInt32(button.CommandArgument);

            await UpdateRequestStatus(requestId, "Rejected", "Request rejected by admin.");
        }

        private async Task UpdateRequestStatus(int requestId, string status, string adminNotes)
        {
            ShowLoading(true);
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    var payload = new
                    {
                        request_id = requestId,
                        status,
                        admin_notes = adminNotes
                    };

                    StringContent content = new StringContent(JsonConvert.SerializeObject(payload), System.Text.Encoding.UTF8, "application/json");
                    HttpResponseMessage response = await client.PostAsync(apiUrl, content);

                    if (response.IsSuccessStatusCode)
                    {
                        lblStatus.Text = $"Request {status.ToLower()} successfully!";
                        await FetchRequests();
                    }
                    else
                    {
                        DisplayError("Failed to update the request status.");
                    }
                }
            }
            catch (Exception ex)
            {
                DisplayError($"Error: {ex.Message}");
            }
            finally
            {
                ShowLoading(false);
            }
        }

        private void ShowLoading(bool isLoading)
        {
            loadingMessage.Visible = isLoading;
            RequestsRepeater.Visible = !isLoading;
        }

        private void DisplayError(string message)
        {
            lblStatus.Text = message;
            lblStatus.CssClass = "status-error";
        }
    }
}
