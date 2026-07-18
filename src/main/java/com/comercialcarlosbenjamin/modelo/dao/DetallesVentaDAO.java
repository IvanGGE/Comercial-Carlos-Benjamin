package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.DetallesVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class DetallesVentaDAO implements CRUD_Interface<DetallesVenta> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public DetallesVentaDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(DetallesVenta item) {
        String sql = "insert into detalles_venta (id_venta, id_producto, cantidad, precio_compra_momento, precio_aplicado, descuento, subtotal) values(?,?,?,?,?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, item.getId_venta());
            preStatement.setInt(2, item.getId_producto());
            preStatement.setInt(3, item.getCantidad());
            preStatement.setBigDecimal(4, item.getPrecio_compra_momento());
            preStatement.setBigDecimal(5, item.getPrecio_aplicado());
            preStatement.setBigDecimal(6, item.getDescuento());
            preStatement.setBigDecimal(7, item.getSubtotal());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public DetallesVenta getById(Integer id) {
        DetallesVenta item = null;
        String sql = "SELECT * FROM detalles_venta WHERE id_detalle_venta = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new DetallesVenta();
 
                // Id
                item.setId_detalle_venta(rs.getInt("id_detalle_venta"));
 
                // Enteros (Integers)
                item.setId_venta(rs.getInt("id_venta"));
                item.setId_producto(rs.getInt("id_producto"));
                item.setCantidad(rs.getInt("cantidad"));
 
                // Decimales (BigDecimals)
                item.setPrecio_compra_momento(rs.getBigDecimal("precio_compra_momento"));
                item.setPrecio_aplicado(rs.getBigDecimal("precio_aplicado"));
                item.setDescuento(rs.getBigDecimal("descuento"));
                item.setSubtotal(rs.getBigDecimal("subtotal"));
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
    public List<DetallesVenta> listar(String valor) {
        Integer idVentaFiltro = null;
        if (valor != null && !valor.trim().isEmpty()) {
            try {
                idVentaFiltro = Integer.parseInt(valor.trim());
            } catch (NumberFormatException nfe) {
                idVentaFiltro = null;
            }
        }
 
        String sql = (idVentaFiltro != null)
                ? "select * from detalles_venta where id_venta = ? order by id_detalle_venta asc"
                : "select * from detalles_venta order by id_detalle_venta asc";
 
        List<DetallesVenta> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            if (idVentaFiltro != null) preStatement.setInt(1, idVentaFiltro);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                DetallesVenta item = new DetallesVenta();
 
                item.setId_detalle_venta(rs.getInt("id_detalle_venta"));
                item.setId_venta(rs.getInt("id_venta"));
                item.setId_producto(rs.getInt("id_producto"));
                item.setCantidad(rs.getInt("cantidad"));
                item.setPrecio_compra_momento(rs.getBigDecimal("precio_compra_momento"));
                item.setPrecio_aplicado(rs.getBigDecimal("precio_aplicado"));
                item.setDescuento(rs.getBigDecimal("descuento"));
                item.setSubtotal(rs.getBigDecimal("subtotal"));
 
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
    public void actualizar(DetallesVenta item) {
        String sql = "UPDATE detalles_venta SET "
           + "id_venta = ?, "
           + "id_producto = ?, "
           + "cantidad = ?, "
           + "precio_compra_momento = ?, "
           + "precio_aplicado = ?, "
           + "descuento = ?, "
           + "subtotal = ? "
           + "WHERE id_detalle_venta = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, item.getId_venta());
            preStatement.setInt(2, item.getId_producto());
            preStatement.setInt(3, item.getCantidad());
            preStatement.setBigDecimal(4, item.getPrecio_compra_momento());
            preStatement.setBigDecimal(5, item.getPrecio_aplicado());
            preStatement.setBigDecimal(6, item.getDescuento());
            preStatement.setBigDecimal(7, item.getSubtotal());
            preStatement.setInt(8, item.getId_detalle_venta());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from detalles_venta where id_detalle_venta=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}