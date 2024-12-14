package QLThiSinh;


import java.util.logging.Level;
import java.util.logging.Logger;
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlthisinh","root","");
            return c;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

