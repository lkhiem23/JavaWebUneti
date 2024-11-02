<%-- 
    Document   : Them_LeHoaiKhiem
    Created on : Oct 29, 2024, 10:47:59 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="classes.Xemay" %>
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
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Xe Máy</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f9f9f9;
        }

        h2 {
            color: #333;
            text-align: center;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            max-width: 400px;
            margin: auto;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            
            color: #000;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Danh sách xe máy hiện có</h2>
    <table border="1">
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

    <h2>Thêm thông tin xe máy mới</h2>
    <form action="HTServlet_LeHoaiKhiem" method="POST">
        <label for="ma">Mã Xe:</label>
        <input type="number" id="ma" name="ma" required><br><br>
        
        <label for="ten">Tên Xe:</label>
        <input type="text" id="ten" name="ten" required><br><br>
        
        <label for="hangSX">Hãng Sản Xuất:</label>
        <input type="text" id="hangSX" name="hangSX" required><br><br>
        
        <label for="mau">Màu:</label>
        <input type="text" id="mau" name="mau" required><br><br>
        
        <input type="submit" value="Thêm Xe">
    </form>
</body>
</html>