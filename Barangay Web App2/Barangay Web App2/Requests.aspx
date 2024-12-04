<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Requests.aspx.cs" Inherits="Barangay_Web_App2.Requests" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Document Requests - Barangay Web App</title>
    <link rel="stylesheet" type="text/css" href="styles/request.css" />
</head>
<body>
    <div id="header">Document Requests</div>
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
                <asp:TextBox ID="txtSearch" runat="server" CssClass="searchBox" placeholder="Search by name or document..." />
                <asp:Button ID="BtnSearch" runat="server" Text="Search" CssClass="searchButton" OnClick="BtnSearch_Click" />
            </div>

            <!-- Requests Table -->
            <asp:PlaceHolder ID="RequestsContainer" runat="server">
                <asp:Repeater ID="RequestsRepeater" runat="server">
                    <HeaderTemplate>
                        <table>
                            <thead>
                                <tr>
                                    <th>Request ID</th>
                                    <th>Resident Name</th>
                                    <th>Document Requested</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                    </HeaderTemplate>
                    <ItemTemplate>
                        <tr>
                            <td><%# Eval("RequestId") %></td>
                            <td><%# Eval("FirstName") %> <%# Eval("LastName") %></td>
                            <td><%# Eval("DocumentName") %></td>
                            <td><%# Eval("Status") ?? "Pending" %></td>
                            <td>
                                <asp:Button ID="ApproveButton" runat="server" Text="Approve" CssClass="approveButton"
                                    CommandArgument='<%# Eval("RequestId") %>' OnClick="ApproveRequestAsync" />
                                <asp:Button ID="RejectButton" runat="server" Text="Reject" CssClass="rejectButton"
                                    CommandArgument='<%# Eval("RequestId") %>' OnClick="RejectRequestAsync" />
                            </td>
                        </tr>
                    </ItemTemplate>
                    <FooterTemplate>
                            </tbody>
                        </table>
                    </FooterTemplate>
                </asp:Repeater>

            </asp:PlaceHolder>

            <!-- Empty State -->
            <asp:Label ID="NoRequestsMessage" runat="server" CssClass="no-requests-message" Text="No requests found." Visible="false" />
        </div>
    </form>
</body>
</html>
