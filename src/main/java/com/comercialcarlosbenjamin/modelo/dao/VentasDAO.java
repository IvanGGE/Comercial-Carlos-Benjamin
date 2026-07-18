package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class VentasDAO implements CRUD_Interface<Ventas> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public VentasDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(Ventas item) {
        String sql = "insert into ventas (fecha, total, id_usuario) values(?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setTimestamp(1, item.getFecha());
            preStatement.setBigDecimal(2, item.getTotal());
            preStatement.setInt(3, item.getId_usuario());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public Ventas getById(Integer id) {
        Ventas item = null;
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new Ventas();
 
                // Id
                item.setId_venta(rs.getInt("id_venta"));
 
                // Fechas (Timestamps)
                item.setFecha(rs.getTimestamp("fecha"));
 
                // Enteros (Integers)
                item.setId_usuario(rs.getInt("id_usuario"));
 
                // Decimales (BigDecimals)
                item.setTotal(rs.getBigDecimal("total"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }
 
    @Override
    public List<Ventas> listar(String valor) {
        Integer idUsuarioFiltro = null;
        if (valor != null && !valor.trim().isEmpty()) {
            try {
                idUsuarioFiltro = Integer.parseInt(valor.trim());
            } catch (NumberFormatException nfe) {
                idUsuarioFiltro = null;
            }
        }
 
        String sql = (idUsuarioFiltro != null)
                ? "select * from ventas where id_usuario = ? order by id_venta desc"
                : "select * from ventas order by id_venta desc";
 
        List<Ventas> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            if (idUsuarioFiltro != null) preStatement.setInt(1, idUsuarioFiltro);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Ventas item = new Ventas();
 
                item.setId_venta(rs.getInt("id_venta"));
                item.setFecha(rs.getTimestamp("fecha"));
                item.setId_usuario(rs.getInt("id_usuario"));
                item.setTotal(rs.getBigDecimal("total"));
 
                lista.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
 
    @Override
    public void actualizar(Ventas item) {
        String sql = "UPDATE ventas SET "
           + "fecha = ?, "
           + "total = ?, "
           + "id_usuario = ? "
           + "WHERE id_venta = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setTimestamp(1, item.getFecha());
            preStatement.setBigDecimal(2, item.getTotal());
            preStatement.setInt(3, item.getId_usuario());
            preStatement.setInt(4, item.getId_venta());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from ventas where id_venta=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
