package com.mrpolopets.Controller;


import com.mrpolopets.Implements.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reportes/pdf")
public class ReportePDFServlet extends HttpServlet {

    // Instancias de tus DAOs
    private MascotaDAOImpl mascotaDAO = new MascotaDAOImpl();
    private VentaDAOImpl ventaDAO = new VentaDAOImpl();
    private ReportePDFDAOImpl reporteDAO = new ReportePDFDAOImpl();
    private ConsultaDAOImpl consultaDAO = new ConsultaDAOImpl();
    private ClienteDAOImpl clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. Obtener qué tipo de reporte quiere el usuario
        String tipo = req.getParameter("tipo"); // ejemplo: ?tipo=mascotas

        if (tipo == null) tipo = "";

        // 2. Configurar la respuesta para que el navegador sepa que es un PDF
        resp.setContentType("application/pdf");
        // Esta línea hace que se descargue con nombre. Si la quitas, se abre en el navegador.
        resp.setHeader("Content-Disposition", "attachment; filename=reporte_" + tipo + ".pdf");

        // 3. Generar el PDF según el tipo
        try {
            switch (tipo) {
                case "mascota":
                    reporteDAO.generarReporteMascotas(mascotaDAO.listarMascotas(), resp.getOutputStream());
                    break;
                case "ventas":
                    reporteDAO.generarReporteVentas(ventaDAO.listarVentas(), resp.getOutputStream());
                    break;
                case "consultas":
                    reporteDAO.generarReporteConsultas(consultaDAO.listarConsultas(), resp.getOutputStream());
                    break;
                case "cliente":
                    reporteDAO.generarReporteClientes(clienteDAO.listarClientes(), resp.getOutputStream());
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo de reporte no válido");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error generando PDF", e);
        }
    }
}
