/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/DangNhap_Khiem"})
public class DangNhap_Khiem extends HttpServlet {
private static final long serialVersionUID = 1L;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting username and password from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Predefined credentials
        String correctUsername = "admin";
        String correctPassword = "123";

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            // Correct username and password
            response.getWriter().println("<h3>Chào " + username + ", bạn đã đăng nhập thành công!</h3>");
        } else if (!username.equals(correctUsername) && !password.equals(correctPassword)) {
            // Both username and password are incorrect
            response.getWriter().println("<h3>Bạn đã nhập sai cả username và password!</h3>");
        } else if (!username.equals(correctUsername)) {
            // Incorrect username
            response.getWriter().println("<h3>Tên đăng nhập không đúng!</h3>");
        } else if (!password.equals(correctPassword)) {
            // Incorrect password
            response.getWriter().println("<h3>Mật khẩu không đúng!</h3>");
        }
    }

}
