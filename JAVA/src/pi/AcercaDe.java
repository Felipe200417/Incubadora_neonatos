package pi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AcercaDe extends JDialog {
    
    JButton jbCerrar;
    JLabel jlImagen, jlImagen2, jlImagen3;
    
    public AcercaDe(Entidad e){
        super(e, "Acerca de...", true);
        setSize(500, 400);
        setLocationRelativeTo(e);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.lightGray);

        crearGUI();
        
        setVisible(true);
    }

    private void crearGUI() {
        
        ImageIcon ImgProgramador1 = new ImageIcon(getClass().getResource("../Imagenes/mujer.png"));
        ImageIcon ImgA = new ImageIcon(ImgProgramador1.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        jlImagen = new JLabel(ImgA);
        jlImagen.setBounds(40, 30, 64, 64);
        add(jlImagen);
        
        JLabel j1 = new JLabel("Angeline Molina Muñoz - 260211023");
        j1.setBounds(75, 40, 250, 30);
        j1.setHorizontalAlignment(SwingConstants.CENTER);
        add(j1);
        
        JLabel j4 = new JLabel("angeline.molina01@uceva.edu.co");
        j4.setBounds(98, 65, 200, 30);
        j4.setHorizontalAlignment(SwingConstants.CENTER);
        add(j4);
        
        ImageIcon ImgProgramador2 = new ImageIcon(getClass().getResource("../Imagenes/hombre.png"));
        ImageIcon ImgB = new ImageIcon(ImgProgramador2.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        jlImagen2 = new JLabel(ImgB);
        jlImagen2.setBounds(40, 130, 64, 64);
        add(jlImagen2);
        
        JLabel j2 = new JLabel("Alberto José Duque Gámez - 260211021");
        j2.setBounds(85, 140, 250, 30);
        j2.setHorizontalAlignment(SwingConstants.CENTER);
        add(j2);
         
        JLabel j5 = new JLabel("alberto.duque01@uceva.edu.co");
        j5.setBounds(100, 160, 200, 30);
        j5.setHorizontalAlignment(SwingConstants.CENTER);
        add(j5);
        
        ImageIcon ImgProgramador3 = new ImageIcon(getClass().getResource("../Imagenes/padre.png"));
        ImageIcon ImgJj = new ImageIcon(ImgProgramador3.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        jlImagen3 = new JLabel(ImgJj);
        jlImagen3.setBounds(40, 230, 64, 64);
        add(jlImagen3);
        
        JLabel j6 = new JLabel("Stefanny Avila Varela-260211005");
        j6.setBounds(85, 240, 250, 30);
        j6.setHorizontalAlignment(SwingConstants.CENTER);
        add(j6);
        
        JLabel j7 = new JLabel("stefanny.avila01@uceva.edu.co");
        j7.setBounds(110, 260,200, 30);
        j7.setHorizontalAlignment(SwingConstants.CENTER);
        add(j7);
        
        jbCerrar = new JButton("Salir");
        jbCerrar.setBounds(290, 320, 130, 30);
        ImageIcon ImgCerrar = new ImageIcon(getClass().getResource("../Imagenes/salida.png"));
        jbCerrar.setIcon(new ImageIcon(ImgCerrar.getImage().getScaledInstance(38, jbCerrar.getHeight(), Image.SCALE_SMOOTH)));
        jbCerrar.setBackground(Color.DARK_GRAY);
        jbCerrar.setForeground(Color.WHITE);
        jbCerrar.setFont(new Font("Arial", Font.BOLD, 16));
        jbCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbCerrar();
            }

        });
        add(jbCerrar);
    }
    
    private void evento_jbCerrar() {
        setVisible(false);
        dispose();
    }
}
