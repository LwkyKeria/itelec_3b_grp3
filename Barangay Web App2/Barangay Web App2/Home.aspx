<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Barangay_Web_App2.Home" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Home - Barangay Web App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        #header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 15px 0;
            font-size: 24px;
        }

        #nav {
            margin: 15px 0;
            text-align: center;
        }

        #nav a {
            margin: 0 15px;
            text-decoration: none;
            color: #4CAF50;
            font-size: 18px;
        }

        #content {
            text-align: center;
            padding: 20px;
        }

        #content h1 {
            font-size: 32px;
            color: #333;
        }

        #footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div id="header">
            Welcome to Barangay Web App!
        </div>

        <div id="nav">
            <a href="Home.aspx">Home</a>
            <a href="About.aspx">About</a>
            <a href="Residents.aspx">Residents</a>
            <a href="Requests.aspx">Requests</a>
        </div>

        <div id="content">
            <h1>Welcome, Admin!</h1>
            <p>Welcome to Barangay management system.</p>
        </div>

        <div id="footer">
            &copy; 2024 Barangay Web App - Jasper Company.
        </div>
    </form>
</body>
</html>
