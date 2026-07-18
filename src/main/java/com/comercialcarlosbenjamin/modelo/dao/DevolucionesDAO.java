package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.Devoluciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class DevolucionesDAO implements CRUD_Interface<Devoluciones> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public DevolucionesDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(Devoluciones item) {
        String sql = "insert into devoluciones (id_detalle_venta, cantidad, fecha, motivo, dinero_devuelto, estado_producto) values(?,?,?,?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, item.getId_detalle_venta());
            preStatement.setInt(2, item.getCantidad());
            preStatement.setTimestamp(3, item.getFecha());
            preStatement.setString(4, item.getMotivo());
            preStatement.setBigDecimal(5, item.getDinero_devuelto());
            preStatement.setString(6, item.getEstado_producto());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public Devoluciones getById(Integer id) {
        Devoluciones item = null;
        String sql = "SELECT * FROM devoluciones WHERE id_devolucion = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new Devoluciones();
 
                // Id
                item.setId_devolucion(rs.getInt("id_devolucion"));
 
                // Enteros (Integers)
                item.setId_detalle_venta(rs.getInt("id_detalle_venta"));
                item.setCantidad(rs.getInt("cantidad"));
 
                // Fechas (Timestamps)
                item.setFecha(rs.getTimestamp("fecha"));
 
                // Textos (Strings)
                item.setMotivo(rs.getString("motivo"));
                item.setEstado_producto(rs.getString("estado_producto"));
 
                // Decimales (BigDecimals)
                item.setDinero_devuelto(rs.getBigDecimal("dinero_devuelto"));
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
    public List<Devoluciones> listar(String valor) {
        Integer idDetalleVentaFiltro = null;
        if (valor != null && !valor.trim().isEmpty()) {
            try {
                idDetalleVentaFiltro = Integer.parseInt(valor.trim());
            } catch (NumberFormatException nfe) {
                idDetalleVentaFiltro = null;
            }
        }
 
        String sql = (idDetalleVentaFiltro != null)
                ? "select * from devoluciones where id_detalle_venta = ? order by id_devolucion desc"
                : "select * from devoluciones order by id_devolucion desc";
 
        List<Devoluciones> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            if (idDetalleVentaFiltro != null) preStatement.setInt(1, idDetalleVentaFiltro);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Devoluciones item = new Devoluciones();
 
                item.setId_devolucion(rs.getInt("id_devolucion"));
                item.setId_detalle_venta(rs.getInt("id_detalle_venta"));
                item.setCantidad(rs.getInt("cantidad"));
                item.setFecha(rs.getTimestamp("fecha"));
                item.setMotivo(rs.getString("motivo"));
                item.setEstado_producto(rs.getString("estado_producto"));
                item.setDinero_devuelto(rs.getBigDecimal("dinero_devuelto"));
 
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
    public void actualizar(Devoluciones item) {
        String sql = "UPDATE devoluciones SET "
           + "id_detalle_venta = ?, "
           + "cantidad = ?, "
           + "fecha = ?, "
           + "motivo = ?, "
           + "dinero_devuelto = ?, "
           + "estado_producto = ? "
           + "WHERE id_devolucion = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, item.getId_detalle_venta());
            preStatement.setInt(2, item.getCantidad());
            preStatement.setTimestamp(3, item.getFecha());
            preStatement.setString(4, item.getMotivo());
            preStatement.setBigDecimal(5, item.getDinero_devuelto());
            preStatement.setString(6, item.getEstado_producto());
            preStatement.setInt(7, item.getId_devolucion());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from devoluciones where id_devolucion=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}