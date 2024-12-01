<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Requests.aspx.cs" Inherits="Barangay_Web_App2.Requests" %>

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
                    <%-- Placeholder for dynamic request data --%>
                    <tr>
                        <td>1</td>
                        <td>Joshua B. Dulla</td>
                        <td>Barangay Clearance</td>
                        <td>Pending</td>
                        <td>
                            <button class="approve">Approve</button>
                            <button class="reject">Reject</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>
