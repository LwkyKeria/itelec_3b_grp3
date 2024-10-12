using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;
using System.Web.UI.WebControls;

namespace Barangay_Web_App2
{
    public partial class About : System.Web.UI.Page
    {
        protected async void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                await FetchAndDisplayOfficials();
            }
        }

        private async Task FetchAndDisplayOfficials(string searchQuery = "")
        {
            string apiUrl = "https://barangayapp.x10.mx/api/config/fetchOfficials.php"; // PHP API URL for fetching officials

            try
            {
                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.GetAsync(apiUrl);
                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();
                        List<Official> officials = JsonConvert.DeserializeObject<List<Official>>(jsonResponse);

                        // Filter results based on search query
                        if (!string.IsNullOrEmpty(searchQuery))
                        {
                            officials = officials.FindAll(o => o.Official_name.ToLower().Contains(searchQuery.ToLower()));
                        }

                        // Bind data to the repeater control
                        OfficialsRepeater.DataSource = officials;
                        OfficialsRepeater.DataBind();
                    }
                    else
                    {
                        lblStatus.Text = "Failed to fetch officials."; // Show fetch error
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error fetching officials: {ex.Message}";
            }
        }

        protected async void BtnAddOfficial_Click(object sender, EventArgs e)
        {
            string name = txtOfficialName.Text;
            string position = txtOfficialPosition.Text;
            string contact = txtContactInfo.Text;

            string apiUrl = "https://barangayapp.x10.mx/api/routes/insertOfficial.php"; // PHP API URL for inserting official

            try
            {
                var data = new FormUrlEncodedContent(new[]
                {
            new KeyValuePair<string, string>("official_name", name),
            new KeyValuePair<string, string>("official_position", position),
            new KeyValuePair<string, string>("contact_info", contact)
        });

                using (HttpClient client = new HttpClient())
                {
                    HttpResponseMessage response = await client.PostAsync(apiUrl, data);
                    if (response.IsSuccessStatusCode)
                    {
                        string apiResponse = await response.Content.ReadAsStringAsync();

                        // Deserialize the JSON response to extract the message
                        var jsonResponse = JsonConvert.DeserializeObject<Dictionary<string, string>>(apiResponse);

                        if (jsonResponse.ContainsKey("message"))
                        {
                            lblStatus.Text = jsonResponse["message"];  // Display only the message
                        }
                        else
                        {
                            lblStatus.Text = "Unexpected response format.";  // Fallback in case of missing message
                        }

                        // Clear the input fields after successful addition
                        txtOfficialName.Text = string.Empty;
                        txtOfficialPosition.Text = string.Empty;
                        txtContactInfo.Text = string.Empty;

                        await FetchAndDisplayOfficials();  // Refresh officials list after adding
                    }
                    else
                    {
                        lblStatus.Text = "Failed to add official."; // Show error if the request fails
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error adding official: {ex.Message}";  // Catch any exceptions
            }
        }

        protected async void BtnDeleteOfficial_Click(object sender, CommandEventArgs e)
        {
            int officialId = Convert.ToInt32(e.CommandArgument); // Get the official_id from the button argument
            string apiUrl = "https://barangayapp.x10.mx/api/routes/deleteOfficials.php"; // PHP API URL for deleting official

            try
            {
                // Prepare the official_id in JSON format
                var jsonData = JsonConvert.SerializeObject(new { official_id = officialId });

                using (HttpClient client = new HttpClient())
                {
                    // Set up the request with JSON content type
                    var content = new StringContent(jsonData, System.Text.Encoding.UTF8, "application/json");
                    HttpResponseMessage response = await client.PostAsync(apiUrl, content);

                    if (response.IsSuccessStatusCode)
                    {
                        string apiResponse = await response.Content.ReadAsStringAsync();

                        // Deserialize the JSON response to extract the message
                        var jsonResponse = JsonConvert.DeserializeObject<Dictionary<string, string>>(apiResponse);

                        if (jsonResponse.ContainsKey("message"))
                        {
                            lblStatus.Text = jsonResponse["message"];  // Display the deletion status message
                        }
                        else
                        {
                            lblStatus.Text = "Unexpected response format.";  // Fallback in case of missing message
                        }

                        await FetchAndDisplayOfficials();  // Refresh officials list after deletion
                    }
                    else
                    {
                        lblStatus.Text = "Failed to delete official."; // Show error if the request fails
                    }
                }
            }
            catch (Exception ex)
            {
                lblStatus.Text = $"Error deleting official: {ex.Message}";  // Catch any exceptions
            }
        }



        protected async void BtnSearch_Click(object sender, EventArgs e)
        {
            string searchQuery = txtSearch.Text;
            await FetchAndDisplayOfficials(searchQuery);
        }
    }


    public class Official
    {
        public int Official_id { get; set; }      // official_id from database
        public string Official_name { get; set; } // official_name from database
        public string Official_position { get; set; } // official_position from database
        public string Contact_info { get; set; }  // contact_info from database
    }
}
