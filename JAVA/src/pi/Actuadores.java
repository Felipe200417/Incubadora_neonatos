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
    JButton jbVolver, AcLUZ, AcVentilador, AcHumnificador, jbIngreso, jbIngresoPeso, jbTara, jbMode;
    JTextField jtTempESP, jtPesoESP;
    double TemperaturaESP, PesoESP;
    String dato_a_enviar = "";
    JLabel OFF1, ON1;
    JLabel OFF2, ON2;

    public Actuadores(Modo obj2, PI pi1) {

        super("Actuadores");
        pi = pi1;
        ob = obj2;
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
        jbVolver.setBounds(50, 690 - 110, 54, 54);
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

        Salir();

        ImageIcon Tara = new ImageIcon(getClass().getResource("../Imagenes/BotonTara.png"));
        JButton jbTara = new JButton(Tara);
        jbTara.setBounds(800, 560, 200, 57);
        jbTara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                    pi.ESP32[2] = "1";
                    dato_a_enviar = "," + pi.ESP32[0] + "," + pi.ESP32[1] + "," + pi.ESP32[2]+","+"\n";
                    pi.recepcion_ESP.sendData(dato_a_enviar);
                    JOptionPane.showMessageDialog(null, "Hecho");
                } catch (Exception ex) {
                    Logger.getLogger(Actuadores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(jbTara);

        ImageIcon Mode = new ImageIcon(getClass().getResource("../Imagenes/BotonMode.png"));
        JButton jbMode = new JButton(Mode);
        jbMode.setBounds(1100, 560, 190, 57);
        jbMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });
        add(jbMode);

        jtTempESP = new JTextField();
        jtTempESP.setBounds(1080, 265, 150, 30);
        add(jtTempESP);

        jtPesoESP = new JTextField();
        jtPesoESP.setBounds(1000, 450, 100, 30);
        add(jtPesoESP);
        

        JButton jbIngresoPeso = new JButton("Guardar");
        jbIngresoPeso.setBounds(1130, 450, 100, 30);
        jbIngresoPeso.setMnemonic('G');
        jbIngresoPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbIngresoPeso();
            }
        });
        add(jbIngresoPeso);

        JButton jbIngreso = new JButton("Guardar");
        jbIngreso.setBounds(1080, 305, 150, 30);
        jbIngreso.setMnemonic('G');
        jbIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbIngreso();
            }
        });
        add(jbIngreso);

        String tempAc = "" + pi.TemperaturaActual + " °C";
        String tempPID = "" + pi.TemperaturaPID + " °C";
        String PesoPID = "" + pi.PesoBascula + " °gr";

        JLabel jlPeso = new JLabel(PesoPID);
        jlPeso.setFont(new Font("Tahoma", Font.BOLD, 30));
        jlPeso.setBounds(835, 410, 300, 48);
        jlPeso.setForeground(Color.white);
        add(jlPeso);
        pi.Peso_1 = jlPeso;
        

        JLabel jlTemperaturaAc = new JLabel(tempAc);
        jlTemperaturaAc.setFont(new Font("Tahoma", Font.BOLD, 30));
        jlTemperaturaAc.setBounds(840, 160, 300, 48);
        jlTemperaturaAc.setForeground(Color.white);
        add(jlTemperaturaAc);
         pi.TempAmb_1 = jlTemperaturaAc;

        JLabel jlTemperaturaPID = new JLabel(tempPID);
        jlTemperaturaPID.setFont(new Font("Tahoma", Font.BOLD, 30));
        jlTemperaturaPID.setBounds(1075, 160, 300, 48);
        jlTemperaturaPID.setForeground(Color.white);
        add(jlTemperaturaPID);
        pi.Temp_PID = jlTemperaturaPID;

        ImageIcon DatosTecnicos = new ImageIcon(getClass().getResource("../Imagenes/DatosTecnicos.png"));
        JLabel jlDatosTecnicos = new JLabel(DatosTecnicos);
        jlDatosTecnicos.setBounds(810, 40, 450, 457);

        add(jlDatosTecnicos);

        ImageIcon ON = new ImageIcon(getClass().getResource("../Imagenes/ON.png"));
        ImageIcon OFF = new ImageIcon(getClass().getResource("../Imagenes/OFF.png"));

        OFF1 = new JLabel(OFF);
        OFF1.setBounds(130, 180, 100, 100);
        add(OFF1);

        ON1 = new JLabel(ON);
        ON1.setBounds(130, 180, 100, 100);
        add(ON1);

        if (!pi.LUZ) {
            OFF1.setVisible(true);
            ON1.setVisible(false);
        } else {
            OFF1.setVisible(false);
            ON1.setVisible(true);
        }

        OFF2 = new JLabel(OFF);
        OFF2.setBounds(530, 180, 100, 100);
        add(OFF2);
        ON2 = new JLabel(ON);
        ON2.setBounds(530, 180, 100, 100);
        add(ON2);
        if (!pi.VENTILADOR) {
            OFF2.setVisible(true);
            ON2.setVisible(false);
        } else {
            OFF2.setVisible(false);
            ON2.setVisible(true);
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
                        dato_a_enviar = "," + pi.ESP32[0] + "," + pi.ESP32[1] + "," + "\n";
                        System.out.println(dato_a_enviar);
                        pi.recepcion_ESP.sendData(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {
                    } catch (Exception ex) {
                        Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pi.LUZ = true;
                    OFF1.setVisible(false);
                    ON1.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Se apago la luz UV");
                    try {
                        pi.ESP32[1] = "20";
                        dato_a_enviar = "," + pi.ESP32[0] + "," + pi.ESP32[1] + "," + "\n";
                        System.out.println(dato_a_enviar);
                        pi.recepcion_ESP.sendData(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {
                    } catch (Exception ex) {
                        Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pi.LUZ = false;
                    OFF1.setVisible(true);
                    ON1.setVisible(false);
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
                        dato_a_enviar = "," + pi.ESP32[0] + "," + pi.ESP32[1] + "," + "\n";
                        System.out.println(dato_a_enviar);
                        pi.recepcion_ESP.sendData(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {
                    } catch (Exception ex) {
                        Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pi.VENTILADOR = true;
                    OFF2.setVisible(false);
                    ON2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Se apago la  ventilador");
                    try {
                        pi.ESP32[1] = "10";
                        dato_a_enviar = "," + pi.ESP32[0] + "," + pi.ESP32[1] + "," + "\n";
                        System.out.println(dato_a_enviar);
                        pi.recepcion_ESP.sendData(dato_a_enviar);
                    } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {
                    } catch (Exception ex) {
                        Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pi.VENTILADOR = false;
                    OFF2.setVisible(true);
                    ON2.setVisible(false);
                }
            }
        });
        add(AcVentilador);

    }

    void evento_jbIngreso() {
        String jtEsp = jtTempESP.getText();
        TemperaturaESP = Double.parseDouble(jtEsp);
         pi.ESP32[2] = "0";
        pi.ESP32[3] = jtTempESP.getText();
        dato_a_enviar = "," + pi.ESP32[0] + "," + pi.ESP32[1] + "," + pi.ESP32[2]+","+pi.ESP32[3]+",\n";
        try {
            pi.recepcion_ESP.sendData(dato_a_enviar);
        } catch (Exception ex) {
            Logger.getLogger(Actuadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Hecho");
        jtTempESP.setText(" ");
    }

    void evento_jbIngresoPeso() {
        String jtPesoEsp = jtPesoESP.getText();
        PesoESP = Double.parseDouble(jtPesoEsp);
        JOptionPane.showMessageDialog(null, "Hecho");
        jtPesoESP.setText(" ");
    }

}
