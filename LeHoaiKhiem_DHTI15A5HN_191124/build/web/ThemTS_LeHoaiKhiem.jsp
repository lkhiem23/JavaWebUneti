<%-- 
    Document   : ThemTS_LeHoaiKhiem
    Created on : Nov 19, 2024, 9:20:02 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="QLThiSinh.KetNoi" %>
<html>
<head>
    <title>Thêm Thí Sinh</title>
</head>
<body>
    <!-- Hiển thị thông báo -->
    <%
        String message = request.getParameter("message");
        if (message != null) {
            out.println("<p style='color: red;'>" + message + "</p>");
        }
    %>
    <h2>Thêm Thí Sinh</h2>
    <form action="XLThemTS_LeHoaiKhiem" method="post">
        Số báo danh: <input type="text" name="sbd" required><br>
        Họ tên: <input type="text" name="hoten" required><br>
        Điểm Toán: <input type="number" name="diemtoan" step="0.1" required><br>
        Điểm Văn: <input type="number" name="diemvan" step="0.1" required><br>
        <button type="submit">Thêm</button>
    </form>
    <h3>Kết quả hiện tại:</h3>
    <table border="1">
        <tr>
            <th>Số báo danh</th>
            <th>Họ tên</th>
            <th>Điểm Toán</th>
            <th>Điểm Văn</th>
            <th>Kết quả</th>
        </tr>
        <%
            Connection conn = null;
            try {
                conn = KetNoi.KNCSDL();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM thisinh");

                while (rs.next()) {
                    float diemToan = rs.getFloat("DiemToan");
                    float diemVan = rs.getFloat("DiemVan");
                    String ketQua = (diemToan + diemVan >= 10) ? "Đỗ" : "Trượt";

                    out.println("<tr>");
                    out.println("<td>" + rs.getString("SBD") + "</td>");
                    out.println("<td>" + rs.getString("HoTen") + "</td>");
                    out.println("<td>" + diemToan + "</td>");
                    out.println("<td>" + diemVan + "</td>");
                    out.println("<td>" + ketQua + "</td>");
                    out.println("</tr>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        %>
    </table>
</body>
</html>