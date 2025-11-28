package com.mrpolopets.Model;

public class ClienteDTO {
    private String id;
    private String nombre;
    private String direccionContacto;
    private String numeroContacto;
    private String idMascotasCargo;
    
    public ClienteDTO(String id, String nombre, String direccionContacto, String numeroContacto, String idMascotasCargo){
        this.id = id;
        this.nombre = nombre;
        this.direccionContacto = direccionContacto;
        this.numeroContacto = numeroContacto;
        this.idMascotasCargo = idMascotasCargo;
    }
    
    // Getters y setters
    
    public String getIdMascotasCargo() {
        return idMascotasCargo;
    }
    
    public void setIdMascotasCargo(String idMascotasCargo) {
        this.idMascotasCargo = idMascotasCargo;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionContacto() {
        return direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto) {
        this.direccionContacto = direccionContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    @Override
    public String toString() {
        return "ClienteDTO {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccionContacto='" + direccionContacto + '\'' +
                ", numeroContacto='" + numeroContacto + '\'' +
                ", idMascotasCargo='" + idMascotasCargo + '\'' +
                '}';
    }
    
}
