package com.comercialcarlosbenjamin.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String url = "jdbc:mysql://localhost:3306/comercial_carlos_benjamin";
	private static final String usuario = "root";
	private static final String contraseña = "9876";
	
	static Connection conn = null;
	public static Connection conectar() throws SQLException
	{
            if(conn == null || conn.isClosed())
            {
                try {
                    conn = DriverManager.getConnection(url, usuario, contraseña);
                    System.out.println("Nueva conexion creada exitosamente");
                    return conn;
	     } catch(SQLException e) {
                    System.out.println("Error de conexion a la base de datos");
                    e.printStackTrace();
                    return conn;
	     }
            }
            return conn;
	}
}
