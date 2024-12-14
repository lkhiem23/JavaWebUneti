package QLThiSinh;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(name = "XLThemTS_LeHoaiKhiem", urlPatterns = {"/XLThemTS_LeHoaiKhiem"})
public class XLThemTS_LeHoaiKhiem extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sbd = request.getParameter("sbd");
        String hoten = request.getParameter("hoten");
        String diemToanStr = request.getParameter("diemtoan");
        String diemVanStr = request.getParameter("diemvan");

        if (sbd == null || hoten == null || diemToanStr == null || diemVanStr == null ||
            sbd.isEmpty() || hoten.isEmpty() || diemToanStr.isEmpty() || diemVanStr.isEmpty()) {
            response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: Điền đầy đủ thông tin!");
            return;
        }

         try (Connection conn = KetNoi.KNCSDL()) {
            PreparedStatement checkStmt = conn.prepareStatement("SELECT 1 FROM thisinh WHERE SBD = ?");
            checkStmt.setString(1, sbd);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: Số báo danh đã tồn tại!");
            } else {
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO thisinh (SBD, HoTen, DiemToan, DiemVan) VALUES (?, ?, ?, ?)");
                stmt.setString(1, sbd);
                stmt.setString(2, hoten);
                stmt.setFloat(3, Float.parseFloat(diemToanStr));
                stmt.setFloat(4, Float.parseFloat(diemVanStr));
                
                String message = stmt.executeUpdate() > 0 ?
                    "Thêm thành công số báo danh " + sbd : "Lỗi: Không thể thêm thí sinh!";
                response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=" + message);
            }
        } catch (Exception e) {
            response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: " + e.getMessage());
        }
    }
}
