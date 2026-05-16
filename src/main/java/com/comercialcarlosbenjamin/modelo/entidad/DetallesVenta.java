package com.comercialcarlosbenjamin.modelo.entidad;

import java.math.BigDecimal;



public class DetallesVenta {
    private int id_detalle_venta;
    private int id_venta;
    private int id_producto;
    private int cantidad;
    private BigDecimal precio_aplicado;
    private BigDecimal descuento;
    private BigDecimal subtotal;
}
