<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="Barangay_Web_App2.index" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Barangay App Login</title>
    <style type="text/css">
        #form1 {
            height: 208px;
            background-color: lightblue;
            padding: 20px;
            width: 300px;
            margin: 0 auto;
            text-align: center;
            border-radius: 10px;
        }
        body {
            background-color: #f0f0f0;
        }
        #lblResult {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <h2>Barangay App Login</h2>
        <div>
            <label for="txtName">Enter your name:</label>
            <asp:TextBox ID="txtName" runat="server"></asp:TextBox>
        </div>
        <div>
            <label for="txtPassword">Enter your pass:</label>
            <asp:TextBox ID="txtPassword" runat="server" TextMode="Password"></asp:TextBox>
        </div>
        <div style="margin-top: 10px;">
            <asp:Button ID="btnSubmit" runat="server" Text="Login" OnClick="Submit_Click" />
        </div>
        <div>
            <asp:Label ID="lblResult" runat="server"></asp:Label>
        </div>
    </form>
</body>
</html>
