/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Felipe
 */
public class AlertaDialog extends JDialog{
    
    
    PI pi;
    String mensaje, alerta;
    int width = 300;
    public AlertaDialog(PI pi, String a, String m){
        super(pi, "Alerta", false);
        alerta = a;
        this.pi = pi;
        mensaje = m;
        setSize(width, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        
        crearGUI();
        setVisible(true);
    }
    
    public void crearGUI(){
        
        JLabel titulo = new JLabel(alerta);
        titulo.setBounds(0, 0, width, 40);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setBackground(Color.CYAN);
        add(titulo);
        
        JLabel jlMensaje = new JLabel(mensaje);
        jlMensaje.setBounds(0, 50, width, 45);
        jlMensaje.setHorizontalAlignment(JLabel.CENTER);
        jlMensaje.setFont(new Font("Arial", Font.BOLD, 12));
        add(jlMensaje);
        
        
        JButton jbCerrar = new JButton("Cerrar");
        jbCerrar.setBounds(100, 120, 100, 30);
        jbCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbCerrar();
            }
        });
        
        add(jbCerrar);
    }
    
    public void evento_jbCerrar(){
        setVisible(false);
        pi.isAlert = false;
        dispose();
    }
    
}
