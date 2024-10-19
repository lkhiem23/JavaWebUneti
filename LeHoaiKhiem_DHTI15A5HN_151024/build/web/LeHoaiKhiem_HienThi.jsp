<%-- 
    Document   : LeHoaiKhiem_HienThi
    Created on : Oct 15, 2024, 10:21:06 AM
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
        <p>Bạn tên là: <%= session.getAttribute("name") %></p>
        <p>Năm nay bạn: <%= session.getAttribute("age") %> tuổi</p>
    </body>
</html>
