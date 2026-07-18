package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.Compras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class ComprasDAO implements CRUD_Interface<Compras> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public ComprasDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(Compras item) {
        String sql = "insert into compras (fecha_compra, fecha_llegada, estado, total, id_proveedor, id_usuario) values(?,?,?,?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setTimestamp(1, item.getFecha_compra());
            preStatement.setTimestamp(2, item.getFecha_llegada());
            preStatement.setString(3, item.getEstado());
            preStatement.setBigDecimal(4, item.getTotal());
            preStatement.setInt(5, item.getId_proveedor());
            preStatement.setInt(6, item.getId_usuario());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public Compras getById(Integer id) {
        Compras item = null;
        String sql = "SELECT * FROM compras WHERE id_compra = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new Compras();
 
                // Id
                item.setId_compra(rs.getInt("id_compra"));
 
                // Fechas (Timestamps)
                item.setFecha_compra(rs.getTimestamp("fecha_compra"));
                item.setFecha_llegada(rs.getTimestamp("fecha_llegada"));
 
                // Textos (Strings)
                item.setEstado(rs.getString("estado"));
 
                // Enteros (Integers)
                item.setId_proveedor(rs.getInt("id_proveedor"));
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
    public List<Compras> listar(String valor) {
        boolean filtrarPorEstado = valor != null && !valor.trim().isEmpty();
        String sql = filtrarPorEstado
                ? "select * from compras where estado like ? order by id_compra desc"
                : "select * from compras order by id_compra desc";
        List<Compras> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            if (filtrarPorEstado) preStatement.setString(1, "%" + valor + "%");
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Compras item = new Compras();
 
                item.setId_compra(rs.getInt("id_compra"));
                item.setFecha_compra(rs.getTimestamp("fecha_compra"));
                item.setFecha_llegada(rs.getTimestamp("fecha_llegada"));
                item.setEstado(rs.getString("estado"));
                item.setId_proveedor(rs.getInt("id_proveedor"));
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
    public void actualizar(Compras item) {
        String sql = "UPDATE compras SET "
           + "fecha_compra = ?, "
           + "fecha_llegada = ?, "
           + "estado = ?, "
           + "total = ?, "
           + "id_proveedor = ?, "
           + "id_usuario = ? "
           + "WHERE id_compra = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setTimestamp(1, item.getFecha_compra());
            preStatement.setTimestamp(2, item.getFecha_llegada());
            preStatement.setString(3, item.getEstado());
            preStatement.setBigDecimal(4, item.getTotal());
            preStatement.setInt(5, item.getId_proveedor());
            preStatement.setInt(6, item.getId_usuario());
            preStatement.setInt(7, item.getId_compra());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from compras where id_compra=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}