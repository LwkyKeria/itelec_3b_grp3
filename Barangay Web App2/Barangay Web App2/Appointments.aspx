<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Appointments.aspx.cs" Inherits="Barangay_Web_App2.Appointments" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Appointments</title>
    <link rel="stylesheet" type="text/css" href="styles/appointments.css" />
</head>
<body>
    <div id="header">Barangay Appointments</div>
    <div id="nav">
        <a href="Home.aspx">Home</a>
        <a href="About.aspx">About</a>
        <a href="Residents.aspx">Residents</a>
        <a href="Requests.aspx">Requests</a>
        <a href="Appointments.aspx">Appointments</a>
    </div>

    <form id="form1" runat="server">
        <div id="content">
            <!-- Feedback Status -->
            <asp:Label ID="lblStatus" runat="server" CssClass="status-message" />

            <!-- Search Bar -->
            <div id="searchContainer">
                <asp:TextBox ID="txtSearch" runat="server" CssClass="searchBox" placeholder="Search by reason or official..." />
                <asp:Button ID="BtnSearch" runat="server" Text="Search" CssClass="searchButton" OnClick="BtnSearch_Click" />
            </div>

            <!-- Appointments Table -->
            <asp:Panel ID="AppointmentsContainer" runat="server">
                <asp:Repeater ID="AppointmentsRepeater" runat="server">
                    <HeaderTemplate>
                        <table>
                            <thead>
                                <tr>
                                    <th>Appointment ID</th>
                                    <th>Reason</th>
                                    <th>Official</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                    </HeaderTemplate>
                    <ItemTemplate>
                        <tr>
                            <td><%# Eval("Id") %></td>
                            <td><%# Eval("Reason") %></td>
                            <td><%# Eval("OfficialName") %> (<%# Eval("OfficialPosition") %>)</td>
                            <td><%# Eval("Date", "{0:MMMM dd, yyyy}") %></td>
                            <td><%# Eval("Status") ?? "Pending" %></td>
                            <td>
                                <asp:Button ID="btnAccept" runat="server" Text="Accept" CssClass="approveButton"
                                    CommandArgument='<%# Eval("Id") %>' OnClick="UpdateAppointmentStatus" CommandName="accepted" />
                                <asp:Button ID="btnReject" runat="server" Text="Reject" CssClass="rejectButton"
                                    CommandArgument='<%# Eval("Id") %>' OnClick="UpdateAppointmentStatus" CommandName="rejected" />
                            </td>
                        </tr>
                    </ItemTemplate>
                    <FooterTemplate>
                            </tbody>
                        </table>
                    </FooterTemplate>
                </asp:Repeater>
            </asp:Panel>

            <!-- Empty State -->
            <asp:Label ID="NoAppointmentsMessage" runat="server" CssClass="no-appointments-message" Text="No appointments found." Visible="false" />
        </div>
    </form>
</body>
</html>
