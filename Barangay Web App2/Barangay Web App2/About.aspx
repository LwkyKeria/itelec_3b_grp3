<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="About.aspx.cs" Inherits="Barangay_Web_App2.About" Async="true" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Barangay Officials</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="styles/about.css" />
</head>
<body>
    <div id="header">
    Barangay Officials
    </div>
            <div id="nav">
    <a href="Home.aspx">Home</a>
    <a href="About.aspx">About</a>
    <a href="Residents.aspx">Residents</a>
    <a href="Requests.aspx">Requests</a>
    <a href="Appointments.aspx">Appointments</a>
</div>

    <form id="form1" runat="server">
        <div class="container">
            <!-- Search functionality -->
            <div class="row mb-4">
                <div class="col-md-8">
                    <div class="input-group">
                        <asp:TextBox ID="txtSearch" runat="server" CssClass="form-control" placeholder="Search officials by name"></asp:TextBox>
                        <div class="input-group-append">
                            <asp:Button ID="BtnSearch" runat="server" Text="Search" CssClass="btn btn-primary" OnClick="BtnSearch_Click" />
                        </div>
                    </div>
                </div>
            </div>

            <!-- Error or Status Message -->
            <asp:Label ID="lblStatus" runat="server" CssClass="status-message text-danger"></asp:Label>

            <!-- Official Input Form -->
            <h3 class="mt-4">Add Barangay Official</h3>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="txtOfficialName">Name:</label>
                        <asp:TextBox ID="txtOfficialName" runat="server" CssClass="form-control" placeholder="Enter official's name"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label for="txtOfficialPosition">Position:</label>
                        <asp:TextBox ID="txtOfficialPosition" runat="server" CssClass="form-control" placeholder="Enter official's position"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label for="txtContactInfo">Contact Info:</label>
                        <asp:TextBox ID="txtContactInfo" runat="server" CssClass="form-control" placeholder="Enter official's contact info"></asp:TextBox>
                    </div>
                    <asp:Button ID="BtnAddOfficial" runat="server" Text="Add Official" CssClass="btn btn-success" OnClick="BtnAddOfficial_Click" />
                </div>
            </div>

            <!-- Officials Table -->
            <h3 class="mt-5">List of Barangay Officials</h3>
            <asp:Repeater ID="OfficialsRepeater" runat="server">
    <HeaderTemplate>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Contact Info</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
    </HeaderTemplate>
    <ItemTemplate>
        <tr>
            <!-- Store official_id in a hidden field instead of displaying it -->
            <td><%# Eval("official_name") %></td>
            <td><%# Eval("official_position") %></td>
            <td><%# Eval("contact_info") %></td>
            <td>
                <!-- Hidden Field to hold official_id -->
                <asp:HiddenField ID="HiddenOfficialID" runat="server" Value='<%# Eval("official_id") %>' />

                <!-- Button to delete, referencing the hidden field value -->
                <asp:Button ID="BtnDeleteOfficial" runat="server" Text="Delete" CommandArgument='<%# Eval("official_id") %>' OnCommand="BtnDeleteOfficial_Click" CssClass="btn btn-danger" />
            </td>
        </tr>
    </ItemTemplate>
    <FooterTemplate>
        </tbody>
        </table>
    </FooterTemplate>
</asp:Repeater>

        </div>
    </form>

    <!-- Add Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
