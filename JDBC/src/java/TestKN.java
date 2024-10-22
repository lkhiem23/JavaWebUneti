/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/TestKN"})
public class TestKN extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Gọi phương thức để kết nối đến cơ sở dữ liệu
            Connection kn = KetNoi.KNCSDL();
            if (kn == null) {
                out.println("<html><body>");
                out.println("<h1>Kết nối thất bại!</h1>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Lê Hoài Khiêm - DHTI15A5HN - 21103100327</h2>");
                out.println("<h3>Danh sách Sinh Viên: </h3>");
                
                // Tạo câu lệnh SQL để truy vấn dữ liệu từ bảng sinhvien
                String sql = "SELECT MaSV, TenSV FROM sinhvien";
                Statement statement = kn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                
                // Hiển thị dữ liệu dưới dạng bảng HTML
                out.println("<table border='1'>");
                out.println("<tr><th>Mã Sinh Viên</th><th>Họ Tên Sinh Viên</th></tr>");
                
                // Duyệt qua từng dòng kết quả
                while (rs.next()) {
                    String MaSV = rs.getString("MaSV");
                    String TenSV = rs.getString("TenSV");
                    out.println("<tr>");
                    out.println("<td>" + MaSV + "</td>");
                    out.println("<td>" + TenSV + "</td>");
                    out.println("</tr>");
                }               
                out.println("</table>");
                out.println("</body></html>");
                
                // Đóng kết nối và các tài nguyên
                rs.close();
                statement.close();
                kn.close();
            }
        } catch (SQLException ex) {
            out.println("<html><body>");
            out.println("<h1>Lỗi kết nối hoặc truy vấn: " + ex.getMessage() + "</h1>");
            out.println("</body></html>");
            Logger.getLogger(TestKN.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close(); // Đảm bảo đóng PrintWriter
        }
        
        
        
         
            /*
            try (PrintWriter out = response.getWriter()) {
            // Gọi phương thức để kết nối đến cơ sở dữ liệu
            Connection kn = KetNoi1.KNCSDL();
            
            // Kiểm tra kết nối và hiển thị thông báo thích hợp
            if (kn == null) {
            out.println("<html><body>");
            out.println("<h1>Kết nối thất bại</h1>");
            out.println("</body></html>");
            } else {
            out.println("<html><body>");
            out.println("<h1>Kết nối thành công</h1>");
            out.println("</body></html>");
            kn.close(); // Đóng kết nối sau khi sử dụng
            }
            } catch (SQLException e) {
            // Bắt và hiển thị lỗi nếu có
            try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Lỗi kết nối: " + e.getMessage() + "</h1>");
            out.println("</body></html>");
            }
            }
            
            */
}
}