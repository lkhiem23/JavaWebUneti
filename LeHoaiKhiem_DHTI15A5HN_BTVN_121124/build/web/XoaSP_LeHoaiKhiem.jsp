<%@ page import="java.sql.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }

            .container {
                width: 70%;
                margin: 0 auto;
                background-color: #ffffff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
            }

            h2 {
                color: #FF69B4;  /* Màu hồng */
                text-align: center;
                font-size: 24px;
            }

            h3 {
                color: #333;
                margin-bottom: 20px;
                font-size: 20px;
            }

            form {
                background-color: #f4f4f4;
                padding: 20px;
                border-radius: 8px;
                margin-bottom: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            form label {
                font-weight: bold;
                color: #333;
                font-size: 16px;
            }

            form input[type="text"] {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                margin-bottom: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
                font-size: 16px;
            }

            form input[type="submit"] {
                background-color: #FF69B4;  /* Màu hồng */
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
                font-size: 16px;
                width: 100%;
            }

            form input[type="submit"]:hover {
                background-color: #FF1493;  /* Màu hồng đậm */
            }

            .message {
                text-align: center;
                font-size: 18px;
                margin-bottom: 20px;
            }

            .success {
                color: green;
                font-weight: bold;
            }

            .error {
                color: red;
                font-weight: bold;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 30px;
            }

            table th, table td {
                padding: 12px;
                text-align: center;
                border: 1px solid #ddd;
                font-size: 16px;
            }

            table th {
                background-color: #FF69B4;  /* Màu hồng */
                color: white;
            }

            table tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            table tr:hover {
                background-color: #f1f1f1;
            }

        </style>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
            
            String messageType = (String) request.getAttribute("messageType"); // Lấy loại thông báo
        %>
            <p class="<%= messageType != null && messageType.equals("error") ? "error" : "success" %>">
                <%= message %>
            </p>
        <%
            }
        %>
        <h2>Xóa Sản Phẩm</h2>
        <form action="XLXoaSP_LeHoaiKhiem" method="post">
            <label>Nhập mã sản phẩm cần xóa:</label>
            <input type="text" name="MaSP" required>
            <input type="submit" value="Xóa sản phẩm">
        </form>

        <h3>Danh sách Sản Phẩm hiện tại</h3>
        <table border="1">
            <tr>
                <th>Mã SP</th>
                <th>Tên SP</th>
                <th>Loại SP</th>
                <th>Giá</th>
            </tr>
            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLSanPham", "root", "");
                    stmt = conn.createStatement();
                    String sql = "SELECT * FROM SanPham";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
            %>
                        <tr>
                            <td><%= rs.getInt("MaSP") %></td>
                            <td><%= rs.getString("TenSP") %></td>
                            <td><%= rs.getString("LoaiSP") %></td>
                            <td><%= rs.getDouble("Gia") %></td>
                        </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            %>
        </table>
    </body>
</html>