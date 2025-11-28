package com.mrpolopets.Dao;

import com.mrpolopets.Model.ConsultaDTO;
import java.util.List;

public interface ConsultaDao {
   
    boolean almacenarConsulta(ConsultaDTO consulta);
    ConsultaDTO consultarConsulta(String id);
    List<ConsultaDTO> listarConsultas();
    boolean eliminarConsulta(String id);
    boolean actualizarConsulta(ConsultaDTO consulta);
    
}

