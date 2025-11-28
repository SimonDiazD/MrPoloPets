package com.mrpolopets.Implements;
import com.mrpolopets.Model.MascotaDTO;
import com.mrpolopets.Dao.*;
import com.mrpolopets.Dao.config.*; // Asegúrate que este import coincida con tu clase de conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAOImpl implements MascotaDao {

    @Override
    public List<MascotaDTO> listarMascotas() {
        List<MascotaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";

        // Usamos try-with-resources para cerrar la conexión automáticamente
        try (Connection con = Db.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Creamos el objeto usando los datos de la BD
                MascotaDTO mascota = new MascotaDTO(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getString("vacunas"),
                    rs.getString("tipo"),
                    rs.getString("peso"),
                    rs.getBoolean("esta_adoptado"), // MySQL convierte TINYINT a boolean automáticamente
                    rs.getString("lugar_origen"),
                    rs.getString("genero"),
                    rs.getString("fecha_ingreso")
                );
                lista.add(mascota);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar mascotas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODOS PENDIENTES (Dejar retornando false/null por ahora) ---
    // Implementaremos estos cuando hagamos el módulo de crear/editar
    @Override
    public boolean almacenarMascota(MascotaDTO mascota) { return false; }
    @Override
    public MascotaDTO consultarMascota(String id) { return null; }
    @Override
    public boolean eliminarMascota(String id) { return false; }
    @Override
    public boolean actualizarMascota(MascotaDTO mascota) { return false; }
}