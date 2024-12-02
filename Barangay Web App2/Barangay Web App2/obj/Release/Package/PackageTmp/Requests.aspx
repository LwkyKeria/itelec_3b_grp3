<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Requests.aspx.cs" Inherits="Barangay_Web_App2.Requests" Async="true" %>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Requests - Barangay Web App</title>
    <link rel="stylesheet" type="text/css" href="styles/request.css" />
</head>
<body>
    <form id="form1" runat="server">
        <div id="header">
            Document Requests
        </div>

        <div id="content">
            <!-- Loading Indicator -->
            <div id="loadingMessage" runat="server" style="text-align: center; padding: 20px; display: none;">
                Loading requests, please wait...
            </div>

            <!-- Feedback Status -->
            <asp:Label ID="lblStatus" runat="server" CssClass="status-message" />

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
                    <asp:Repeater ID="RequestsRepeater" runat="server">
                        <ItemTemplate>
                            <tr>
                                <td><%# Eval("RequestID") %></td>
                                <td><%# Eval("ResidentName") %></td>
                                <td><%# Eval("DocumentName") %></td>
                                <td><%# Eval("Status") %></td>
                                <td>
                                    <asp:Button ID="ApproveButton" runat="server" Text="Approve" CssClass="approve"
                                        CommandArgument='<%# Eval("RequestID") %>' OnClick="ApproveRequest" />
                                    <asp:Button ID="RejectButton" runat="server" Text="Reject" CssClass="reject"
                                        CommandArgument='<%# Eval("RequestID") %>' OnClick="RejectRequest" />
                                </td>
                            </tr>
                        </ItemTemplate>
                    </asp:Repeater>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>
