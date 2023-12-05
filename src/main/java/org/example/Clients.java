package org.example;

import org.bson.types.ObjectId;


public class Clients {

    private ObjectId id;
    private String clientid;

   private String nombre;
   private String apellidos;
   private String nacionalidad;
   private String telefono;
   private String usuario;
   private String email;
   private String contrasenya;

    public Clients(ObjectId id, String clientid, String nombre, String apellidos, String nacionalidad, String telefono, String usuario, String email, String contrasenya) {
        this.id = id;
        this.clientid = clientid;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.usuario = usuario;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public Clients(String clientid, String nombre, String apellidos, String nacionalidad, String telefono, String usuario, String email, String contrasenya) {
        this.clientid = clientid;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.usuario = usuario;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public Clients() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", clientid='" + clientid + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }
}
