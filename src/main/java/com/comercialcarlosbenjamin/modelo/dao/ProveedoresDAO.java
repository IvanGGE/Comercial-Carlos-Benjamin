package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class ProveedoresDAO implements CRUD_Interface<Proveedores> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public ProveedoresDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(Proveedores item) {
        String sql = "insert into proveedores (nombre, telefono, direccion) values(?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getNombre());
            preStatement.setString(2, item.getTelefono());
            preStatement.setString(3, item.getDireccion());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public Proveedores getById(Integer id) {
        Proveedores item = null;
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new Proveedores();
 
                // Id
                item.setId_proveedor(rs.getInt("id_proveedor"));
 
                // Textos (Strings)
                item.setNombre(rs.getString("nombre"));
                item.setTelefono(rs.getString("telefono"));
                item.setDireccion(rs.getString("direccion"));
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
    public List<Proveedores> listar(String valor) {
        String sql = "select * from proveedores where nombre like ? order by id_proveedor asc";
        List<Proveedores> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, "%" + valor + "%");
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Proveedores item = new Proveedores();
 
                // Id
                item.setId_proveedor(rs.getInt("id_proveedor"));
 
                // Textos (Strings)
                item.setNombre(rs.getString("nombre"));
                item.setTelefono(rs.getString("telefono"));
                item.setDireccion(rs.getString("direccion"));
 
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
    public void actualizar(Proveedores item) {
        String sql = "UPDATE proveedores SET "
           + "nombre = ?, "
           + "telefono = ?, "
           + "direccion = ? "
           + "WHERE id_proveedor = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getNombre());
            preStatement.setString(2, item.getTelefono());
            preStatement.setString(3, item.getDireccion());
            preStatement.setInt(4, item.getId_proveedor());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from proveedores where id_proveedor=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
