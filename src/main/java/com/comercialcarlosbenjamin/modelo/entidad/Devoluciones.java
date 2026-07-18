package com.comercialcarlosbenjamin.modelo.entidad;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
 
public class Devoluciones {
    private int id_devolucion;
    private int id_detalle_venta;
    private int cantidad;
    private Timestamp fecha;
    private String motivo;             // ENUM en la BD -> String en Java (ver notas finales)
    private BigDecimal dinero_devuelto;
    private String estado_producto;
 
    public int getId_devolucion() {
        return id_devolucion;
    }
 
    public void setId_devolucion(int id_devolucion) {
        this.id_devolucion = id_devolucion;
    }
 
    public int getId_detalle_venta() {
        return id_detalle_venta;
    }
 
    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }
 
    public int getCantidad() {
        return cantidad;
    }
 
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
 
    public Timestamp getFecha() {
        return fecha;
    }
 
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
 
    public String getMotivo() {
        return motivo;
    }
 
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
 
    public BigDecimal getDinero_devuelto() {
        return dinero_devuelto;
    }
 
    public void setDinero_devuelto(BigDecimal dinero_devuelto) {
        this.dinero_devuelto = dinero_devuelto;
    }
 
    public String getEstado_producto() {
        return estado_producto;
    }
 
    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }
}