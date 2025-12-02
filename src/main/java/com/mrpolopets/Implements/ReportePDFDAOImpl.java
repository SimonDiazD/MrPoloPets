package com.mrpolopets.Implements;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.mrpolopets.Dao.ReportePDFDao;
import com.mrpolopets.Model.*; // Importa todos tus DTOs
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Stream;

public class ReportePDFDAOImpl implements ReportePDFDao {

    // --- IMPLEMENTACIÓN MASCOTAS ---
    @Override
    public void generarReporteMascotas(List<MascotaDTO> lista, OutputStream out) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
            Paragraph titulo = new Paragraph("Reporte General de Mascotas", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Tabla
            PdfPTable table = new PdfPTable(5); // 5 columnas
            table.setWidthPercentage(100);
            addTableHeader(table, "Nombre", "Raza", "Edad", "Estado", "Ingreso");

            for (MascotaDTO m : lista) {
                table.addCell(m.getNombre());
                table.addCell(m.getRaza());
                table.addCell(String.valueOf(m.getEdad()));
                table.addCell(m.isEstaAdoptado() ? "Adoptado" : "Disponible");
                table.addCell(m.getFechaIngreso());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    // --- IMPLEMENTACIÓN VENTAS ---
    @Override
    public void generarReporteVentas(List<VentaDTO> lista, OutputStream out) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("Reporte Financiero de Ventas"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            addTableHeader(table, "Fecha", "ID Venta", "Cliente", "Total");

            double granTotal = 0;
            for (VentaDTO v : lista) {
                table.addCell(v.getFecha());
                table.addCell(v.getIdVenta());
                table.addCell(v.getIdCliente());
                table.addCell("$ " + v.getValorVenta());
                granTotal += v.getValorVenta();
            }
            document.add(table);
            
            Paragraph total = new Paragraph("Total Recaudado: $ " + granTotal);
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setSpacingBefore(10);
            document.add(total);

            document.close();
        } catch (Exception e) { e.printStackTrace(); }
    }


    @Override
    public void generarReporteClientes(List<ClienteDTO> lista, OutputStream out) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
            Paragraph titulo = new Paragraph("Directorio de Clientes", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Tabla de 5 columnas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            
            float[] columnWidths = {3f, 1f, 2f, 3f, 2f}; 
            table.setWidths(columnWidths);

            addTableHeader(table, "Nombre", "ID", "Teléfono", "Dirección", "Mascotas");

            for (ClienteDTO c : lista) {
                table.addCell(c.getNombre());
                table.addCell(c.getId());
                table.addCell(c.getNumeroContacto());
                table.addCell(c.getDireccionContacto());
                
                // Validación para evitar nulos en el PDF
                String mascotas = (c.getIdMascotasCargo() != null) ? c.getIdMascotasCargo() : "Ninguna";
                table.addCell(mascotas);
            }

            document.add(table);
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generarReporteConsultas(List<ConsultaDTO> lista, OutputStream out) {
        try {
            Document document = new Document(PageSize.A4.rotate()); // ¡OJO! Ponemos la hoja horizontal (Landscape)
            PdfWriter.getInstance(document, out);
            document.open();

            // Título
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
            Paragraph titulo = new Paragraph("Historial de Consultas Médicas", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Tabla de 5 columnas
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            float[] anchos = {1.5f, 1.5f, 1.5f, 4f, 4f}; 
            table.setWidths(anchos);

            addTableHeader(table, "Ref.", "Fecha", "Paciente", "Síntomas", "Tratamiento");

            for (ConsultaDTO c : lista) {
                table.addCell(c.getIdConsulta());
                table.addCell(c.getFecha());
                table.addCell(c.getIdMascota());
                table.addCell(c.getSintomas());
                
                // Si no hay tratamiento, ponemos "Pendiente" en cursiva
                String tratamiento = c.getTratamiento();
                if (tratamiento == null || tratamiento.isEmpty()) {
                    tratamiento = "Pendiente de asignación";
                }
                table.addCell(tratamiento);
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        Stream.of(headers).forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setPadding(5);
            table.addCell(header);
        });
    }
}