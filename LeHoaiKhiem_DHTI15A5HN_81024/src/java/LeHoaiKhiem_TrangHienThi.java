import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LeHoaiKhiem_TrangHienThi")
public class LeHoaiKhiem_TrangHienThi extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thiết lập nội dung trả về là HTML
        response.setContentType("text/html;charset=UTF-8");
        
        // Lấy session hiện tại
        HttpSession session = request.getSession();
        
        // Lấy thông tin từ session
        String name = (String) session.getAttribute("hoTen");
        String classSelected = (String) session.getAttribute("lop");
        
        // Tạo đối tượng PrintWriter để ghi HTML trả về
        PrintWriter out = response.getWriter();
        
        // Bắt đầu tạo nội dung trang HTML
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Trang Hiển Thị</title>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("<h1>LÊ HOÀI KHIÊM_DHTI15A5HN_21103100327</h1>");
        out.println("<h3>Thông tin của bạn:</h3>");
        
        // Kiểm tra nếu có thông tin trong session thì hiển thị, ngược lại thông báo lỗi
        if (name != null && classSelected != null) {
            out.println("Tên của bạn: " + name + "<br>");
            out.println("Bạn học lớp: " + classSelected + "<br>");
        } else {
            out.println("Không có thông tin để hiển thị. Vui lòng quay lại trang nhập thông tin.");
        }
        
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển phương thức POST thành GET để xử lý cùng cách
        doGet(request, response);
    }
}