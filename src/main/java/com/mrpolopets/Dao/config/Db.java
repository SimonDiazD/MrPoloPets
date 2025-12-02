package com.mrpolopets.Dao.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {

    private static String driver = "com.mysql.cj.jdbc.Driver"; 
    private static String url = "jdbc:mysql://localhost:3306/mrpolopets?serverTimezone=UTC&useSSL=false"; 
    private static String user = "Simon"; 
    private static String password = "Simon549";

    private Db() {}

    public static Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la BD");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Falló la conexión SQL.");
            e.printStackTrace();
        }
        return cn;
    }
}

