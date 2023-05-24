package pi;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Modo extends JFrame{

    VentanaLogin obj;
    PI pi;
    JButton jbVolver, jbManual, jbAuto;
    String dato_a_enviar = "";
    

    public Modo(VentanaLogin obj1 , PI pi1) {
        
        super("Modo");
        
  
        pi = pi1;
        obj = obj1;
         try {
                    pi.ESP32[0] = "0";
                    dato_a_enviar = ","+pi.ESP32[0]+","+"\n";
                    System.out.println(dato_a_enviar);
                    pi.recepcion_ESP.sendData(dato_a_enviar);
                } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                } catch (Exception ex) {
                    Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
        }
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 51, 153));
        setLayout(null);
        CrearGUI();
        setVisible(true);

    }

    public void Salir() {
        ImageIcon ImgVolver = new ImageIcon(getClass().getResource("../Imagenes/volver.png"));
        jbVolver = new JButton(ImgVolver);
        jbVolver.setBounds(400, 290, 54, 54);
        jbVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                pi.setVisible(true);
            }
        });
        add(jbVolver);

    }

    public void CrearGUI() {

        ImageIcon Img1 = new ImageIcon(getClass().getResource("../Imagenes/TituloModo.png"));

        JLabel jlModo = new JLabel(Img1);
        jlModo.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlModo.setBounds(70, 0, 350, 150);
        jlModo.setForeground(Color.white);
        add(jlModo);

        ImageIcon Img2 = new ImageIcon(getClass().getResource("../Imagenes/BotonMoAuto.png"));
        ImageIcon Img3 = new ImageIcon(getClass().getResource("../Imagenes/BotonMoManual.png"));

        JButton jbAuto = new JButton(Img2);
        jbAuto.setBounds(70, 160, 300, 60);
        jbAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Modo automatico activado");
                try {
                    pi.ESP32[0] = "2";
                    dato_a_enviar = ","+pi.ESP32[0]+","+"\n";
                    System.out.println(dato_a_enviar);
                    pi.recepcion_ESP.sendData(dato_a_enviar);
                } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                } catch (Exception ex) {
                    Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
                }
                setVisible(false);
                dispose();
                pi.setVisible(true);

            }
        });
        add(jbAuto);

        JButton jbManual = new JButton(Img3);
        jbManual.setBounds(70, 240, 255, 60);
        jbManual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pi.ESP32[0] = "1";
                    dato_a_enviar = pi.ESP32[0]+","+"\n";
                    System.out.print(dato_a_enviar);
                    pi.recepcion_ESP.sendData(dato_a_enviar);
                } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                } catch (Exception ex) {
                    Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
                }
                EventoActuadores();

            }
        });
        add(jbManual);

        Salir();

    }

    public void EventoActuadores() {
        
        Actuadores as = new Actuadores(this,pi);
        setVisible(false);
    }

}
