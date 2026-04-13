package com.comercialcarlosbenjamin.modelo.entidad;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Compras {
    private int id_compra;
    private Timestamp fecha;
    private BigDecimal total;
    private int id_proveedor;
    private int id_usuario;
}
