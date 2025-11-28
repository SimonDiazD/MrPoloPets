package com.mrpolopets.Model;

public class ConsultaDTO {
    private String idConsulta;
    private String fecha;
    private String sintomas;
    private String tratamiento;
    private String idMascota;

    public ConsultaDTO(String idConsulta, String fecha, String sintomas, String tratamiento, String idMascota) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.idMascota = idMascota;
    }

    // Getters y setters

    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    @Override
    public String toString() {
        return "ConsultaDTO {" +
                "idConsulta='" + idConsulta + '\'' +
                ", fecha='" + fecha + '\'' +
                ", sintomas='" + sintomas + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", idMascota='" + idMascota + '\'' +
                '}';
    }
}