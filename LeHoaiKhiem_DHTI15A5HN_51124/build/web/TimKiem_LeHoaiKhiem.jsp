<%-- 
    Document   : TimKiem_LeHoaiKhiem
    Created on : Nov 5, 2024, 9:13:44 AM
    Author     : ADMIN
--%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.sql.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <title>Search Phones - Le Hoai Khiem</title>
</head>
<body>
    <h2>Danh sách điện thoại</h2>
    <table border="1">
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
        </tr>
         <%
            // Kết nối cơ sở dữ liệu và lấy dữ liệu từ bảng DienThoai
            DecimalFormat df = new DecimalFormat("#,###");  // Định dạng số với dấu phẩy
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qldt", "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dienthoai");

                while(rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("Ma") + "</td>");
                    out.println("<td>" + rs.getString("Ten") + "</td>");
                    out.println("<td>" + rs.getInt("Soluong") + "</td>");
                    out.println("<td>" + df.format(rs.getDouble("Dongia")) + "</td>");  // Định dạng đơn giá
                    out.println("</tr>");
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        %>
    </table>

    <h2>Tìm kiếm điện thoại</h2>
    <form action="XLTimKiem_LeHoaiKhiem" method="get">
        <label for="ma">Mã điện thoại:</label>
        <input type="text" name="ma" id="ma" required>
        <button type="submit">Tìm kiếm</button>
    </form>

    <%
        String message = request.getParameter("message");
        if (message != null) {
    %>
        <p><%= message %></p>
    <% } %>
</body>
</html>
