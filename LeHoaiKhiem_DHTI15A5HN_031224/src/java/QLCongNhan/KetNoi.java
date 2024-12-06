package QLCongNhan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KetNoi {
    public static Connection KNCSDL() throws SQLException {
        try {
            // Nạp trình điều khiển
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlcongnhan", "root", "");
            return c;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
