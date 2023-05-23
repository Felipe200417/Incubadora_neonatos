package pi;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class DatosAmbiente extends JFrame{

    PI obj;
    JButton jbVolver;
    String TempAmb = "", Humedad = "";
    String TempAmbiente;
    String Hum;

    public DatosAmbiente(PI obj1){

        super("Datos del ambiente");
        obj = obj1;
        setSize(900, 700);
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
        jbVolver.setBounds(800, 590, 54, 54);
        jbVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                obj.setVisible(true);
            }
        });
        add(jbVolver);

    }

    public void CrearGUI() {
        

        JLabel jlTemp = new JLabel("");
        jlTemp.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlTemp.setBounds(500, 165, 300, 48);
        jlTemp.setForeground(Color.white);
        add(jlTemp);
        obj.TempAmb = jlTemp;

        JLabel jlHum = new JLabel(Hum);
        jlHum.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlHum.setBounds(500, 230, 300, 48);
        jlHum.setForeground(Color.white);
        add(jlHum);
        obj.TempHum = jlHum;

        ImageIcon Img1 = new ImageIcon(getClass().getResource("../Imagenes/DatosAmbiente.png"));
        JLabel jlTitulo = new JLabel(Img1);
        jlTitulo.setBounds(250, 0, 500, 300);
        add(jlTitulo);
        Salir();

    }


}
