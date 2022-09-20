/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasirapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


/**
 *
 * @author NITRO 5
 */
public class Koneksi {
    
    
    private static Connection konek;
    
    
    public static Connection getKoneksi() throws SQLException{
        
        try {

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            konek = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasir_app", "root", "");
            System.out.println("Koneksi berhasil"); 
            
        }catch(SQLException e){
            System.out.println("Koneksi gagal" + e.getMessage());
        }
        return konek;
    }
    
    /*
    public static void main(String[] args) throws SQLException{
        getKoneksi();
    }
    */
}
