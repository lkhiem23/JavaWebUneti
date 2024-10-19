<%-- 
    Document   : LeHoaiKhiem_Login
    Created on : Oct 15, 2024, 9:08:05 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
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

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h1 {
            font-size: 14px;
            margin-top: -10px;
            color: #777;
        }

        h2 {
            margin-bottom: 10px;
            color: #333;
            font-size: 18px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            text-align: left;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"],
        input[type="button"] {
            width: 45%;
            padding: 10px;
            margin: 5px 2% 0;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="button"] {
            background-color: #f44336;
        }

        input[type="submit"]:hover,
        input[type="button"]:hover {
            background-color: #45a049;
        }

        input[type="button"]:hover {
            background-color: #d32f2f;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>ĐĂNG NHẬP</h2>
        <h1>Lê Hoài Khiêm - DHTI15A5HN - 21103100327</h1>
        <form action="LeHoaiKhiem_Login.jsp" method="post">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
            
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>">
            
            <input type="submit" value="Đăng nhập">
            <input type="button" value="Thoát" onclick="window.location.href='index.jsp';">
        </form>

        <% 
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username != null && password != null) {
                if (username.equals("LeHoaiKhiem") && password.equals("123")) {
                    session.setAttribute("username", username);
                    response.sendRedirect("DNThanhCong.jsp");
                } else {
                    out.println("<div class='error-message'>Bạn đã nhập sai thông tin về UserName hoặc Password</div>");
                }
            }
        %>
    </div>
</body>
</html>