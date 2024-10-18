<%-- 
    Document   : TinhToan_jsp
    Created on : Oct 1, 2024, 10:40:08 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phép toán với 2 số</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column; /* Đảm bảo nội dung theo chiều dọc */
            }

            h1 {
                color: #333;
                margin-bottom: 20px;
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 420px;
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

            .button-group {
                display: flex;
                justify-content: space-between;
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                flex: 1;
                margin-right: 5px;
            }

            button:last-child {
                margin-right: 0;
            }

            button:hover {
                background-color: #45a049;
            }

            p {
                font-size: 18px;
                color: #333;
                text-align: center;
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
        <script>
            function resetForm() {
                // Reset các trường nhập liệu và kết quả
                document.getElementById("a").value = "";
                document.getElementById("b").value = "";
                document.getElementById("result").value = "";
            }
        </script>
    </head>
    <body>
        <h1>Nhập hai số và chọn phép toán</h1>
        
        <!-- Form gửi dữ liệu đến Servlet -->
        <form action="KQTT_LeHoaiKhiem" method="POST">
            <p>FORM TÍNH TOÁN</p>
            <p>Lê Hoài Khiêm _ DHTI15A5HN _ 21103100327</p>
            
            <label for="a">a:</label>
            <input type="number" id="a" name="a" step="any" required>
            
            <label for="b">b:</label>
            <input type="number" id="b" name="b" step="any" required>
            <label for="result">Kết quả:</label>
            <input type="number" id="result" name="result" step="any" readonly 
                   value="<%= request.getAttribute("result") != null ? request.getAttribute("result").toString() : "" %>">

            <!-- Các nút chọn phép toán -->
            <div class="button-group">
                <button type="submit" name="operation" value="cong">Cộng</button>
                <button type="submit" name="operation" value="tru">Trừ</button>
                <button type="submit" name="operation" value="nhan">Nhân</button>
                <button type="submit" name="operation" value="chia">Chia</button>
                <button type="button" onclick="resetForm()">Reset</button>
            </div>
            

        </form>


    </body>
</html>
