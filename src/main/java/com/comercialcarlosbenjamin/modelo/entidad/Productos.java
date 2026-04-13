package com.comercialcarlosbenjamin.modelo.entidad;

import java.math.BigDecimal;

public class Productos {
    private int id_producto;
    private String codigo_serial;
    private String nombre;
    private String descripcion;
    private BigDecimal precio_compra; //<-- REVISAR DESPUES
    private BigDecimal precio_venta_unitario; //<-- REVISAR DESPUES
    private BigDecimal precio_venta_mayorista;
    private int iva;
    private int stock;
    private int stock_minimo;
    private int id_categoria;
}
