package ThuVien;

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
import java.math.BigDecimal;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/XLTimKiem_LeHoaiKhiem"})
public class XLTimKiem_LeHoaiKhiem extends HttpServlet {
     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ma = request.getParameter("ma");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qldt", "root", "");

            String sql = "SELECT * FROM dienthoai WHERE Ma = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                out.println("<tr>");
                out.println("<h2>Kết quả tìm kiếm:</h2>");
                out.println("<td> <p>Mã: " + rs.getString("Ma") + "</p> </td>");
                out.println("<td> <p>Tên: " + rs.getString("Ten") + "</p> </td>");
                out.println("<td> <p>Số lượng: " + rs.getInt("Soluong") + "</p> </td>");
                out.println("</tr>");
                // Sử dụng BigDecimal để đảm bảo độ chính xác và tránh hiển thị dưới dạng khoa học
                BigDecimal dongia = rs.getBigDecimal("Dongia");
                out.println("<p>Đơn giá: " + dongia.toPlainString() + "</p>");
            } else {
                out.println("<h2>Không có mã điện thoại cần tìm</h2>");
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Warniggg !!!!!! Cảnh báo</h3>");
            out.println("<p>Lỗi khi tìm kiếm dữ liệu.</p>");
        } finally {
            out.close();
        }
     }    
}
