package com.mrpolopets.Dao.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {

// Eliminamos 'private static Connection connection' para evitar problemas de concurrencia (leer nota abajo)

    // ACTUALIZACIÓN: En MySQL 8+ se usa "cj"
    private static String driver = "com.mysql.cj.jdbc.Driver"; 
    // RECOMENDACIÓN: Agrega parámetros para evitar errores de zona horaria y SSL
    private static String url = "jdbc:mysql://localhost:3306/mrpolopets?serverTimezone=UTC&useSSL=false"; 
    private static String user = "Simon"; 
    private static String password = "Simon549";

    // Constructor privado para evitar instancias
    private Db() {}

    public static Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName(driver);
            // No necesitas hacer casting (Connection) porque DriverManager ya devuelve la interfaz correcta
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

