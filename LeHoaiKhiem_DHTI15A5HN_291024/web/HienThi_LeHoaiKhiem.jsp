<%-- 
    Document   : HienThi_LeHoaiKhiem
    Created on : Oct 29, 2024, 9:24:32 AM
    Author     : ADMIN
--%>

<%@page import="classes.Xemay"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%
    // Kết nối và lấy danh sách xe máy từ cơ sở dữ liệu
    String jdbcURL = "jdbc:mysql://localhost:3306/qlxemay";
    String jdbcUsername = "root";
    String jdbcPassword = "";

    List<Xemay> xemayList = new ArrayList<>();
    String SELECT_ALL_XEMAY = "SELECT * FROM xemay";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_XEMAY);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            int ma = rs.getInt("Ma");
            String ten = rs.getString("Ten");
            String hangSX = rs.getString("HangSX");
            String mau = rs.getString("Mau");
            xemayList.add(new Xemay(ma, ten, hangSX, mau));
        }

        rs.close();
        preparedStatement.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách xe máy</title>
</head>
<body>
    <h2>Lê Hoài Khiêm - DHTI15A5HN - 21103100327</h2>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #ddd;
            background-color: #fff;
        }

        th {
            
            color: #000;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        @media (max-width: 768px) {
            table {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <h2>Danh sách xe máy</h2>
    <table>
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Hãng Sản Xuất</th>
            <th>Màu</th>
        </tr>
        <%
            for (Xemay xe : xemayList) {
        %>
        <tr>
            <td><%= xe.getMa() %></td>
            <td><%= xe.getTen() %></td>
            <td><%= xe.getHangSX() %></td>
            <td><%= xe.getMau() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>