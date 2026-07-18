package com.comercialcarlosbenjamin.controlador;

import com.comercialcarlosbenjamin.modelo.dao.Conexion;
import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import com.comercialcarlosbenjamin.vista.MenuPrincipalGUI;
import com.comercialcarlosbenjamin.vista.paneles.PanelInicio;
import com.comercialcarlosbenjamin.vista.paneles.PanelUsuarios;
import com.comercialcarlosbenjamin.vista.paneles.PanelProductos;
import com.comercialcarlosbenjamin.vista.paneles.PanelVentas;
import com.comercialcarlosbenjamin.controlador.UsuariosCONT;
import com.comercialcarlosbenjamin.modelo.dao.UsuariosDAO;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalCONT implements ActionListener{
    private MenuPrincipalGUI gui;
    private CardLayout card;
    private Usuarios usuario;
    private UsuariosCONT usuariosCONT;
    private UsuariosDAO usuariosDAO = new UsuariosDAO();
    private PanelInicio panelInicio = new PanelInicio();
    private PanelUsuarios panelUsuarios = new PanelUsuarios();
    private PanelProductos panelProductos = new PanelProductos();
    private PanelVentas panelVentas = new PanelVentas();
    public MenuPrincipalCONT(MenuPrincipalGUI gui, Usuarios usuario){
        this.usuario = usuario;
        this.gui = gui;
        this.card = (CardLayout) gui.getPanelPrincipal().getLayout();
        
        gui.panelPrincipal.add(panelInicio, "inicio");
        gui.panelPrincipal.add(panelUsuarios, "usuarios");
        gui.panelPrincipal.add(panelProductos,"productos");
        gui.panelPrincipal.add(panelVentas, "ventas");
        //Tambien se puede hacer asi: gui.panelPrincipal.add(new PanelVentas(), "ventas");
        this.gui.itemInicio.addActionListener(this);
        this.gui.itemAgregarUsuario.addActionListener(this);
        this.gui.itemAgregarProducto.addActionListener(this);
        this.gui.itemVender.addActionListener(this);
        card.show(gui.getPanelPrincipal(), "inicio");
        //-----Las siguientes 3 lineas arreglan el error de tamaño 0x0 de la GUI-----//
        gui.getPanelPrincipal().revalidate();
        gui.getPanelPrincipal().repaint();
        gui.pack();
        //---O bien, ponemos un tamaño definido nuevamente desde el controlador para solucionarlo---//
        //gui.setSize(800, 600); //
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == gui.itemInicio){
            card.show(gui.getPanelPrincipal(), "inicio");
        }
        else if(e.getSource() == gui.itemAgregarUsuario){
            if(usuariosCONT == null){
                usuariosCONT = new UsuariosCONT(panelUsuarios, usuariosDAO);
            }
            card.show(gui.getPanelPrincipal(), "usuarios");
        }
        else if(e.getSource() == gui.itemAgregarProducto){
            card.show(gui.getPanelPrincipal(), "productos");
        }
        else if(e.getSource() == gui.itemVender){
            card.show(gui.getPanelPrincipal(), "ventas");
        }
    }
}
