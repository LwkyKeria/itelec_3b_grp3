<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Barangay_Web_App2.Home" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Home - Barangay Web App</title>
    <link rel="stylesheet" type="text/css" href="styles/home.css" />
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
    <a href="Appointments.aspx">Appointments</a>
</div>

        <div id="content">
            <h1>Create and View Events</h1>

            <!-- Event Creation Form -->
            <div class="event-form">
                <h2>Create New Event</h2>
                <label for="txtEventTitle">Event Title:</label>
                <asp:TextBox ID="txtEventTitle" runat="server" placeholder="Enter event title" />

                <label for="txtEventDescription">Event Description:</label>
                <asp:TextBox ID="txtEventDescription" runat="server" TextMode="MultiLine" Rows="4" placeholder="Enter event description" />

                <label for="txtEventDate">Event Date:</label>
                <input type="date" id="txtEventDate" runat="server" />

                <label for="txtEventTime">Event Time:</label>
                <input type="time" id="txtEventTime" runat="server" />


                <label for="txtEventLocation">Event Location:</label>
                <asp:TextBox ID="txtEventLocation" runat="server" placeholder="Enter event location" />

                <br /><br />
                <asp:Button ID="SubmitEvent" Text="Submit Event" OnClick="BtnAddEvent_Click" runat="server" />
                <asp:Label ID="lblStatus" runat="server" CssClass="status-message" />
            </div>

            <!-- Event List -->
            <div class="event-list">
                <h2>Events List</h2>
                <asp:Repeater ID="EventsRepeater" runat="server">
                    <HeaderTemplate>
                        <table>
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Location</th>
                                </tr>
                            </thead>
                            <tbody>
                    </HeaderTemplate>
                    <ItemTemplate>
                        <tr>
                            <td><%# Eval("Event_title") %></td>
                            <td><%# Eval("Event_description") %></td>
                            <td><%# Eval("Event_date") %></td>
                            <td><%# Eval("Event_time") %></td>
                            <td><%# Eval("Event_location") %></td>
                        </tr>
                    </ItemTemplate>
                    <FooterTemplate>
                            </tbody>
                        </table>
                    </FooterTemplate>
                </asp:Repeater>

                <!-- Placeholder for no data -->
                <div id="noEventsMessage" runat="server" style="text-align:center; padding:20px; color:gray; display:none;">
                    No events found. Try creating one!
                </div>
            </div>
        </div>

        <div id="footer">
            &copy; 2024 Barangay Web App - Jasper Company.
        </div>
    </form>
</body>
</html>
