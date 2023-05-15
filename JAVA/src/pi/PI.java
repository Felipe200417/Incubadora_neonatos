
package pi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import gnu.io.*;
import java.io.IOException;
import java.io.OutputStream;



public class PI extends JFrame {
    

    
   
    JButton jbDatosBB , jbDatosAmbiente, jbParar, jbModo,jbFormularios;
    boolean LUZ = false , VENTILADOR = false;
    String portName = "COM11";
    int baudRate = 9600;
    String[] ESP32 = new String[3];
    
    
    public PI() {
        
        
        super("Incubadora");
        
        
        setSize(900, 800);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground( new Color(0,51,153));

        setLayout(null);
        CrearGUI();

        setVisible(true);
    }
    






    public void send(String data) throws UnsupportedCommOperationException, IOException, NoSuchPortException, PortInUseException {
        
            // Abre el puerto serial
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commPort = portIdentifier.open(PI.class.getName(), 1000);
                
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            OutputStream outputStream = serialPort.getOutputStream();

            // Envía los datos a la ESP32
            outputStream.write(data.getBytes());

            // Cierra el puerto serial
            outputStream.close();
            serialPort.close();
     }
    


    public void CrearGUI(){
        
        
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
    
    public void  EventoDatosBB(){
        DatosBebe db = new DatosBebe(this);
        setVisible(false);
    }
    
    public void  EventoDatosAmbiente(){
        DatosAmbiente da = new DatosAmbiente(this);
        setVisible(false);
    }


    public void  EventoParar(){
        JOptionPane.showMessageDialog(null, "Se apago todo!!!");
    }
    
    public void  EventoModo(){
        VentanaLogin mp = new VentanaLogin(this);
        setVisible(false);
    }
    
    public void  EventoFormulario(){
        Entidad fr = new Entidad(this);
        setVisible(false);
    }
     
    
    public static void main(String[] args) {
        PI pr = new PI();
    }
    
}
