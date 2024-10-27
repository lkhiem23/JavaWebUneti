/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        
        Connection kn = KetNoi1.KNCSDL();
        
        if(kn == null){
            System.out.println("Ket noi that bai");
        }else{
            System.out.println("Ket noi thanh cong");
        }
        
    }
}
