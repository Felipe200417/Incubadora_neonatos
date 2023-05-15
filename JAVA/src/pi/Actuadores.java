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
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Actuadores extends JFrame {

    PI pi;
    Modo ob;
    JButton jbVolver, AcLUZ, AcVentilador, AcHumnificador,  jbIngreso,jbTara,jbMode;
    JTextField jtTempESP;
    float TemperaturaActual = 00, TemperaturaPID = 00;
    double TemperaturaESP;
    String dato_a_enviar = "";
    
    
  

    public Actuadores(Modo obj2, PI pi1) {

        super("Actuadores");
        pi = pi1;
        ob = obj2;
        pi.ESP32[1] = " ";
        setSize(1500, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(128, 128, 128));
        setLayout(null);
        CrearGUI();

        setVisible(true);
    }

    public void Salir() {
        


        ImageIcon ImgVolver = new ImageIcon(getClass().getResource("../Imagenes/volver.png"));
        jbVolver = new JButton(ImgVolver);
        jbVolver.setBounds(1300, 450, 54, 54);
        jbVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                ob.setVisible(true);
            }
        });
        add(jbVolver);

    }

    public void CrearGUI() {
        
        ImageIcon Tara = new ImageIcon(getClass().getResource("../Imagenes/BotonTara.png")); 
        JButton jbTara = new JButton(Tara);
        jbTara.setBounds(850, 500, 200, 57);
        jbTara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Hecho");
               try {
                            pi.ESP32[2] = "1";
                            dato_a_enviar = ","+pi.ESP32[0]+","+pi.ESP32[1]+","+pi.ESP32[2]+"\n";
                            System.out.println(dato_a_enviar);
                            pi.send(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                    }
            }
        });
        add(jbTara);
        
        ImageIcon Mode = new ImageIcon(getClass().getResource("../Imagenes/BotonMode.png")); 
        JButton jbMode = new JButton(Mode);
        jbMode.setBounds(1100, 500, 190, 57);
        jbMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Hecho");
                try {
                            pi.ESP32[2] = "2";
                            dato_a_enviar = ","+pi.ESP32[0]+","+pi.ESP32[1]+","+pi.ESP32[2]+"\n";
                            System.out.println(dato_a_enviar);
                            pi.send(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                    }
            }
        });
        add(jbMode);
        
        
        jtTempESP = new JTextField();
        jtTempESP.setBounds(1120,360,150,30);
        add(jtTempESP);
        
        JButton jbIngreso = new JButton("Guardar");
        jbIngreso.setBounds(1120, 400, 150, 30);
        jbIngreso.setMnemonic('I');
        jbIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbIngreso();
            }
        });
        add(jbIngreso);
        
        
        
        String tempAc = "" + TemperaturaActual + " °C";
        String tempPID = "" + TemperaturaPID + " °C";
        
        JLabel jlTemperaturaAc = new JLabel(tempAc);
        jlTemperaturaAc.setFont(new Font("Tahoma", Font.BOLD, 30));
        jlTemperaturaAc.setBounds(860, 210, 300, 48);
        jlTemperaturaAc.setForeground(Color.white);
        add(jlTemperaturaAc);
        
        JLabel jlTemperaturaPID = new JLabel(tempPID);
        jlTemperaturaPID.setFont(new Font("Tahoma", Font.BOLD, 30));
        jlTemperaturaPID.setBounds(1170, 210, 300, 48);
        jlTemperaturaPID.setForeground(Color.white);
        add(jlTemperaturaPID);
        
        ImageIcon DatosTecnicos = new ImageIcon(getClass().getResource("../Imagenes/DatosTecnicos.png")); 
        JLabel jlDatosTecnicos = new JLabel(DatosTecnicos);
        jlDatosTecnicos.setBounds(810, 40, 530, 381);

        add(jlDatosTecnicos);

        ImageIcon ON = new ImageIcon(getClass().getResource("../Imagenes/ON.png"));
        ImageIcon OFF = new ImageIcon(getClass().getResource("../Imagenes/OFF.png"));

        if (!pi.LUZ) {
            JLabel OFF1 = new JLabel(OFF);
            OFF1.setBounds(130, 180, 100, 100);
            add(OFF1);
        } else {
            JLabel ON1 = new JLabel(ON);
            ON1.setBounds(130, 180, 100, 100);
            add(ON1);
        }

        if (!pi.VENTILADOR) {
            JLabel OFF1 = new JLabel(OFF);
            OFF1.setBounds(530, 180, 100, 100);
            add(OFF1);
        } else {
            JLabel ON1 = new JLabel(ON);
            ON1.setBounds(530, 180, 100, 100);
            add(ON1);
        }

        ImageIcon Img1 = new ImageIcon(getClass().getResource("../Imagenes/TituloAct.png"));
        JLabel jlTitulo = new JLabel(Img1);
        jlTitulo.setBounds(150, 10, 500, 69);

        add(jlTitulo);

        ImageIcon Img2 = new ImageIcon(getClass().getResource("../Imagenes/LUZON.png"));
        JButton AcLUZ = new JButton(Img2);
        AcLUZ.setOpaque(false);
        AcLUZ.setContentAreaFilled(false);
        AcLUZ.setBorderPainted(false);
        AcLUZ.setBounds(30, 200, 300, 356);

        AcLUZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pi.LUZ) {
                    JOptionPane.showMessageDialog(null, "Se encendio la luz UV");
                    try {
                        pi.ESP32[1] = "21";
                        dato_a_enviar = ","+pi.ESP32[0]+","+pi.ESP32[1]+","+"\n";
                        System.out.println(dato_a_enviar);
                        pi.send(dato_a_enviar);
                } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                }
                    pi.LUZ = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Se apago la luz UV");
                    
                        try {
                            pi.ESP32[1] = "20";
                            dato_a_enviar = ","+pi.ESP32[0]+","+pi.ESP32[1]+","+"\n";
                            System.out.println(dato_a_enviar);
                            pi.send(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                    }
                    pi.LUZ = false;
                }
            }
        });

        add(AcLUZ);

        ImageIcon Img3 = new ImageIcon(getClass().getResource("../Imagenes/Ventilador.png"));
        JButton AcVentilador = new JButton(Img3);
        AcVentilador.setOpaque(false);
        AcVentilador.setContentAreaFilled(false);
        AcVentilador.setBorderPainted(false);
        AcVentilador.setBounds(400, 200, 356, 356);
        AcVentilador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pi.VENTILADOR) {
                    JOptionPane.showMessageDialog(null, "Se encendio la ventilador");
                        try {
                            pi.ESP32[1] = "11";
                            dato_a_enviar = ","+pi.ESP32[0]+","+pi.ESP32[1]+","+"\n";
                            System.out.println(dato_a_enviar);
                            pi.send(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                    }
                    pi.VENTILADOR = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Se apago la  ventilador");
                        try {
                            pi.ESP32[1] = "10";
                            dato_a_enviar = ","+pi.ESP32[0]+","+pi.ESP32[1]+","+"\n";
                            System.out.println(dato_a_enviar);
                            pi.send(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                    }
                    pi.VENTILADOR = false;
                }
            }
        });
        add(AcVentilador);

        Salir();

    }
    
    void evento_jbIngreso(){
        String jtEsp = jtTempESP.getText();
        TemperaturaESP = Double.parseDouble(jtEsp);
        JOptionPane.showMessageDialog(null, "Hecho");
        jtTempESP.setText(" ");
    }

}
