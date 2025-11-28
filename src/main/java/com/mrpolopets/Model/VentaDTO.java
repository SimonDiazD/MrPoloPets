package com.mrpolopets.Model;

public class VentaDTO {
    private String idVenta;
    private String fecha;
    private Double valorVenta;
    private String idCliente;
    private String idMascota;

    public VentaDTO(String idVenta, String fecha, Double valorVenta, String idCliente, String idMascota) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.valorVenta = valorVenta;
        this.idCliente = idCliente;
        this.idMascota = idMascota;
    }

    // Getters y setters

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    @Override
    public String toString() {
        return "VentaDTO {" +
                "idVenta='" + idVenta + '\'' +
                ", fecha='" + fecha + '\'' +
                ", valorVenta=" + valorVenta +
                ", idCliente='" + idCliente + '\'' +
                ", idMascota='" + idMascota + '\'' +
                '}';
    }
}