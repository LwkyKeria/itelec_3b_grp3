using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.Web;

namespace Barangay_Web_App2
{
    public class UserController
    {
    }
    public class DataService
    {
        private readonly HttpClient _httpClient;

        public DataService()
        {
            _httpClient = new HttpClient();
        }

        public async Task<List<YourDataType>> GetDataFromApi()
        {
            string apiUrl = ConfigurationManager.AppSettings["ApiUrl"];
            var response = await _httpClient.GetStringAsync(apiUrl);

            // Deserialize JSON response to your data type
            var data = JsonConvert.DeserializeObject<List<YourDataType>>(response);
            return data;
        }
    }

    // Define your data type based on the structure of your database table
    public class YourDataType
    {
        public int Id { get; set; } // Example property
        public string Name { get; set; } // Example property
                                         // Add other properties as needed
    }
    public async Task<ActionResult> Index()
    {
        var dataService = new DataService();
        var data = await dataService.GetDataFromApi();
        return View(data); // Pass data to your view
    }

}