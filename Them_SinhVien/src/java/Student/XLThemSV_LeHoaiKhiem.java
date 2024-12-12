package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/XLThemSV_LeHoaiKhiem"})
public class XLThemSV_LeHoaiKhiem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thiết lập mã hóa UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String mssv = request.getParameter("mssv");
        String hoten = request.getParameter("hoten");
        String lop = request.getParameter("lop");
        String diemtbStr = request.getParameter("diemtb");

        double diemtb = 0;
        try {
            diemtb = Double.parseDouble(diemtbStr);
        } catch (NumberFormatException e) {
            // Đặt thông báo lỗi vào request attribute và chuyển tiếp về trang JSP
            request.setAttribute("message", "Điểm không hợp lệ");
            request.getRequestDispatcher("ThemSV_LeHoaiKhiem.jsp").forward(request, response);
            return;
        }

        try (Connection conn = KetNoi.KNCSDL()) {
            if (conn != null) {
        String checkSql = "SELECT * FROM Student WHERE MSSV = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, mssv);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    request.setAttribute("message", "Thêm không thành công do trùng MSSV " + mssv);
                } else {
                    String insertSql = "INSERT INTO Student (MSSV, HoTen, Lop, DiemTB) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, mssv);
                        insertStmt.setString(2, hoten);
                        insertStmt.setString(3, lop);
                        insertStmt.setDouble(4, diemtb);
                        int rowsInserted = insertStmt.executeUpdate();
                        request.setAttribute("message", rowsInserted > 0 
                            ? "Thêm thành công MSSV " + mssv 
                            : "Thêm không thành công");
                    }
                }
            }
        }
    }
} catch (SQLException e) {
    request.setAttribute("message", "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(XLThemSV_LeHoaiKhiem.class.getName()).log(Level.SEVERE, null, ex);
}
        // Chuyển tiếp về trang JSP với thông báo
        request.getRequestDispatcher("ThemSV_LeHoaiKhiem.jsp").forward(request, response);
    }
}
