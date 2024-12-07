using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Barangay_Web_App2
{
    public partial class Appointments : System.Web.UI.Page
    {
        private static readonly HttpClient HttpClient = new HttpClient();

        protected async void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await LoadAppointmentsAsync();
            }
        }

        private async Task LoadAppointmentsAsync(string searchQuery = "")
        {
            string apiUrl = "https://barangayapp.x10.mx/api/routes/fetch_appointment.php";

            try
            {
                string url = string.IsNullOrWhiteSpace(searchQuery)
                             ? apiUrl
                             : $"{apiUrl}?search={Uri.EscapeDataString(searchQuery)}";

                HttpClient.DefaultRequestHeaders.Accept.Add(
                    new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));

                HttpResponseMessage response = await HttpClient.GetAsync(url);

                if (response.IsSuccessStatusCode)
                {
                    string jsonResponse = await response.Content.ReadAsStringAsync();
                    var apiResponse = JsonConvert.DeserializeObject<ApiResponse>(jsonResponse);

                    if (apiResponse?.Appointments != null && apiResponse.Appointments.Count > 0)
                    {
                        AppointmentsRepeater.DataSource = apiResponse.Appointments;
                        AppointmentsRepeater.DataBind();
                        NoAppointmentsMessage.Visible = false;
                    }
                    else
                    {
                        ShowNoAppointmentsMessage();
                    }
                }
                else
                {
                    ShowError("Unable to fetch appointments. Please try again later.");
                }
            }
            catch (Exception ex)
            {
                ShowError($"An error occurred: {ex.Message}");
            }
        }

        private void BindAppointments(List<Appointment> appointments)
        {
            AppointmentsRepeater.DataSource = appointments;
            AppointmentsRepeater.DataBind();
            NoAppointmentsMessage.Visible = false;
        }

        private void ShowNoAppointmentsMessage()
        {
            AppointmentsRepeater.DataSource = null;
            AppointmentsRepeater.DataBind();
            NoAppointmentsMessage.Visible = true;
        }

        private void ShowError(string message)
        {
            lblStatus.Text = message;
            ShowNoAppointmentsMessage();
        }

        protected async void UpdateAppointmentStatus(object sender, EventArgs e)
        {
            if (sender is Button button && int.TryParse(button.CommandArgument, out int id))
            {
                string status = button.CommandName;
                if (status != "accepted" && status != "rejected")
                {
                    lblStatus.Text = "Invalid action.";
                    return;
                }

                await UpdateStatusAsync(id, status);
            }
            else
            {
                lblStatus.Text = "Invalid request.";
            }
        }

        private async Task UpdateStatusAsync(int id, string status)
        {
            string apiUrl = "https://barangayapp.x10.mx/api/routes/update_appointment.php"; // API URL for updating appointment status

            try
            {
                var data = new FormUrlEncodedContent(new[] {
                    new KeyValuePair<string, string>("id", id.ToString()),
                    new KeyValuePair<string, string>("status", status)
                });

                HttpResponseMessage response = await HttpClient.PostAsync(apiUrl, data);

                if (response.IsSuccessStatusCode)
                {
                    lblStatus.Text = $"Appointment ID {id} has been marked as '{status}'.";
                    lblStatus.ForeColor = System.Drawing.Color.Green;
                }
                else
                {
                    string serverError = await response.Content.ReadAsStringAsync();
                    ShowError($"Server error: {serverError}");
                }

                await LoadAppointmentsAsync(); // Reload appointments after status update
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error updating appointment: {ex.Message}";
            }
        }

        protected async void BtnSearch_Click(object sender, EventArgs e)
        {
            string searchQuery = txtSearch.Text.Trim();
            await LoadAppointmentsAsync(searchQuery); // Pass the search query to filter appointments
        }

        public class ApiResponse
        {
            public bool Success { get; set; }
            public List<Appointment> Appointments { get; set; }
        }

        public class Appointment
        {
            public int Id { get; set; }  // Maps to idbrgy_appointment
            public string Reason { get; set; }  // Maps to appointment_reason
            public string Date { get; set; }    // Maps to appointment_date
            public string OfficialName { get; set; }  // Maps to official_name
            public string OfficialPosition { get; set; }  // Maps to official_position
            public string Status { get; set; }  // Maps to status
        }
    }
}
