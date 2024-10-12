using System;
using System.Net.Http;
using System.Threading.Tasks;
using System.Collections.Generic;
using Newtonsoft.Json;

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
                            officials = officials.FindAll(o => o.official_name.ToLower().Contains(searchQuery.ToLower()));
                        }

                        // Bind data to the repeater control
                        OfficialsRepeater.DataSource = officials;
                        OfficialsRepeater.DataBind();
                    }
                    else
                    {
                        // Handle API errors
                        // Optionally display an error message to the user
                    }
                }
            }
            catch (Exception ex)
            {
                // Optionally log exception here
                // Optionally display an error message to the user
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
                        // Fetch and display updated list after successful insertion
                        await FetchAndDisplayOfficials();
                    }
                    else
                    {
                        // Handle insertion failure
                    }
                }
            }
            catch (Exception ex)
            {
                // Optionally log exception here
                // Optionally display an error message to the user
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
        public int official_id { get; set; }      // official_id from database
        public string official_name { get; set; } // official_name from database
        public string official_position { get; set; } // official_position from database
        public string contact_info { get; set; }  // contact_info from database
    }
}
