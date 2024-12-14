/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLThiSinh;

import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class TestKN {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlthisinh", "root", "");
            if (conn != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
