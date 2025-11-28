package com.mrpolopets.Model;

public class MascotaDTO {

    private String id;
    private String nombre;
    private String raza;
    private int edad;
    private String vacunas;
    private String tipo;
    private String peso;
    private boolean estaAdoptado;
    private String lugarOrigen;
    private String genero;
    private String fechaIngreso;

    // Constructor mínimo
    public MascotaDTO(String id, String nombre, String raza, int edad, String vacunas, String tipo, String peso, boolean estaAdoptado, String lugarOrigen, String genero, String fechaIngreso) {

    this.id = id;
    this.nombre = nombre;
    this.raza = raza;
    this.edad = edad;
    this.vacunas = vacunas;
    this.tipo = tipo;
    this.peso = peso;
    this.estaAdoptado = estaAdoptado;
    this.lugarOrigen = lugarOrigen;
    this.genero = genero;
    this.fechaIngreso = fechaIngreso;
    }

    // Getters y setters
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
    
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getVacunas() {
        return vacunas;
    }

    public void setVacunas(String vacunas) {
        this.vacunas = vacunas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public boolean isEstaAdoptado() {
        return estaAdoptado;
    }

    public void setEstaAdoptado(boolean estaAdoptado) {
        this.estaAdoptado = estaAdoptado;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "MascotaDTO {" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", vacunas='" + vacunas + '\'' +
                ", tipo='" + tipo + '\'' +
                ", peso='" + peso + '\'' +
                ", estaAdoptado=" + estaAdoptado +
                ", lugarOrigen='" + lugarOrigen + '\'' +
                ", genero='" + genero + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                '}';
    }
}