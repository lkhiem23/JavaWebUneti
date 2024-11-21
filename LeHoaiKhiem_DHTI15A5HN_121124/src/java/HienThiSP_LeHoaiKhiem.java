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
import java.sql.*;
/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/HienThiSP_LeHoaiKhiem"})
public class HienThiSP_LeHoaiKhiem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String maSP = request.getParameter("maSP");

        String url = "jdbc:mysql://localhost:3306/QLSanPham";
        String user = "root"; 
        String password = ""; 
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            // Kiểm tra xem sản phẩm có tồn tại không
            String checkQuery = "SELECT * FROM SanPham WHERE MaSP = ?";
            ps = conn.prepareStatement(checkQuery);
            ps.setInt(1, Integer.parseInt(maSP));
            rs = ps.executeQuery();

            if (rs.next()) {
                // Xóa sản phẩm nếu tồn tại
                String deleteQuery = "DELETE FROM SanPham WHERE MaSP = ?";
                ps = conn.prepareStatement(deleteQuery);
                ps.setInt(1, Integer.parseInt(maSP));
                ps.executeUpdate();

                out.println("<h2>Xóa thành công sản phẩm có mã: " + maSP + "</h2>");
            } else {
                out.println("<h2>Không có sản phẩm nào với mã " + maSP + " để xóa</h2>");
            }
            out.println("<html><head><title>Kết quả xóa sản phẩm</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; margin: 20px; }");
            out.println("h2 { color: #FF69B4; }");  // Màu hồng cho tiêu đề
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 10px; text-align: left; }");
            out.println("th { background-color: #FF69B4; color: white; }");  // Màu hồng cho tiêu đề bảng
            out.println(".message { font-size: 18px; color: #FF69B4; margin-top: 20px; }");  // Màu hồng cho thông báo
            out.println("</style></head><body>");

            
            // Hiển thị lại danh sách sản phẩm sau khi xóa
            out.println("<h2>Danh sách sản phẩm</h2>");
            out.println("<table border='1'><tr><th>Mã SP</th><th>Tên SP</th><th>Loại SP</th><th>Giá</th></tr>");
            
            String query = "SELECT * FROM SanPham";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("MaSP") + "</td>");
                out.println("<td>" + rs.getString("TenSP") + "</td>");
                out.println("<td>" + rs.getString("LoaiSP") + "</td>");
                out.println("<td>" + rs.getDouble("Gia") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Lỗi: " + e.getMessage() + "</h2>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
