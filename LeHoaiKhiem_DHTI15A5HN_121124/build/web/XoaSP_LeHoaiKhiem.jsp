<%-- 
    Document   : XoaSP_LeHoaiKhiem
    Created on : Nov 12, 2024, 9:00:15 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<%
    // Kết nối cơ sở dữ liệu
    String url = "jdbc:mysql://localhost:3306/QLSanPham";
    String user = "root";  // Thay bằng username của bạn
    String password = "";  // Thay bằng password của bạn
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
        
        // Lấy danh sách sản phẩm hiện có
        String query = "SELECT * FROM SanPham";
        rs = stmt.executeQuery(query);
%>
<html>
<head><title>Xóa Sản Phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 20px;
        }

        h2 {
            color: #4CAF50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        form {
            margin-top: 20px;
        }

        input[type="text"] {
            padding: 8px;
            width: 200px;
            margin-right: 10px;
        }

        input[type="submit"] {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .message {
            font-size: 18px;
            color: #4CAF50;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Danh sách sản phẩm</h2>
    <table border="1">
        <tr>
            <th>Mã SP</th>
            <th>Tên SP</th>
            <th>Loại SP</th>
            <th>Giá</th>
        </tr>
        <%
            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("MaSP") %></td>
            <td><%= rs.getString("TenSP") %></td>
            <td><%= rs.getString("LoaiSP") %></td>
            <td><%= rs.getDouble("Gia") %></td>
        </tr>
        <%
            }
        %>
    </table>
    
    <h2>Xóa sản phẩm</h2>
    <form action="HienThiSP_LeHoaiKhiem" method="post">
        <label for="maSP">Nhập mã sản phẩm cần xóa:</label>
        <input type="text" id="maSP" name="maSP" required>
        <input type="submit" value="Xóa">
    </form>
</body>
</html>
<%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>
