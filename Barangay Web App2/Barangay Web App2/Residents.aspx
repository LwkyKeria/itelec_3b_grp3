<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Residents.aspx.cs" Inherits="Barangay_Web_App2.Residents" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Residents - Barangay Web App</title>
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
    background-color: #f8f9fa;
    text-align: center;
    padding: 10px 0;
    margin-bottom: 20px;
}

    #nav a {
        margin: 0 15px;
        text-decoration: none;
        color: #007bff;
        font-size: 18px;
        font-weight: 500;
    }

        #nav a:hover {
            color: #0056b3;
        }

        #content {
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        .status {
            color: red;
        }

        .empty-message {
            text-align: center;
            color: gray;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div id="header">
            Registered Residents
        </div>
                <div id="nav">
    <a href="Home.aspx">Home</a>
    <a href="About.aspx">About</a>
    <a href="Residents.aspx">Residents</a>
    <a href="Requests.aspx">Requests</a>
    <a href="Appointments.aspx">Appointments</a>
</div>

        <div id="content">
            <!-- Search Box -->
            <asp:TextBox ID="txtSearch" runat="server" placeholder="Search by name..." />
            <asp:Button ID="btnSearch" runat="server" Text="Search" OnClick="btnSearch_Click" />

            <asp:Label ID="lblStatus" runat="server" CssClass="status" Text="" />

            <!-- Repeater Control -->
            <asp:Repeater ID="ResidentsRepeater" runat="server">
                <HeaderTemplate>
                    <table>
                        <thead>
                            <tr>
                                <th>Resident ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Location</th>
                            </tr>
                        </thead>
                        <tbody>
                </HeaderTemplate>

                <ItemTemplate>
                    <tr>
                        <td><%# Eval("ResidentID") %></td>
                        <td><%# Eval("FullName") %></td>
                        <td><%# Eval("Phone") %></td>
                        <td><%# Eval("Location") %></td>
                    </tr>
                </ItemTemplate>

                <FooterTemplate>
                        </tbody>
                    </table>
                </FooterTemplate>
            </asp:Repeater>

            <!-- Empty Data Message -->
            <asp:Panel ID="EmptyDataPanel" runat="server" CssClass="empty-message" Visible="false">
                No residents found.
            </asp:Panel>
        </div>
    </form>
</body>
</html>
