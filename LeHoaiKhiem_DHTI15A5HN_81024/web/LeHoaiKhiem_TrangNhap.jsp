<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String[] classes = {"Lớp 10", "Lớp 11", "Lớp 12"};
%>
<!DOCTYPE html>
<html>
<head>
    <title>Trang Nhập Thông Tin</title>
</head>
<body>
    <form action="LeHoaiKhiem_TrangNhap.jsp" method="post">
    Nhập Tên: <input type="text" name="hoTen" required><br>
    Chọn Lớp:
    <select name="lop">
        <% for (String lop : classes) { %>
            <option value="<%= lop %>"><%= lop %></option>
        <% } %>
    </select><br>
    <input type="submit" value="Hiển Thị">
</form>

    <% 
        if(request.getMethod().equalsIgnoreCase("POST")) {
            // Lấy thông tin từ form
            String name = request.getParameter("hoTen");
            String classSelected = request.getParameter("lop");

            // Lưu tên và lớp vào session
            session.setAttribute("hoTen", name);
            session.setAttribute("lop", classSelected);

            // Chuyển hướng sang servlet `LeHoaiKhiem_TrangHienThi`
            response.sendRedirect("LeHoaiKhiem_TrangHienThi");
        }
    %>
</body>
</html>