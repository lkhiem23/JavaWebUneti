<%-- 
    Document   : ThemSV_LeHoaiKhiem
    Created on : Dec 3, 2024, 10:28:58 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="Student.KetNoi" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Sinh Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 20px;
        }

        h1, h2 {
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 20px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            font-weight: bold;
            margin-top: 10px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 10px 20px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .message {
            font-size: 16px;
            font-weight: bold;
            color: red;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Thêm Sinh Viên</h1>
    
    <form action="XLThemSV_LeHoaiKhiem" method="POST">
        <label for="mssv">Mã số sinh viên:</label>
        <input 
            type="text" 
            id="mssv" 
            name="mssv" 
            value="<%= request.getParameter("mssv") != null ? request.getParameter("mssv") : "" %>" 
            required>

        <label for="hoten">Họ tên:</label>
        <input 
            type="text" 
            id="hoten" 
            name="hoten" 
            value="<%= request.getParameter("hoten") != null ? request.getParameter("hoten") : "" %>" 
            required>

        <label for="lop">Lớp:</label>
        <input 
            type="text" 
            id="lop" 
            name="lop" 
            value="<%= request.getParameter("lop") != null ? request.getParameter("lop") : "" %>" 
            required>

        <label for="diemtb">Điểm trung bình:</label>
        <input 
            type="number" 
            step="0.01" 
            id="diemtb" 
            name="diemtb" 
            value="<%= request.getParameter("diemtb") != null ? request.getParameter("diemtb") : "" %>" 
            required>

        <button type="submit">Thêm</button>
    </form>

    <% 
        // Hiển thị thông báo nếu có
        String message = (String) request.getAttribute("message");
        if (message != null) { 
    %>
        <p class="message"><%= message %></p>
    <% } %>

    <h2>Danh sách sinh viên</h2>
    <table>
        <tr>
            <th>MSSV</th>
            <th>Họ tên</th>
            <th>Lớp</th>
            <th>Điểm trung bình</th>
            <th>Xếp loại</th>
        </tr>
        <% 
            Connection conn = null;
            try {
                conn = KetNoi.KNCSDL();
                if (conn != null) {
                    String sql = "SELECT * FROM Student";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        double diemTB = rs.getDouble("DiemTB");
                        String xepLoai;
                        if (diemTB >= 8.5) {
                            xepLoai = "Giỏi";
                        } else if (diemTB >= 6.5) {
                            xepLoai = "Khá";
                        } else if (diemTB >= 5.0) {
                            xepLoai = "Trung bình";
                        } else {
                            xepLoai = "Yếu";
                        }
        %>
        <tr>
            <td><%= rs.getString("MSSV") %></td>
            <td><%= rs.getString("HoTen") %></td>
            <td><%= rs.getString("Lop") %></td>
            <td><%= rs.getDouble("DiemTB") %></td>
            <td><%= xepLoai %></td>
        </tr>
        <%
                    }
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>

</body>
</html>
