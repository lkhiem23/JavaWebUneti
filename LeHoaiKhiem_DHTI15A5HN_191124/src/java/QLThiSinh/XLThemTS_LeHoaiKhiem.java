/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package QLThiSinh;

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
@WebServlet(name = "XLThemTS_LeHoaiKhiem", urlPatterns = {"/XLThemTS_LeHoaiKhiem"})

public class XLThemTS_LeHoaiKhiem extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Lấy thông tin từ form
            String sbd = request.getParameter("sbd");
            String hoten = request.getParameter("hoten");
            String diemToanStr = request.getParameter("diemtoan");
            String diemVanStr = request.getParameter("diemvan");

            // Kiểm tra giá trị nhập vào
            if (sbd == null || hoten == null || diemToanStr == null || diemVanStr == null ||
                    sbd.isEmpty() || hoten.isEmpty() || diemToanStr.isEmpty() || diemVanStr.isEmpty()) {
                response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: Vui lòng điền đầy đủ thông tin!");
                return;
            }

            try {
                float diemToan = Float.parseFloat(diemToanStr);
                float diemVan = Float.parseFloat(diemVanStr);

                // Kết nối cơ sở dữ liệu
                Connection conn = KetNoi.KNCSDL();

                // Kiểm tra trùng lặp SBD
                String checkSql = "SELECT * FROM thisinh WHERE SBD = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, sbd);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    // Nếu đã tồn tại, trả về thông báo lỗi
                    response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: Số báo danh đã tồn tại!");
                } else {
                    // Thêm thí sinh mới
                    String insertSql = "INSERT INTO thisinh (SBD, HoTen, DiemToan, DiemVan) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                    insertStmt.setString(1, sbd);
                    insertStmt.setString(2, hoten);
                    insertStmt.setFloat(3, diemToan);
                    insertStmt.setFloat(4, diemVan);

                    int rowsInserted = insertStmt.executeUpdate();
                    if (rowsInserted > 0) {
                        // Thêm thành công
                        response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Thêm thành công số báo danh " + sbd);
                    } else {
                        response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: Không thể thêm thí sinh!");
                    }
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(out);
                response.sendRedirect("ThemTS_LeHoaiKhiem.jsp?message=Lỗi: " + e.getMessage());
            }
        }
    }
}
