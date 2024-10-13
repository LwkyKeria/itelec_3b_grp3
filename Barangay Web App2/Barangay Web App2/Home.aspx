<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Barangay_Web_App2.Home" Async="true" %>

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

        /* Styling for the event creation form */
        .event-form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            text-align: left;
        }

        input[type="text"], input[type="date"], input[type="time"] {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
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
            <h1>Create New Event</h1>

            <!-- Event Creation Form -->
            <div class="event-form">
                <label for="eventTitle">Event Title:</label>
                <asp:TextBox ID="eventTitle" runat="server" />

                <label for="eventDescription">Event Description:</label>
                <asp:TextBox ID="eventDescription" runat="server" TextMode="MultiLine" Rows="4" />

                <label for="eventDate">Event Date:</label>
                <asp:TextBox ID="eventDate" runat="server" TextMode="Date" />

                <label for="eventTime">Event Time:</label>
                <asp:TextBox ID="eventTime" runat="server" TextMode="Time" />

                <label for="eventLocation">Event Location:</label>
                <asp:TextBox ID="eventLocation" runat="server" />

                <br /><br />
                <asp:Button ID="SubmitEvent" Text="Submit Event" OnClick="SubmitEvent_Click" runat="server" />
            </div>
        </div>

        <div id="footer">
            &copy; 2024 Barangay Web App - Jasper Company.
        </div>
    </form>
</body>
</html>
