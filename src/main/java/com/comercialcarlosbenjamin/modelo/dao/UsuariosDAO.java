package com.comercialcarlosbenjamin.modelo.dao;

import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UsuariosDAO {
        Connection connection = null;
	Conexion conexion = null;
	PreparedStatement preStatement = null;
	ResultSet resultSet = null;
        
        public UsuariosDAO() {
            conexion = new Conexion();
        }
        
        public Usuarios iniciarSesion(String usuario, String contrasena)
        {
            try {
                connection = conexion.conectar();
            } catch (SQLException ex) {
                System.getLogger(UsuariosDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
            if(connection == null)
            {
                System.out.println("No se pudo conectar a la base de datos");
            }
            
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
                    usuarioLogueado.setRol(resultSet.getString("rol"));
                    return usuarioLogueado;
                }
                
            } catch (SQLException ex) {
                System.getLogger(UsuariosDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
            return null;
        }
}
