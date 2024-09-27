<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Residents.aspx.cs" Inherits="Barangay_Web_App2.Residents" %>

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

        #content {
            padding: 20px;
            text-align: center;
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
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div id="header">
            Registered Residents
        </div>

        <div id="content">
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
                    <%-- Placeholder for dynamic residents data --%>
                    <tr>
                        <td>1</td>
                        <td>Jasper B. Latag</td>
                        <td>123456@ub.edu.ph</td>
                        <td>Heaven Street</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>
</body>
</html>
