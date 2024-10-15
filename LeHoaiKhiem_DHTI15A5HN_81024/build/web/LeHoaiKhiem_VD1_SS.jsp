<%-- 
    Document   : LeHoaiKhiem_VD1_SS
    Created on : Oct 8, 2024, 9:33:16 AM
    Author     : ADMIN
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LÊ HOÀI KHIÊM_DHTI15A5HN_21103100327</h1>

        <!-- Form để nhập tên -->
        <form action="LeHoaiKhiem_VD1_SS.jsp" method="post">
            <label for="name">Nhập tên:</label>
            <input type="text" id="name" name="name">
            <input type="submit" value="Hiển thị">
        </form>

        <%
            // Lấy session

            // Lấy tên từ request và lưu vào session
            String name = request.getParameter("name");
            if (name != null && !name.trim().isEmpty()) {
                session.setAttribute("userName", name);
            }

            // Hiển thị tên từ session nếu có
            String savedName = (String) session.getAttribute("userName");
            if (savedName != null) {
        %>
            <p>Tên bạn vừa nhập là: <strong><%= savedName %></strong></p>
        <%
            }
        %>
    </body>
</html>