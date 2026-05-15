package com.comercialcarlosbenjamin.modelo.dao;

import com.comercialcarlosbenjamin.modelo.entidad.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductosDAO {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
    ResultSet resultSet = null;

    public ProductosDAO() {
        conexion = new Conexion();
    }
    
}
