package com.mrpolopets.Dao;

import com.mrpolopets.Model.ClienteDTO;
import java.util.List;

public interface ClienteDao {
    
    public boolean almacenarCliente(ClienteDTO cliente);
    public ClienteDTO consultarCliente(String id);
    public List<ClienteDTO> listarClientes();
    public boolean eliminarCliente(String id);
    public boolean actualizarCliente(ClienteDTO cliente);
    
}
