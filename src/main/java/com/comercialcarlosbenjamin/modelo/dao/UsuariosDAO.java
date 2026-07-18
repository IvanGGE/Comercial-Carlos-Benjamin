package com.comercialcarlosbenjamin.modelo.dao;

import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO implements CRUD_Interface<Usuarios>{
    Connection connection = null;
    PreparedStatement preStatement = null;
    ResultSet resultSet = null;
    public UsuariosDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }

    public Usuarios iniciarSesion(String usuario, String contrasena)
    {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1,usuario);
            preStatement.setString(2, contrasena);

            resultSet = preStatement.executeQuery();
            if(resultSet.next())
            {
                Usuarios usuarioLogueado = new Usuarios();
                usuarioLogueado.setId_usuario(resultSet.getInt("id_usuario"));
                usuarioLogueado.setNombre(resultSet.getString("nombre"));
                usuarioLogueado.setUsuario(resultSet.getString("usuario"));
                usuarioLogueado.setContrasena(resultSet.getString("contrasena"));
                usuarioLogueado.setRol(resultSet.getInt("id_rol"));
                return usuarioLogueado;
            }

        } catch (SQLException ex) {
            System.getLogger(UsuariosDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    @Override
    public void crear(Usuarios obj) {
        String sql = "INSERT INTO usuarios (nombre, usuario, contrasena, id_rol) VALUES (?, ?, ?, ?)";
        try {
            preStatement= connection.prepareStatement(sql);
            preStatement.setString(1, obj.getNombre());
            preStatement.setString(2, obj.getUsuario());
            preStatement.setString(3, obj.getContrasena());
            preStatement.setInt(4, obj.getRol());
            preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public Usuarios getById(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            try (ResultSet rs = preStatement.executeQuery()) {
                if (rs.next()) {
                    Usuarios u = new Usuarios();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setRol(rs.getInt("id_rol"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Usuarios> listar(String valor) {
        List<Usuarios> lista = new ArrayList<>();
        // Búsqueda por coincidencia de nombre o usuario
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ? OR usuario LIKE ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, "%" + valor + "%");
            preStatement.setString(2, "%" + valor + "%");

            try (ResultSet rs = preStatement.executeQuery()) {
                while (rs.next()) {
                    Usuarios u = new Usuarios();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setRol(rs.getInt("id_rol"));
                    lista.add(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    public void actualizar(Usuarios obj) {
        String sql = "UPDATE usuarios SET nombre = ?, usuario = ?, contrasena = ?, id_rol = ? WHERE id_usuario = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, obj.getNombre());
            preStatement.setString(2, obj.getUsuario());
            preStatement.setString(3, obj.getContrasena());
            preStatement.setInt(4, obj.getRol());
            preStatement.setInt(5, obj.getId_usuario());
            preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
