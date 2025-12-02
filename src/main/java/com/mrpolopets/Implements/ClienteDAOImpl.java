package com.mrpolopets.Implements;

import com.mrpolopets.Model.ClienteDTO;
import com.mrpolopets.Dao.*;
import com.mrpolopets.Dao.config.*; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDao {

    @Override
    public List<ClienteDTO> listarClientes() {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        try (Connection con = Db.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Creamos el objeto usando los datos de la BD
                ClienteDTO Cliente = new ClienteDTO(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("direccion_contacto"),
                    rs.getString("numero_contacto"),
                    rs.getString("id_mascotas_cargo")
                );
                lista.add(Cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODOS PENDIENTES (Dejar retornando false/null por ahora) ---
    // Implementaremos estos cuando hagamos el módulo de crear/editar
    @Override
    public boolean almacenarCliente(ClienteDTO Cliente) { return false; }
    @Override
    public ClienteDTO consultarCliente(String id) { return null; }
    @Override
    public boolean eliminarCliente(String id) { return false; }
    @Override
    public boolean actualizarCliente(ClienteDTO Cliente) { return false; }
}
