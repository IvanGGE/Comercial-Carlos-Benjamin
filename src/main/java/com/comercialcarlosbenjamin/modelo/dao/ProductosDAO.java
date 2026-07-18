package com.comercialcarlosbenjamin.modelo.dao;

import com.comercialcarlosbenjamin.modelo.entidad.Productos;
import com.comercialcarlosbenjamin.modelo.dao.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ProductosDAO implements CRUD_Interface<Productos> {
    Connection connection = null;
    Conexion conexion = null;
    PreparedStatement preStatement = null;

    public ProductosDAO() {
        try {
            this.connection = Conexion.conectar();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo establecer la conexión con la base de datos.", e);
        }
    }

    @Override
    public void crear(Productos item) {
        String sql = "insert into productos (codigo_serial, nombre, descripcion, id_categoria, precio_compra, precio_venta_unitario, precio_venta_mayorista, cantidad_mayorista, stock, stock_minimo) values(?,?,?,?,?,?,?,?,?)";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, item.getCodigo_serial());
            preStatement.setString(2, item.getNombre());
            preStatement.setString(3, item.getDescripcion());
            preStatement.setInt(4, item.getId_categoria());
            preStatement.setBigDecimal(5, item.getPrecio_compra());
            preStatement.setBigDecimal(6, item.getPrecio_venta_unitario());
            preStatement.setBigDecimal(7, item.getPrecio_venta_mayorista());
            preStatement.setInt(8,item.getCantidad_mayorista());
            preStatement.setInt(9, item.getStock());
            preStatement.setInt(10, item.getStock_minimo());
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Productos getById(Integer id) {
        Productos item = null;
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet rs = preStatement.executeQuery();

            if (rs.next()) {
                item = new Productos();

                // Id
                item.setId_producto(rs.getInt("id_producto"));

                // Textos (Strings)
                item.setCodigo_serial(rs.getString("codigo_serial"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));

                // Enteros (Integers)
                item.setId_categoria(rs.getInt("id_categoria"));
                item.setCantidad_mayorista(rs.getInt("cantidad_mayorista"));
                item.setStock(rs.getInt("stock"));
                item.setStock_minimo(rs.getInt("stock_minimo"));

                // Decimales (BigDecimals)
                item.setPrecio_compra(rs.getBigDecimal("precio_compra"));
                item.setPrecio_venta_unitario(rs.getBigDecimal("precio_venta_unitario"));
                item.setPrecio_venta_mayorista(rs.getBigDecimal("precio_venta_mayorista"));
            }

        } catch (SQLException ex) {
            // Es buena práctica al menos imprimir el error en consola para saber si algo falla
            ex.printStackTrace(); 
        } finally {
            // Cerramos el preStatement y el ResultSet al no usar try-with-resources
            try {
                if (preStatement != null) preStatement.close(); //Basta con cerrar el preStatement, pues este cierra automáticamente todos los objetos ResultSet asociados a él 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    @Override
    public List<Productos> listar(String valor) {
        String sql = "select * from productos where nombre like ? order by id asc";
        ArrayList lista = new ArrayList();
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setString(1, "%" + valor + "%");
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()) {
                Productos item = new Productos();
            
                // Id
                item.setId_producto(rs.getInt("id_producto"));

                // Textos (Strings)
                item.setCodigo_serial(rs.getString("codigo_serial"));
                item.setNombre(rs.getString("nombre"));
                item.setDescripcion(rs.getString("descripcion"));

                // Enteros (Integers)
                item.setId_categoria(rs.getInt("id_categoria"));
                item.setCantidad_mayorista(rs.getInt("cantidad_mayorista"));
                item.setStock(rs.getInt("stock"));
                item.setStock_minimo(rs.getInt("stock_minimo"));

                // Decimales (BigDecimals)
                item.setPrecio_compra(rs.getBigDecimal("precio_compra"));
                item.setPrecio_venta_unitario(rs.getBigDecimal("precio_venta_unitario"));
                item.setPrecio_venta_mayorista(rs.getBigDecimal("precio_venta_mayorista"));
                
                // se agrega el objeto a la lista
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
    public void actualizar(Productos item) {
        String sql = "UPDATE productos SET "
           + "codigo_serial = ?, "
           + "nombre = ?, "
           + "descripcion = ?, "
           + "id_categoria = ?, "
           + "precio_compra = ?, "
           + "precio_venta_unitario = ?, "
           + "precio_venta_mayorista = ?, "
           + "cantidad_mayorista = ?, "
           + "stock = ?, "
           + "stock_minimo = ? "
           + "WHERE id_producto = ?";

        try {
            preStatement = connection.prepareStatement(sql);

            preStatement.setString(1, item.getCodigo_serial());
            preStatement.setString(2, item.getNombre());
            preStatement.setString(3, item.getDescripcion());
            preStatement.setInt(4, item.getId_categoria());
            preStatement.setBigDecimal(5, item.getPrecio_compra());
            preStatement.setBigDecimal(6, item.getPrecio_venta_unitario());
            preStatement.setBigDecimal(7, item.getPrecio_venta_mayorista());
            preStatement.setInt(8, item.getCantidad_mayorista());
            preStatement.setInt(9, item.getStock());
            preStatement.setInt(10, item.getStock_minimo());

            // El ID va al final porque está en el WHERE
            preStatement.setInt(11, item.getId_producto()); 

            preStatement.executeUpdate();

        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "delete from productos where id=?";
        try {
            preStatement = connection.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
