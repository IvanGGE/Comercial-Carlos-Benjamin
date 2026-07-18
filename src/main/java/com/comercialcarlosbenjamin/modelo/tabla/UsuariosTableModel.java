package com.comercialcarlosbenjamin.modelo.tabla;

import javax.swing.table.AbstractTableModel;
import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import java.util.ArrayList;
import java.util.List;


public class UsuariosTableModel extends AbstractTableModel {
    private List<Usuarios> lista = new ArrayList<>();
    private final String[] columnas = {"ID", "Nombre", "Usuario", "Rol"};

    public void setLista(List<Usuarios> lista) {
        this.lista = lista;
        fireTableDataChanged(); // Le avisa a la JTable que se redibuje de forma óptima
    }

    public Usuarios getUsuarioByRow(int fila) {
        if (fila >= 0 && fila < lista.size()) {
            return lista.get(fila);
        }
        return null;
    }

    @Override
    public int getRowCount() { return lista.size(); }

    @Override
    public int getColumnCount() { return columnas.length; }

    @Override
    public String getColumnName(int column) { return columnas[column]; }

    @Override
    public Object getValueAt(int fila, int columna) {
        Usuarios usuario = lista.get(fila);
        switch (columna) {
            case 0: return usuario.getId_usuario();
            case 1: return usuario.getNombre();
            case 2: return usuario.getUsuario();
            case 3: return usuario.getRol();
            default: return null;
        }
    }
}
