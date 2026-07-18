package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class RolesDAO implements CRUD_Interface<Roles> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public RolesDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(Roles item) {
        String sql = "INSERT INTO roles (nombre_rol) VALUES(?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getNombre_rol());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public Roles getById(Integer id) {
        Roles item = null;
        String sql = "SELECT * FROM roles WHERE id_rol = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new Roles();
                item.setId_rol(rs.getInt("id_rol"));
                item.setNombre_rol(rs.getString("nombre_rol"));
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
    public List<Roles> listar(String valor) {
        String sql = "SELECT * FROM roles WHERE nombre_rol LIKE ? ORDER BY id_rol ASC";
        List<Roles> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, "%" + valor + "%");
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Roles item = new Roles();
                item.setId_rol(rs.getInt("id_rol"));
                item.setNombre_rol(rs.getString("nombre_rol"));
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
    public void actualizar(Roles item) {
        String sql = "UPDATE roles SET nombre_rol = ? WHERE id_rol = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getNombre_rol());
            preStatement.setInt(2, item.getId_rol());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM roles WHERE id_rol=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
