package com.example.crud_nodejs_postgres.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonaModel {

    @SerializedName("idpersona")
    @Expose
    private Integer idpersona;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("idprofesion")
    @Expose
    private Integer idprofesion;

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdprofesion() {
        return idprofesion;
    }

    public void setIdprofesion(Integer idprofesion) {
        this.idprofesion = idprofesion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idpersona='" + idpersona + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido=" + apellido + '\'' +
                ", email=" + email + '\'' +
                ", idprofesion=" + idprofesion + '\'' +
                '}';
    }

}
