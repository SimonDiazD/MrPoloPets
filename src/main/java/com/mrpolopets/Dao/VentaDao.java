package com.mrpolopets.Dao;

import java.util.List;
import com.mrpolopets.Model.VentaDTO;

public interface VentaDao {
    boolean almacenarVenta(VentaDTO venta);
    VentaDTO consultarVenta(String id);
    List<VentaDTO> listarVentas();
    boolean eliminarVenta(String id);
    boolean actualizarVenta(VentaDTO venta);
}
