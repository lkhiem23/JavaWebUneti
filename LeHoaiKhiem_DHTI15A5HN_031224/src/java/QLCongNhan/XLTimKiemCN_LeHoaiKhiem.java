/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package QLCongNhan;

import java.io.IOException;
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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "XLTimKiemCN_LeHoaiKhiem", urlPatterns = {"/XLTimKiemCN_LeHoaiKhiem"})
public class XLTimKiemCN_LeHoaiKhiem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maCN = request.getParameter("macn"); // Lấy mã công nhân từ request
        List<Map<String, Object>> danhSachCongNhan = new ArrayList<>();
        List<Map<String, Object>> ketQuaTimKiem = new ArrayList<>();

        try (Connection con = KetNoi.KNCSDL()) {
            // Lấy toàn bộ danh sách công nhân
            String queryDanhSach = "SELECT * FROM CongNhan";
            PreparedStatement psDanhSach = con.prepareStatement(queryDanhSach);
            ResultSet rsDanhSach = psDanhSach.executeQuery();

            while (rsDanhSach.next()) {
                Map<String, Object> cn = new HashMap<>();
                cn.put("MaCN", rsDanhSach.getString("MaCN"));
                cn.put("HoTen", rsDanhSach.getString("HoTen"));
                cn.put("GioiTinh", rsDanhSach.getString("GioiTinh"));
                cn.put("SoGioLamVuot", rsDanhSach.getInt("SoGioLamVuot"));
                danhSachCongNhan.add(cn);
            }

            // Nếu người dùng nhập mã công nhân, tìm kiếm trong cơ sở dữ liệu
            if (maCN != null && !maCN.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM CongNhan WHERE HoTen = ?";
                PreparedStatement psTimKiem = con.prepareStatement(queryTimKiem);
                psTimKiem.setString(1, maCN);
                ResultSet rsTimKiem = psTimKiem.executeQuery();

                while (rsTimKiem.next()) {
                    Map<String, Object> cn = new HashMap<>();
                    cn.put("MaCN", rsTimKiem.getString("MaCN"));
                    cn.put("HoTen", rsTimKiem.getString("HoTen"));
                    cn.put("GioiTinh", rsTimKiem.getString("GioiTinh"));
                    cn.put("SoGioLamVuot", rsTimKiem.getInt("SoGioLamVuot"));
                    ketQuaTimKiem.add(cn);
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }

        // Gửi danh sách ban đầu và kết quả tìm kiếm sang JSP
        request.setAttribute("danhSachCongNhan", danhSachCongNhan);
        request.setAttribute("ketQuaTimKiem", ketQuaTimKiem);
        request.getRequestDispatcher("TimKiemCN_LeHoaiKhiem.jsp").forward(request, response);
    }
}
