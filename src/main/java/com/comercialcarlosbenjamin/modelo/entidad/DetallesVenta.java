package com.comercialcarlosbenjamin.modelo.entidad;
 
import java.math.BigDecimal;
 
public class DetallesVenta {
    private int id_detalle_venta;
    private int id_venta;
    private int id_producto;
    private int cantidad;
    private BigDecimal precio_compra_momento;
    private BigDecimal precio_aplicado;
    private BigDecimal descuento;
    private BigDecimal subtotal;
 
    public int getId_detalle_venta() {
        return id_detalle_venta;
    }
 
    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }
 
    public int getId_venta() {
        return id_venta;
    }
 
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
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
 
    public BigDecimal getPrecio_aplicado() {
        return precio_aplicado;
    }
 
    public void setPrecio_aplicado(BigDecimal precio_aplicado) {
        this.precio_aplicado = precio_aplicado;
    }
 
    public BigDecimal getDescuento() {
        return descuento;
    }
 
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
 
    public BigDecimal getSubtotal() {
        return subtotal;
    }
 
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}