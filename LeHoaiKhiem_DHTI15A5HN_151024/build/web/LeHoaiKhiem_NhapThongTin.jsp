<%-- 
    Document   : LeHoaiKhiem_NhapThongTin
    Created on : Oct 15, 2024, 10:18:17 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nhập Thông Tin</title>
    </head>
    <body>
      <body>
    <%
        // Retrieve parameters from the request
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        // Check if the request method is POST
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (name == null || name.trim().isEmpty() || age == null || age.trim().isEmpty()) {
                // Redirect to the error page if name or age is empty
                response.sendRedirect("LeHoaiKhiem_Loi.jsp");
                return;
            } else {
                // Store the information in the session and redirect to HienThi.jsp
                session.setAttribute("name", name);
                session.setAttribute("age", age);
                response.sendRedirect("LeHoaiKhiem_HienThi.jsp");
                return;
            }
        }
    %>

    <!-- Display the input form -->
    <form action="LeHoaiKhiem_NhapThongTin.jsp" method="post">
        <h1>Lê Hoài Khiêm - DHTI15A5HN - 21103100327</h1>
        <h2>NHẬP THÔNG TIN</h2>
        <label for="name">Nhập Tên:</label>
        <input type="text" name="name" id="name" value="<%= (name != null) ? name : "" %>"><br>
        <label for="age">Nhập Tuổi:</label>
        <input type="text" name="age" id="age" value="<%= (age != null) ? age : "" %>"><br>
        <input type="submit" value="HIỂN THỊ">
    </form>
</body>
</html>
