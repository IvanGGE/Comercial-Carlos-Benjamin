package com.comercialcarlosbenjamin.controlador;

import com.comercialcarlosbenjamin.modelo.dao.UsuariosDAO;
import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import com.comercialcarlosbenjamin.modelo.tabla.UsuariosTableModel;
import com.comercialcarlosbenjamin.vista.paneles.PanelUsuarios; // Ajusta el paquete según tu proyecto

import java.util.List;

public class UsuariosCONT {
    
    private final PanelUsuarios vista;
    private final UsuariosDAO dao;
    private final UsuariosTableModel modeloTabla;

    public UsuariosCONT(PanelUsuarios vista, UsuariosDAO dao) {
        this.vista = vista;
        this.dao = dao;
        this.modeloTabla = new UsuariosTableModel();
        
        configurarTabla();
        configurarListeners();
    }

    private void configurarTabla() {
        this.vista.tblUsuarios.setModel(modeloTabla);
        // Ocultar ID
        this.vista.tblUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tblUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    private void configurarListeners() {
        // Expresiones Lambda para botones
        //this.vista.btnNuevo.addActionListener(e -> limpiarCampos());
        this.vista.btnGuardar.addActionListener(e -> guardarUsuario());
        this.vista.btnEliminar.addActionListener(e -> eliminarUsuario());
        //this.vista.txtBuscar.addActionListener(e -> buscar()); // Se dispara al dar Enter en el buscador

        // Listener de selección de tabla
        this.vista.tblUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.tblUsuarios.getSelectedRow();
                Usuarios seleccionado = modeloTabla.getUsuarioByRow(fila);
                if (seleccionado != null) {
                    vista.txtNombre.setText(seleccionado.getNombre());
                    vista.txtUsuario.setText(seleccionado.getUsuario());
                    vista.txtContraseña.setText(seleccionado.getContrasena());
                    // --- MOSTRAR EL ROL EN LOS RADIO BUTTONS ---
                    switch (seleccionado.getRol()) {
                        case 1:
                            vista.rbtnGerente.setSelected(true);
                            break;
                        case 2:
                            vista.rbtnVendedor.setSelected(true);
                            break;
                        case 3:
                            vista.rbtnEncargadoDeDeposito.setSelected(true);
                            break;
                        default:
                            vista.rolBtnGroup.clearSelection(); // Desmarca todos si es un rol desconocido
                            break;
                    }
                    actualizarModoFormulario();
                }
            }
        });
        listar("");
    }

    private void listar(String filtro) {
        List<Usuarios> lista = dao.listar(filtro);
        modeloTabla.setLista(lista);
        
        // ESTO SOLUCIONA EL GLITCH VISUAL:
        // Fuerza al contenedor a recalcular posiciones y redibujar la tabla en pantalla
        //this.vista.revalidate();
        //this.vista.repaint();
    }
    
    private void buscar() {
        //listar(vista.txtBuscar.getText());
    }

    private void guardarUsuario() {
        Usuarios u = new Usuarios();
        u.setNombre(vista.txtNombre.getText());
        u.setUsuario(vista.txtUsuario.getText());
        u.setContrasena(vista.txtContraseña.getText());
            // --- CAPTURA DEL ROL DESDE LOS RADIO BUTTONS ---
        if (vista.rolBtnGroup.getSelection() != null) {
            // Recupera el "ActionCommand" del botón seleccionado
            String idRolStr = vista.rolBtnGroup.getSelection().getActionCommand();
            u.setRol(Integer.parseInt(idRolStr));
        } else {
            // Manejo por si no seleccionaron ningún rol (ej. por defecto Vendedor)
            u.setRol(2); 
        }
        // -----------------------------------------------
            //Logica para saber si guardara o actualizara
        int filaSeleccionada = vista.tblUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            dao.crear(u);
        } else {
            Usuarios usuarioExistente = modeloTabla.getUsuarioByRow(filaSeleccionada);
            u.setId_usuario(usuarioExistente.getId_usuario());
            dao.actualizar(u);
        }

        listar("");
        limpiarCampos();
    }

    private void eliminarUsuario() {
        int fila = vista.tblUsuarios.getSelectedRow();
        if (fila != -1) {
            Usuarios u = modeloTabla.getUsuarioByRow(fila);
            dao.eliminar(u.getId_usuario());
            listar("");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtUsuario.setText("");
        vista.txtContraseña.setText("");
        vista.rolBtnGroup.clearSelection();
        vista.tblUsuarios.clearSelection();
        actualizarModoFormulario();
    }
    
    private void actualizarModoFormulario() {
    boolean hayEdicion = vista.tblUsuarios.getSelectedRow() != -1;
    vista.btnGuardar.setText(hayEdicion ? "Modificar" : "Guardar");
}
}