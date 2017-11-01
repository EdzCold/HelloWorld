package com.volley.profuturo.en501863.learningproyectv07.model;

/**
 * Created by EN501863 on 31/10/2017.
 */

public class ContentInformation {

    private String id;
    private String nombre;
    private String base64;
    private String lastName;
    private String telefono;
    private String codigPostal;
    private String direccion;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigPostal() {
        return codigPostal;
    }

    public void setCodigPostal(String codigPostal) {
        this.codigPostal = codigPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

}
