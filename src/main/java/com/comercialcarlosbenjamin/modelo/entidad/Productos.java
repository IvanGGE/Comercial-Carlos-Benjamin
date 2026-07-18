package com.comercialcarlosbenjamin.modelo.entidad;

import java.math.BigDecimal;

public class Productos {
    private int id_producto;
    private String codigo_serial;
    private String nombre;
    private String descripcion;
    private BigDecimal precio_compra;
    private BigDecimal precio_venta_unitario;
    private BigDecimal precio_venta_mayorista;
    private int cantidad_mayorista;
    private int stock;
    private int stock_minimo;
    private int id_categoria;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo_serial() {
        return codigo_serial;
    }

    public void setCodigo_serial(String codigo_serial) {
        this.codigo_serial = codigo_serial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(BigDecimal precio_compra) {
        this.precio_compra = precio_compra;
    }

    public BigDecimal getPrecio_venta_unitario() {
        return precio_venta_unitario;
    }

    public void setPrecio_venta_unitario(BigDecimal precio_venta_unitario) {
        this.precio_venta_unitario = precio_venta_unitario;
    }

    public BigDecimal getPrecio_venta_mayorista() {
        return precio_venta_mayorista;
    }

    public void setPrecio_venta_mayorista(BigDecimal precio_venta_mayorista) {
        this.precio_venta_mayorista = precio_venta_mayorista;
    }

    public int getCantidad_mayorista() {
        return cantidad_mayorista;
    }

    public void setCantidad_mayorista(int cantidad_mayorista) {
        this.cantidad_mayorista = cantidad_mayorista;
    }
    

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    
}
