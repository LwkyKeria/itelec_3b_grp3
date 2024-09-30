<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="About.aspx.cs" Inherits="Barangay_Web_App2.About" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>About - Barangay Web App</title>
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

        #content {
            padding: 20px;
            text-align: center;
        }

        table {
            width: 100%;
            margin-bottom: 20px;
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
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div id="header">
            About Barangay
        </div>

        <div id="content">
            <h2>Barangay Officials</h2>
            <table>
                <thead>
                    <tr>
                        <th>Official ID</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Contact Information</th>
                    </tr>
                </thead>
                <tbody>
                     <%-- Placeholder without database --%>
                    <tr>
                        <td>1</td>
                        <td>Kim Andrei Besmar</td>
                        <td>Barangay Captain</td>
                        <td>+63 912 345 6789</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jasper Dave</td>
                        <td>Barangay Secretary</td>
                        <td>+63 912 987 6543</td>
                    </tr>
                </tbody>
            </table>

            <h2>Registered Residents</h2>
            <table>
                <thead>
                    <tr>
                        <th>Resident ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Address</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- Placeholder without database --%>
                    <tr>
                        <td>1</td>
                        <td>Joshua Ivan Latag</td>
                        <td>john@example.com</td>
                        <td>123 Barangay St.</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>MJ JAMAICA</td>
                        <td>jane@example.com</td>
                        <td>456 Sitio Hill</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>
