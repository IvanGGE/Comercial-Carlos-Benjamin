package com.comercialcarlosbenjamin.modelo.entidad;
 
public class Proveedores {
    private int id_proveedor;
    private String nombre;
    private String telefono;
    private String direccion;
 
    public int getId_proveedor() {
        return id_proveedor;
    }
 
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getTelefono() {
        return telefono;
    }
 
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
 
    public String getDireccion() {
        return direccion;
    }
 
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}