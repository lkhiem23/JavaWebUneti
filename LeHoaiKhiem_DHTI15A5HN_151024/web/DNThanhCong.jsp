<%-- 
    Document   : DNThanhCong
    Created on : Oct 15, 2024, 9:08:38 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <title>Đăng nhập thành công</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .success-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
        }

        h2 {
            color: #333;
            margin-bottom: 10px;
        }

        p {
            color: #555;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>Chào mừng</h2>
        <p>
            <% 
                String username = (String) session.getAttribute("username");
                if (username != null) {
                    out.println(username);
                } else {
                    out.println("Người dùng không xác định");
                }
            %>
        </p>
        <p>Bạn đã đăng nhập thành công!</p>
    </div>
</body>
</html>
