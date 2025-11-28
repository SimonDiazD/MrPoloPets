package com.mrpolopets.Implements;
import com.mrpolopets.Model.ConsultaDTO;
import com.mrpolopets.Dao.*;
import com.mrpolopets.Dao.config.*; // Asegúrate que este import coincida con tu clase de conexión

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAOImpl implements ConsultaDao {

    @Override
    public List<ConsultaDTO> listarConsultas() {
        List<ConsultaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM consultas";

        // Usamos try-with-resources para cerrar la conexión automáticamente
        try (Connection con = Db.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Creamos el objeto usando los datos de la BD
                ConsultaDTO consulta = new ConsultaDTO(
                    rs.getString("id_consulta"),
                    rs.getString("fecha"),
                    rs.getString("sintomas"),
                    rs.getString("tratamiento"),
                    rs.getString("id_mascota")
                );
                lista.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar consultas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODOS PENDIENTES (Dejar retornando false/null por ahora) ---
    // Implementaremos estos cuando hagamos el módulo de crear/editar
    @Override
    public boolean almacenarConsulta(ConsultaDTO consulta) { return false; }
    @Override
    public ConsultaDTO consultarConsulta(String id) { return null; }
    @Override
    public boolean eliminarConsulta(String id) { return false; }
    @Override
    public boolean actualizarConsulta(ConsultaDTO consulta) { return false; }
}
