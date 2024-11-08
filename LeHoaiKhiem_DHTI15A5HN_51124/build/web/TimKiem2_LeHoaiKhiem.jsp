<%-- 
    Document   : TimKiem2_LeHoaiKhiem
    Created on : Nov 5, 2024, 1:08:19 PM
    Author     : ADMIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="ThuVien.DienThoai"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            color: #333;
        }
        h2, h3 {
            color: #0044cc;
            text-align: center;
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 5px;
            font-size: 14px;
        }
        input[type="submit"] {
            padding: 5px 10px;
            background-color: #0044cc;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #003399;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            padding: 10px;
            text-align: left;
        }
        td {
            padding: 10px;
            text-align: left;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .no-data {
            color: #cc0000;
            font-weight: bold;
            text-align: center;
        }
    </style>
    </head>
<body>
    <h2>DANH SÁCH ĐIỆN THOẠI</h2>
    <h3>LÊ HOÀI KHIÊM - DHTI15A5HN - 21103100327</h3>
    <form action="XLTimKiem2_LeHoaiKhiem" method="POST">
        Nhập mã điện thoại: <input type="text" name="ma"/>
        <input type="submit" value="Tìm kiếm"/>
    </form>
    <!-- Bảng hiển thị toàn bộ dữ liệu -->
    <table>
        <thead>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
            </tr>
        </thead>
        <tbody>
            <%
                DecimalFormat df = new DecimalFormat("#,###");
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
                        out.println("<td>" + df.format(rs.getDouble("Dongia")) + "</td>");
                        out.println("</tr>");
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            %>
        </tbody>
    </table>
        <h3>TÌM KIẾM ĐIỆN THOẠI</h3>
    <!-- Bảng hiển thị kết quả tìm kiếm -->
    <table>
        <thead>
            <tr>
                <th>Mã</th>
                <th>Tên</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
            </tr>
        </thead>
        <tbody>
            <%  
                String ma = request.getParameter("ma");
                ArrayList<DienThoai> arrHT = (ArrayList<DienThoai>) request.getAttribute("arrsv");
                if(arrHT != null && !arrHT.isEmpty()){
                    for(DienThoai dt : arrHT){
            %>
            <tr>
                <td><%= dt.getMa() %></td>
                <td><%= dt.getTen() %></td>
                <td><%= dt.getSoluong() %></td>
                <td><%= df.format(dt.getDongia()) %></td>
            </tr>
            <%
                    }
                } else if (arrHT != null) {
            %>
            <tr>
                <td colspan="4">Không tìm thấy dữ liệu của "<%= ma %>" trong bảng</td>
            </tr>
            <%
                } else {
            %>
            <tr>
            <td colspan="4">Vui lòng nhập mã để tìm kiếm!</td>
            </tr>
            <%
                }
            %>

        </tbody>
    </table>
</body>
</html>
