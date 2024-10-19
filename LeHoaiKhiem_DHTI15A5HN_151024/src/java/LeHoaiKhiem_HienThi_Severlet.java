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
@WebServlet(urlPatterns = {"/LeHoaiKhiem_HienThi_Severlet"})
public class LeHoaiKhiem_HienThi_Severlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        
        if (name == null || name.isEmpty() || age == null || age.isEmpty()) {
            response.sendRedirect("LeHoaiKhiem_Loi.jsp");
        } else {
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("age", age);
            response.sendRedirect("LeHoaiKhiem_HienThi.jsp");
        }
    }

}
