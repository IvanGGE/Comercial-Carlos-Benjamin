package com.comercialcarlosbenjamin;

import com.comercialcarlosbenjamin.controlador.LoginCONT;
import com.comercialcarlosbenjamin.modelo.dao.UsuariosDAO;
import com.formdev.flatlaf.FlatLightLaf;
import com.comercialcarlosbenjamin.vista.LoginGUI;

public class ComercialCarlosBenjamin {

    public static void main(String[] args) {
        LoginGUI gui = new LoginGUI();
        UsuariosDAO dao = new UsuariosDAO();
        LoginCONT controlador = new LoginCONT(gui, dao);
        try {
            // Aplicar el tema
            FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Error al iniciar FlatLaf");
        }

        // Ahora se inicia la interfaz
        java.awt.EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
    }
}
