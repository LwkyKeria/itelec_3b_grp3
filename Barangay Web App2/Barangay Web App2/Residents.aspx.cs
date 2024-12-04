using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace Barangay_Web_App2
{
    public partial class Residents : System.Web.UI.Page
    {
        protected async void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await FetchAndDisplayResidents(); // Initial load
            }
        }

        // Fetch residents data from API
        private async Task FetchAndDisplayResidents(string searchQuery = "")
        {
            string apiUrl = System.Configuration.ConfigurationManager.AppSettings["ResidentsApiUrl"];

            try
            {
                using (HttpClient client = new HttpClient())
                {
                    // Fetch data from API
                    HttpResponseMessage response = await client.GetAsync(apiUrl);
                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();
                        dynamic result = JsonConvert.DeserializeObject(jsonResponse);

                        if (result.success == true)
                        {
                            List<Resident> residents = JsonConvert.DeserializeObject<List<Resident>>(result.data.ToString());

                            // Apply search filtering (if applicable)
                            if (!string.IsNullOrEmpty(searchQuery))
                            {
                                residents = residents.FindAll(r => r.FullName.ToLower().Contains(searchQuery.ToLower()));
                            }

                            // Bind data to the repeater
                            BindResidentsToRepeater(residents);
                        }
                        else
                        {
                            ShowEmptyDataMessage();
                        }
                    }
                    else
                    {
                        lblStatus.Text = "Failed to fetch residents.";
                        ShowEmptyDataMessage();
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error fetching residents: {ex.Message}";
                ShowEmptyDataMessage();
            }
        }

        // Bind residents to the repeater
        private void BindResidentsToRepeater(List<Resident> residents)
        {
            if (residents != null && residents.Count > 0)
            {
                ResidentsRepeater.DataSource = residents;
                ResidentsRepeater.DataBind();
                EmptyDataPanel.Visible = false; // Hide empty message
            }
            else
            {
                ShowEmptyDataMessage();
            }
        }

        // Show empty data message
        private void ShowEmptyDataMessage()
        {
            ResidentsRepeater.DataSource = null;
            ResidentsRepeater.DataBind();
            EmptyDataPanel.Visible = true; // Show empty message
        }

        // Search button click handler
        protected async void btnSearch_Click(object sender, EventArgs e)
        {
            string searchQuery = txtSearch.Text.Trim();
            await FetchAndDisplayResidents(searchQuery); // Fetch data with filter
        }

        // Resident class to hold resident data 
        public class Resident
        {
            public int ResidentID { get; set; }
            public string FullName { get; set; }
            public string Phone { get; set; }
            public string Location { get; set; }
        }
    }
}
