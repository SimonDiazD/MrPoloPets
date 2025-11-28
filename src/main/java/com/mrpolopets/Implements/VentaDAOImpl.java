package com.mrpolopets.Implements;
import com.mrpolopets.Model.VentaDTO;
import com.mrpolopets.Dao.*;
import com.mrpolopets.Dao.config.*; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDao {

    @Override
    public List<VentaDTO> listarVentas() {
        List<VentaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ventas";

        // Usamos try-with-resources para cerrar la conexión automáticamente
        try (Connection con = Db.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Creamos el objeto usando los datos de la BD
                VentaDTO Venta = new VentaDTO(
                    rs.getString("id_venta"),
                    rs.getString("fecha"),
                    rs.getDouble("valor_venta"),
                    rs.getString("id_cliente"),
                    rs.getString("id_mascota")
                );
                lista.add(Venta);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Ventas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODOS PENDIENTES (Dejar retornando false/null por ahora) ---
    // Implementaremos estos cuando hagamos el módulo de crear/editar
    @Override
    public boolean almacenarVenta(VentaDTO Venta) { return false; }
    @Override
    public VentaDTO consultarVenta(String id) { return null; }
    @Override
    public boolean eliminarVenta(String id) { return false; }
    @Override
    public boolean actualizarVenta(VentaDTO Venta) { return false; }
}
