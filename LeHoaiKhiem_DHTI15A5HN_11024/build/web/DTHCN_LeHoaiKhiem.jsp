<%-- 
    Document   : DTHCN_LeHoaiKhiem
    Created on : Oct 1, 2024, 10:08:27 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tính diện tích hình chữ nhật</title>
        <h1>Tính diện tích hình chữ nhật</h1>
        <h3>Lê Hoài Khiêm - DHTI15A5HN - 21103100327</h3>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            
            h3{
                color: red;
            }
            
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 300px;
            }

            label {
                display: block;
                margin-bottom: 10px;
                color: #555;
            }

            input[type="number"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
            }

            button:hover {
                background-color: #45a049;
            }

            p {
                font-size: 18px;
                color: #333;
            }

            p.error {
                color: red;
            }

            .result {
                margin-top: 20px;
                font-size: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        
        
        <!-- Form nhập chiều dài và chiều rộng -->
        <form method="POST">
            <label for="length">Chiều dài:</label>
            <input type="number" id="length" name="length" step="any" required>
            
            <label for="width">Chiều rộng:</label>
            <input type="number" id="width" name="width" step="any" required>

            <!-- Nút tính diện tích -->
            <button type="submit">Tính diện tích</button>
        </form>

        <%
        // Lấy các tham số từ form
        String lengthStr = request.getParameter("length");
        String widthStr = request.getParameter("width");

        if (lengthStr != null && widthStr != null) {
            // Chuyển đổi giá trị từ chuỗi thành số
            double length = Double.parseDouble(lengthStr);
            double width = Double.parseDouble(widthStr);

            if (length > 0 && width > 0) {
                // Tính diện tích hình chữ nhật
                double area = length * width;
        %>
                <p class="result">Diện tích hình chữ nhật là: <%= area %></p>
        <%
            } else {
                // Thông báo nếu giá trị nhập không hợp lệ
                out.println("<p class='error'>Chiều dài và chiều rộng phải lớn hơn 0!</p>");
            }
        }
        %>

    </body>
</html>
