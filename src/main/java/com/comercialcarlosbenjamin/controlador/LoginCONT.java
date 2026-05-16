package com.comercialcarlosbenjamin.controlador;

import com.comercialcarlosbenjamin.vista.LoginGUI;
import com.comercialcarlosbenjamin.modelo.dao.UsuariosDAO;
import com.comercialcarlosbenjamin.modelo.entidad.Usuarios;
import com.comercialcarlosbenjamin.vista.MenuPrincipalGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginCONT implements ActionListener{
    LoginGUI login = new LoginGUI();
    UsuariosDAO dao = new UsuariosDAO();
    
    public LoginCONT(LoginGUI login, UsuariosDAO dao){
        this.login = login;
        this.dao = dao;
        
        login.btnIniciarSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login.btnIniciarSesion){
            if(login.txtUsuario.getText().isEmpty() || login.txtContraseña.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Llene todos los campos");
            }
            else
            {
                String usuario = login.txtUsuario.getText();
                String contraseña = login.txtContraseña.getText();
                Usuarios usuarioLogueado = dao.iniciarSesion(usuario, contraseña);
                if(usuarioLogueado != null)
                {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + usuarioLogueado.getRol() + " " + usuarioLogueado.getUsuario());
                    MenuPrincipalGUI gui = new MenuPrincipalGUI();
                    MenuPrincipalCONT cont = new MenuPrincipalCONT(gui, usuarioLogueado);
                    gui.setVisible(true);
                    login.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                    login.txtUsuario.setText("");
                    login.txtContraseña.setText("");
                    login.txtUsuario.requestFocus();
                }
            }
        }
    }   
}
