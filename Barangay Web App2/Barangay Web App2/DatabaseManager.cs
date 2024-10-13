using System;
using System.Configuration;
using MySql.Data.MySqlClient;

namespace Barangay_Web_App2
{
    public class DatabaseManager
    {
        private readonly string connectionString;  // Marked as readonly

        public DatabaseManager()
        {
            connectionString = ConfigurationManager.ConnectionStrings["MyConnectionString"].ConnectionString;
        }

        public bool Login(string username, string password)
        {
            bool isValidUser = false;
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                string query = "SELECT COUNT(*) FROM Users WHERE Username = @Username AND Password = @Password";
                MySqlCommand cmd = new MySqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@Username", username);
                cmd.Parameters.AddWithValue("@Password", password);
                int count = Convert.ToInt32(cmd.ExecuteScalar());
                if (count > 0)
                {
                    isValidUser = true;
                }
            }
            return isValidUser;
        }
    }
}
