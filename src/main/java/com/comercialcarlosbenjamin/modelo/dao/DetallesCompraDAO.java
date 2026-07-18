package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.DetallesCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class DetallesCompraDAO implements CRUD_Interface<DetallesCompra> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public DetallesCompraDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(DetallesCompra item) {
        String sql = "insert into detalles_compra (id_compra, id_producto, cantidad, precio_compra_momento) values(?,?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, item.getId_compra());
            preStatement.setInt(2, item.getId_producto());
            preStatement.setInt(3, item.getCantidad());
            preStatement.setBigDecimal(4, item.getPrecio_compra_momento());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public DetallesCompra getById(Integer id) {
        DetallesCompra item = null;
        String sql = "SELECT * FROM detalles_compra WHERE id_detalle_compra = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new DetallesCompra();
 
                // Id
                item.setId_detalle_compra(rs.getInt("id_detalle_compra"));
 
                // Enteros (Integers)
                item.setId_compra(rs.getInt("id_compra"));
                item.setId_producto(rs.getInt("id_producto"));
                item.setCantidad(rs.getInt("cantidad"));
 
                // Decimales (BigDecimals)
                item.setPrecio_compra_momento(rs.getBigDecimal("precio_compra_momento"));
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
    public List<DetallesCompra> listar(String valor) {
        Integer idCompraFiltro = null;
        if (valor != null && !valor.trim().isEmpty()) {
            try {
                idCompraFiltro = Integer.parseInt(valor.trim());
            } catch (NumberFormatException nfe) {
                idCompraFiltro = null; // si no es numérico, se listan todos los detalles
            }
        }
 
        String sql = (idCompraFiltro != null)
                ? "select * from detalles_compra where id_compra = ? order by id_detalle_compra asc"
                : "select * from detalles_compra order by id_detalle_compra asc";
 
        List<DetallesCompra> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            if (idCompraFiltro != null) preStatement.setInt(1, idCompraFiltro);
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                DetallesCompra item = new DetallesCompra();
 
                item.setId_detalle_compra(rs.getInt("id_detalle_compra"));
                item.setId_compra(rs.getInt("id_compra"));
                item.setId_producto(rs.getInt("id_producto"));
                item.setCantidad(rs.getInt("cantidad"));
                item.setPrecio_compra_momento(rs.getBigDecimal("precio_compra_momento"));
 
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
    public void actualizar(DetallesCompra item) {
        String sql = "UPDATE detalles_compra SET "
           + "id_compra = ?, "
           + "id_producto = ?, "
           + "cantidad = ?, "
           + "precio_compra_momento = ? "
           + "WHERE id_detalle_compra = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, item.getId_compra());
            preStatement.setInt(2, item.getId_producto());
            preStatement.setInt(3, item.getCantidad());
            preStatement.setBigDecimal(4, item.getPrecio_compra_momento());
            preStatement.setInt(5, item.getId_detalle_compra());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from detalles_compra where id_detalle_compra=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
