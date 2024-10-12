using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Mvc; // Ensure you're using System.Web.Mvc for MVC ActionResult

namespace Barangay_Web_App2
{
    public class UserController : Controller
    {
        // Index action method to fetch data from API and pass it to the view
        public async Task<ActionResult> Index()
        {
            try
            {
                var dataService = new DataService();
                var data = await dataService.GetDataFromApi();
                return View(data); // Pass data to your view
            }
            catch (Exception ex)
            {
                // Optionally log exception here
                return View("Error"); // Return to an error view
            }
        }
    }

    // DataService class to handle API requests
    public class DataService
    {
        private readonly HttpClient _httpClient;

        public DataService()
        {
            _httpClient = new HttpClient();
        }

        // Method to fetch data from the API
        public async Task<List<YourDataType>> GetDataFromApi()
        {
            try
            {
                // Fetch the API URL from web.config
                string apiUrl = ConfigurationManager.AppSettings["ApiUrl"];
                var response = await _httpClient.GetStringAsync(apiUrl);

                // Deserialize JSON response into a list of YourDataType
                var data = JsonConvert.DeserializeObject<List<YourDataType>>(response);
                return data ?? new List<YourDataType>(); // Return empty list if null
            }
            catch (Exception ex)
            {
                // Optionally log exception here
                return new List<YourDataType>(); // Return empty list on failure
            }
            finally
            {
                _httpClient.Dispose(); // Dispose HttpClient
            }
        }
    }

    // Define your data type based on the structure of your API response
    public class YourDataType
    {
        public int Id { get; set; } // Example property
        public string Name { get; set; } // Example property
        // Add other properties as needed to match your data structure
    }
}
