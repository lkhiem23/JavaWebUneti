/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package classes;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
@WebServlet(name = "HTServlet_LeHoaiKhiem", urlPatterns = {"/HTServlet_LeHoaiKhiem"})
public class HTServlet_LeHoaiKhiem extends HttpServlet {
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/qlxemay";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";
    private static final String INSERT_XEMAY_SQL = "INSERT INTO xemay (Ma, Ten, HangSX, Mau) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_XEMAY_SQL = "SELECT * FROM xemay";

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    String ten = request.getParameter("ten");
    String hangSX = request.getParameter("hangSX");
    String mau = request.getParameter("mau");
    int ma = Integer.parseInt(request.getParameter("ma")); // Lấy mã từ form

    try (PrintWriter out = response.getWriter()) {
        out.println("<html><body>");
        out.println("<h2>Đang thực hiện thêm xe máy...</h2>");
        out.println("<p>Tham số đầu vào: Mã = " + ma + ", Tên = " + ten + ", Hãng SX = " + hangSX + ", Màu = " + mau + "</p>");

        // Tải driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("<p>Driver đã được tải thành công.</p>");
        } catch (ClassNotFoundException e) {
            out.println("<p>Lỗi tải driver JDBC: " + e.getMessage() + "</p>");
            return;
        }

        // Kết nối cơ sở dữ liệu
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            out.println("<p>Kết nối thành công đến cơ sở dữ liệu.</p>");

            // Thêm xe máy mới vào cơ sở dữ liệu
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_XEMAY_SQL);
            insertStatement.setInt(1, ma); // Thêm mã vào câu lệnh INSERT
            insertStatement.setString(2, ten);
            insertStatement.setString(3, hangSX);
            insertStatement.setString(4, mau);
            int rowsInserted = insertStatement.executeUpdate();
            insertStatement.close();
            out.println("<p>Đã thêm " + rowsInserted + " bản ghi vào cơ sở dữ liệu.</p>");

            // Lấy toàn bộ danh sách xe máy sau khi thêm
            List<Xemay> xemayList = new ArrayList<>();
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_ALL_XEMAY_SQL);
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                int maXe = rs.getInt("Ma");
                String tenXe = rs.getString("Ten");
                String hangSXxe = rs.getString("HangSX");
                String mauXe = rs.getString("Mau");
                xemayList.add(new Xemay(maXe, tenXe, hangSXxe, mauXe));
            }

            rs.close();
            selectStatement.close();

            // Hiển thị danh sách xe máy
            out.println("<h2>Danh sách xe máy sau khi thêm</h2>");
            out.println("<table border='1'><tr><th>Mã</th><th>Tên</th><th>Hãng Sản Xuất</th><th>Màu</th></tr>");

            for (Xemay xe : xemayList) {
                out.println("<tr><td>" + xe.getMa() + "</td><td>" + xe.getTen() + "</td><td>" + xe.getHangSX() + "</td><td>" + xe.getMau() + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><a href='Them_LeHoaiKhiem.jsp'>Thêm xe máy mới</a>");
            out.println("</body></html>");
        } catch (SQLException e) {
            out.println("<p>Lỗi kết nối cơ sở dữ liệu hoặc truy vấn SQL: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
}