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
@WebServlet(urlPatterns = {"/LeHoaiKhiem"})
public class LeHoaiKhiem extends HttpServlet {
 
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String aStr = request.getParameter("a");
        String bStr = request.getParameter("b");

        PrintWriter out = response.getWriter();

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            out.println("<html><body>");
            out.println("<h2>Kết quả giải phương trình bậc nhất:</h2>");

            if (a == 0) {
                if (b == 0) {
                    out.println("<p>Phương trình có vô số nghiệm.</p>");
                } else {
                    out.println("<p>Phương trình vô nghiệm.</p>");
                }
            } else {
                double x = -b / a;
                out.println("<p>Nghiệm của phương trình là: x = " + x + "</p>");
            }

            out.println("</body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<h2>Lỗi: Vui lòng nhập số hợp lệ cho a và b.</h2>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}
