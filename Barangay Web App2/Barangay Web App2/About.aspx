<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="About.aspx.cs" Inherits="Barangay_Web_App2.About" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Barangay Officials</title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <!-- Search functionality -->
            <h2>Search Barangay Officials</h2>
            <asp:TextBox ID="txtSearch" runat="server"></asp:TextBox>
            <asp:Button ID="BtnSearch" runat="server" Text="Search" OnClick="BtnSearch_Click" />

            <!-- Error Label -->
            <asp:Label ID="lblError" runat="server" ForeColor="Red"></asp:Label>

            <!-- Official Input Form -->
            <h2>Add Barangay Official</h2>
            <div>
                <label for="txtOfficialName">Name:</label>
                <asp:TextBox ID="txtOfficialName" runat="server"></asp:TextBox>
            </div>
            <div>
                <label for="txtOfficialPosition">Position:</label>
                <asp:TextBox ID="txtOfficialPosition" runat="server"></asp:TextBox>
            </div>
            <div>
                <label for="txtContactInfo">Contact Info:</label>
                <asp:TextBox ID="txtContactInfo" runat="server"></asp:TextBox>
            </div>
            <asp:Button ID="BtnAddOfficial" runat="server" Text="Add Official" OnClick="BtnAddOfficial_Click" />

            <!-- Officials Table -->
            <h2>Barangay Officials</h2>
            <asp:Repeater ID="OfficialsRepeater" runat="server">
                <HeaderTemplate>
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Position</th>
                            <th>Contact Info</th>
                        </tr>
                </HeaderTemplate>
                <ItemTemplate>
                    <tr>
                        <td><%# Eval("official_id") %></td>
                        <td><%# Eval("official_name") %></td>
                        <td><%# Eval("official_position") %></td>
                        <td><%# Eval("contact_info") %></td>
                    </tr>
                </ItemTemplate>
                <FooterTemplate>
                    </table>
                </FooterTemplate>
            </asp:Repeater>
        </div>
    </form>
</body>
</html>
