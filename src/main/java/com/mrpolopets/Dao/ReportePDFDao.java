package com.mrpolopets.Dao;

import com.mrpolopets.Model.MascotaDTO;
import com.mrpolopets.Model.VentaDTO;
import com.mrpolopets.Model.ClienteDTO;
import com.mrpolopets.Model.ConsultaDTO;
import java.io.OutputStream;
import java.util.List;

public interface ReportePDFDao {
    
    public void generarReporteMascotas(List<MascotaDTO> lista, OutputStream out);
    public void generarReporteVentas(List<VentaDTO> lista, OutputStream out);
    public void generarReporteConsultas(List<ConsultaDTO> lista, OutputStream out);
    public void generarReporteClientes(List<ClienteDTO> lista, OutputStream out);

}