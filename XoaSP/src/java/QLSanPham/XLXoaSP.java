package QLSanPham;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(urlPatterns = {"/XLXoaSP"})
public class XLXoaSP extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String maSP = request.getParameter("MaSP");
        boolean isDeleted = false;

        try (Connection conn = KetNoi.KNCSDL()) {
            String sql = "DELETE FROM SanPham WHERE MaSP = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, Integer.parseInt(maSP));
                int rowsAffected = pstmt.executeUpdate();
                isDeleted = (rowsAffected > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isDeleted) {
            request.setAttribute("message", "Xóa thành công mã sản phẩm: " + maSP);
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Không tìm thấy mã sản phẩm: " + maSP);
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("XoaSP.jsp").forward(request, response);
    }
}
