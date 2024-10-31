import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class KetNoi {
    public static Connection KNCSDL() throws SQLException {
       try {
            // Nạp trình điều khiển
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver không tìm thấy", e);
        }
        
        // Kết nối đến cơ sở dữ liệu
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsach","root","");
    }
}
