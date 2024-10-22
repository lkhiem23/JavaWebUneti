
import java.sql.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class KetNoi1 {
    public static Connection KNCSDL() throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsv_java","root","");
        return c;
    }
}
