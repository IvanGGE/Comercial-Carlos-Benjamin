package com.comercialcarlosbenjamin.modelo.entidad;
 
import java.math.BigDecimal;
 
public class DetallesCompra {
    private int id_detalle_compra;
    private int id_compra;
    private int id_producto;
    private int cantidad;
    private BigDecimal precio_compra_momento;
 
    public int getId_detalle_compra() {
        return id_detalle_compra;
    }
 
    public void setId_detalle_compra(int id_detalle_compra) {
        this.id_detalle_compra = id_detalle_compra;
    }
 
    public int getId_compra() {
        return id_compra;
    }
 
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }
 
    public int getId_producto() {
        return id_producto;
    }
 
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
 
    public int getCantidad() {
        return cantidad;
    }
 
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
 
    public BigDecimal getPrecio_compra_momento() {
        return precio_compra_momento;
    }
 
    public void setPrecio_compra_momento(BigDecimal precio_compra_momento) {
        this.precio_compra_momento = precio_compra_momento;
    }
}