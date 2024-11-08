package ThuVien;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import ThuVien.DienThoai;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/XLTimKiem2_LeHoaiKhiem"})
public class XLTimKiem2_LeHoaiKhiem extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ma = request.getParameter("ma");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
     
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qldt", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dienthoai WHERE Ma = ?")) {

            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();

            ArrayList<DienThoai> arr = new ArrayList<>();
            while (rs.next()) {
                DienThoai dt = new DienThoai();
                dt.setMa(rs.getString("Ma"));
                dt.setTen(rs.getString("Ten"));
                dt.setSoluong(rs.getInt("Soluong"));
                dt.setDongia(rs.getDouble("Dongia"));
                arr.add(dt);
            }

            request.setAttribute("arrsv", arr);
            request.getRequestDispatcher("TimKiem2_LeHoaiKhiem.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(XLTimKiem2_LeHoaiKhiem.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
