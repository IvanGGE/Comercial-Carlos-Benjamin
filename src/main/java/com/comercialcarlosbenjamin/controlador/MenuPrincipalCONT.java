package com.comercialcarlosbenjamin.controlador;

import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import com.comercialcarlosbenjamin.vista.MenuPrincipalGUI;



public class MenuPrincipalCONT {
    Usuarios usuario = new Usuarios();
    MenuPrincipalGUI gui = new MenuPrincipalGUI();
    public MenuPrincipalCONT(MenuPrincipalGUI gui, Usuarios usuario){
        this.usuario = usuario;
        this.gui = gui;
    }
}
