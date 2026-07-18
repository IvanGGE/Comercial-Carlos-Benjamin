package com.comercialcarlosbenjamin.modelo.dao;
 
import com.comercialcarlosbenjamin.modelo.entidad.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
 
public class CategoriasDAO implements CRUD_Interface<Categorias> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;
 
    public CategoriasDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }
 
    @Override
    public void crear(Categorias item) {
        String sql = "insert into categorias (nombre) values(?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getNombre());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public Categorias getById(Integer id) {
        Categorias item = null;
        String sql = "SELECT * FROM categorias WHERE id_categoria = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();
 
            if (rs.next()) {
                item = new Categorias();
                item.setId_categoria(rs.getInt("id_categoria"));
                item.setNombre(rs.getString("nombre"));
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
    public List<Categorias> listar(String valor) {
        String sql = "select * from categorias where nombre like ? order by id_categoria asc";
        List<Categorias> lista = new ArrayList<>();
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, "%" + valor + "%");
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Categorias item = new Categorias();
                item.setId_categoria(rs.getInt("id_categoria"));
                item.setNombre(rs.getString("nombre"));
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
    public void actualizar(Categorias item) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getNombre());
            preStatement.setInt(2, item.getId_categoria());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    @Override
    public void eliminar(Integer id) {
        String sql = "delete from categorias where id_categoria=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}