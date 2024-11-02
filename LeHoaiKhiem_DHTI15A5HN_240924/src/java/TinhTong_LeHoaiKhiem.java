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
@WebServlet(urlPatterns = {"/TinhTong"})
public class TinhTong_LeHoaiKhiem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        if (num1Str == null || num1Str.isEmpty() || num2Str == null || num2Str.isEmpty()) {
            out.println("<html><body>");
            out.println("<h2>Lỗi: Vui lòng nhập cả hai số.</h2>");
            out.println("</body></html>");
        } else {
            try {
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);
                int sum = num1 + num2;

                out.println("<html><body>");
                out.print("<h2>Lê Hoài Khiêm - DHTI15A5HN - 21103100327 </h2>");
                out.println("<h2>Tổng của hai số: " + num1 + " và " + num2 + " là: " + sum + "</h2>");
                out.println("</body></html>");
            } catch (NumberFormatException e) {
                out.println("<html><body>");
                out.println("<h2>Lỗi: Vui lòng nhập số hợp lệ.</h2>");
                out.println("</body></html>");
            }
        }
    }
}
