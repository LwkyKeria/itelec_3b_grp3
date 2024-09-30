using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Barangay_Web_App2
{
    public partial class index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void Submit_Click(object sender, EventArgs e)
        {
            String user = txtName.Text;
            String pass = txtPassword.Text;

            if ((user == "Kim") && (pass == "Besmar"))
            {
                Response.Redirect("Home.aspx");

            }
            else
            {
                lblResult.Text = "Please enter the correct input.S";
            }
                    
        }
    }
}