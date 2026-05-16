package com.comercialcarlosbenjamin.modelo.entidad;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ventas {
    private int id_venta;
    private Timestamp fecha;
    private BigDecimal total;
    private int id_usuario;
}
