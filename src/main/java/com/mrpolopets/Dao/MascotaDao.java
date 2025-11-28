package com.mrpolopets.Dao;

import com.mrpolopets.Model.MascotaDTO;
import java.util.List;

public interface MascotaDao {
    
    public boolean almacenarMascota(MascotaDTO mascota);
    public MascotaDTO consultarMascota(String id);
    public List<MascotaDTO> listarMascotas();
    public boolean eliminarMascota(String id);
    public boolean actualizarMascota(MascotaDTO mascota);

}