<%-- 
    Document   : VD_jsp
    Created on : Oct 1, 2024, 9:17:13 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lê Hoài Khiêm _ Nhập họ tên</h1>
        
       <form method="POST">
            <label for="hoTen">Nhập họ tên của bạn:</label>
            <input type="text" id="hoTen" name="hoTen" required>
            <input type="submit" value="Hiển thị">
        </form>

        <% 
        String hoTen = request.getParameter("hoTen");
        if (hoTen != null && !hoTen.trim().isEmpty()) {
        %>
            <p>Tên vừa nhập là: <%=hoTen%></p>
        <% 
        }
        %>
        
    </body>
</html>
