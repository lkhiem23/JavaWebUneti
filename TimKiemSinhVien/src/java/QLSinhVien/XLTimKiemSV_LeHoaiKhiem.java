/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package QLSinhVien;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "XLTimKiemSV_LeHoaiKhiem", urlPatterns = {"/XLTimKiemSV_LeHoaiKhiem"})
public class XLTimKiemSV_LeHoaiKhiem extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maSV = request.getParameter("masv");
        List<Map<String, Object>> danhSachSinhVien = new ArrayList<>();
        List<Map<String, Object>> ketQuaTimKiem = new ArrayList<>();
        
        try (Connection con = KetNoi.KNCSDL()) {
            // Lấy toàn bộ danh sách sinh viên
            String queryDanhSach = "SELECT * FROM SinhVien";
            PreparedStatement psDanhSach = con.prepareStatement(queryDanhSach);
            ResultSet rsDanhSach = psDanhSach.executeQuery();
            
            while (rsDanhSach.next()) {
                Map<String, Object> sv = new HashMap<>();
                sv.put("MaSV", rsDanhSach.getString("MaSV"));
                sv.put("HoTen", rsDanhSach.getString("HoTen"));
                sv.put("NgaySinh", rsDanhSach.getDate("NgaySinh"));
                sv.put("DiemTB", rsDanhSach.getFloat("DiemTB"));
                danhSachSinhVien.add(sv);
            }

            // Nếu người dùng nhập mã sinh viên, tìm kiếm trong cơ sở dữ liệu
            if (maSV != null && !maSV.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM SinhVien WHERE MaSV = ?";
                PreparedStatement psTimKiem = con.prepareStatement(queryTimKiem);
                psTimKiem.setString(1, maSV);
                ResultSet rsTimKiem = psTimKiem.executeQuery();

                while (rsTimKiem.next()) {
                    Map<String, Object> sv = new HashMap<>();
                    sv.put("MaSV", rsTimKiem.getString("MaSV"));
                    sv.put("HoTen", rsTimKiem.getString("HoTen"));
                    sv.put("NgaySinh", rsTimKiem.getDate("NgaySinh"));
                    sv.put("DiemTB", rsTimKiem.getFloat("DiemTB"));
                    ketQuaTimKiem.add(sv);
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }

        // Gửi danh sách ban đầu và kết quả tìm kiếm sang JSP
        request.setAttribute("danhSachSinhVien", danhSachSinhVien);
        request.setAttribute("ketQuaTimKiem", ketQuaTimKiem);
        request.getRequestDispatcher("TimKiemSV.jsp").forward(request, response);
    }
}
