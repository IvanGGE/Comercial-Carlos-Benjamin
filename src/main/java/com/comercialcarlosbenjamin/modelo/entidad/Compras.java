package com.comercialcarlosbenjamin.modelo.entidad;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
 
public class Compras {
    private int id_compra;
    private Timestamp fecha_compra;
    private Timestamp fecha_llegada;
    private String estado;
    private BigDecimal total;
    private int id_proveedor;
    private int id_usuario;
 
    public int getId_compra() {
        return id_compra;
    }
 
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
 
    public Timestamp getFecha_compra() {
        return fecha_compra;
    }
 
    public void setFecha_compra(Timestamp fecha_compra) {
        this.fecha_compra = fecha_compra;
    }
 
    public Timestamp getFecha_llegada() {
        return fecha_llegada;
    }
 
    public void setFecha_llegada(Timestamp fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }
 
    public String getEstado() {
        return estado;
    }
 
    public void setEstado(String estado) {
        this.estado = estado;
    }
 
    public BigDecimal getTotal() {
        return total;
    }
 
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
 
    public int getId_proveedor() {
        return id_proveedor;
    }
 
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
 
    public int getId_usuario() {
        return id_usuario;
    }
 
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}