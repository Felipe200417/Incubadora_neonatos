package pi;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import panamahitek.Arduino.PanamaHitek_Arduino;

public class PI extends JFrame implements SerialPortEventListener {

    JButton jbDatosBB, jbDatosAmbiente, jbParar, jbModo, jbFormularios;
    String TemperaturaActual = "", TemperaturaPID = "", PesoBascula = "";
    boolean LUZ = false, VENTILADOR = false;
    String portName = "COM11";
    int baudRate = 9600;
    String[] ESP32 = new String[4];
    String receivedData, dato_a_enviar;
    String[] Datareceived_ESP32 = new String[9];
    JLabel TempAmb = null;
    JLabel TempAmb_1 = null;
    JLabel Peso_1 = null;
    JLabel TempHum = null;
    JLabel TempBebe = null;
    JLabel ECG = null;
    JLabel Oxigeno_Sangre = null;
    JLabel Peso = null;
    JLabel Temp_PID = null;
    JFrame actual;
    boolean isAlert = false;
    
    PanamaHitek_Arduino recepcion_ESP = new PanamaHitek_Arduino();

    public PI() {

        super("Incubadora");

        try {
            recepcion_ESP.arduinoRXTX(portName, baudRate, this); // Reemplaza "COM3" con el puerto COM adecuado
        } catch (Exception ex) {
            Logger.getLogger(PI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setSize(900, 800);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 51, 153));

        setLayout(null);
        actual = this;
        CrearGUI();

        setVisible(true);
    }

    public void CrearGUI() {

        ImageIcon ImgMenu = new ImageIcon(getClass().getResource("../Imagenes/Menu.png"));
        ImageIcon ImgJb1 = new ImageIcon(getClass().getResource("../Imagenes/Boton1.png"));
        ImageIcon ImgJb2 = new ImageIcon(getClass().getResource("../Imagenes/Boton2.png"));
        ImageIcon ImgJb3 = new ImageIcon(getClass().getResource("../Imagenes/Boton3.png"));
        ImageIcon ImgJb4 = new ImageIcon(getClass().getResource("../Imagenes/Boton4.png"));
        ImageIcon ImgJb5 = new ImageIcon(getClass().getResource("../Imagenes/Boton5.png"));
        ImageIcon ImgJb6 = new ImageIcon(getClass().getResource("../Imagenes/Boton6.png"));

        JLabel jlMenu = new JLabel(ImgMenu);
        jlMenu.setBounds(230, 10, 500, 120);
        add(jlMenu);

        jbDatosBB = new JButton(ImgJb1);
        jbDatosBB.setBounds(300, 150, 300, 60);
        jbDatosBB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventoDatosBB();
            }
        });
        add(jbDatosBB);

        jbDatosAmbiente = new JButton(ImgJb2);
        jbDatosAmbiente.setBounds(300, 250, 300, 60);
        jbDatosAmbiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventoDatosAmbiente();
            }
        });
        add(jbDatosAmbiente);

        jbParar = new JButton(ImgJb4);
        jbParar.setBounds(300, 350, 300, 90);
        jbParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventoParar();
            }
        });
        add(jbParar);

        jbModo = new JButton(ImgJb5);
        jbModo.setBounds(300, 480, 300, 60);
        jbModo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventoModo();
            }
        });
        add(jbModo);

        jbFormularios = new JButton(ImgJb6);
        jbFormularios.setBounds(300, 580, 300, 60);
        jbFormularios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventoFormulario();
            }
        });
        add(jbFormularios);

    }

    public void EventoDatosBB() {
        actual = new DatosBebe(this);
        setVisible(false);
    }

    public void EventoDatosAmbiente() {

        actual = new DatosAmbiente(this);
        setVisible(false);
    }

    public void EventoParar() {
        try {
                    ESP32[0] = "0";
                    dato_a_enviar = ","+ESP32[0]+","+"\n";
                    System.out.println(dato_a_enviar);
                    recepcion_ESP.sendData(dato_a_enviar);
                } catch (UnsupportedCommOperationException | IOException | NoSuchPortException | PortInUseException ex) {  
                } catch (Exception ex) {
                    Logger.getLogger(Modo.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Se apago todo!!!");
    }

    public void EventoModo() {
        actual = new VentanaLogin(this);
        setVisible(false);
    }

    public void EventoFormulario() {
        actual = new Entidad(this);
        setVisible(false);
    }

    public static void main(String[] args) {
        PI pr = new PI();
        //pr.initialize();
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        if (recepcion_ESP.isMessageAvailable()) {

            if (TempAmb != null) {
                TempAmb.setText(Datareceived_ESP32[0]);
                TempHum.setText(Datareceived_ESP32[6]);

            }
            
            if (TempBebe != null) {
                Peso.setText(Datareceived_ESP32[1]);
                TempBebe.setText(Datareceived_ESP32[3]);
                Oxigeno_Sangre.setText(Datareceived_ESP32[5]);

            }
            
            if(Peso_1 != null){
                Peso_1.setText(Datareceived_ESP32[1]);
                TempAmb_1.setText(Datareceived_ESP32[0]);
                Temp_PID.setText(Datareceived_ESP32[8]);
            }
            
            
            TemperaturaActual = Datareceived_ESP32[0];
            PesoBascula = Datareceived_ESP32[1];     
            receivedData = recepcion_ESP.printMessage();
            Datareceived_ESP32 = receivedData.split(",");
            if(Datareceived_ESP32[7].equals("4") && !isAlert){
                new AlertaDialog(this,  "ALERTA", "APERTURA INCUBADORA");
                isAlert = true;
            }
            System.out.println(Datareceived_ESP32[0]);
            System.out.println(Datareceived_ESP32[1]);
            System.out.println(Datareceived_ESP32[2]);
            System.out.println(Datareceived_ESP32[3]);
            System.out.println(Datareceived_ESP32[4]);
            System.out.println(Datareceived_ESP32[5]);
            System.out.println(Datareceived_ESP32[6]);
            System.out.println(Datareceived_ESP32[7]);
            System.out.println(Datareceived_ESP32[8]);
            System.out.println("");
           
        }
    }

}
