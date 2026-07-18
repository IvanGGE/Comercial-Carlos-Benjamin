package com.comercialcarlosbenjamin.modelo.entidad;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
 
public class Ventas {
    private int id_venta;
    private Timestamp fecha;
    private BigDecimal total;
    private int id_usuario;
 
    public int getId_venta() {
        return id_venta;
    }
 
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
 
    public Timestamp getFecha() {
        return fecha;
    }
 
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
 
    public BigDecimal getTotal() {
        return total;
    }
 
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
 
    public int getId_usuario() {
        return id_usuario;
    }
 
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}