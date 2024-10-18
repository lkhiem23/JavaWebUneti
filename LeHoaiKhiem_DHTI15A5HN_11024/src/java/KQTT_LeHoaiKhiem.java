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

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/KQTT_LeHoaiKhiem"})
public class KQTT_LeHoaiKhiem extends HttpServlet {
private static final long serialVersionUID = 1L;

    public KQTT_LeHoaiKhiem() {
        super();
    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số từ form
        String aStr = request.getParameter("a");
        String bStr = request.getParameter("b");
        String operation = request.getParameter("operation");

        String result = "";

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            switch (operation) {
                case "cong":
                    result = String.valueOf(a + b);
                    break;
                case "tru":
                    result = String.valueOf(a - b);
                    break;
                case "nhan":
                    result = String.valueOf(a * b);
                    break;
                case "chia":
                    if (b != 0) {
                        result = String.valueOf(a / b);
                    } else {
                        result = "Không thể chia cho 0!";
                    }
                    break;
                default:
                    result = "Phép toán không hợp lệ!";
                    break;
            }
        } catch (NumberFormatException e) {
            result = "Dữ liệu nhập vào không hợp lệ!";
        }

        // Đặt kết quả vào request attribute
        request.setAttribute("result", result);

        // Chuyển tiếp lại trang JSP
        request.getRequestDispatcher("TinhToan_jsp.jsp").forward(request, response);
    }
}