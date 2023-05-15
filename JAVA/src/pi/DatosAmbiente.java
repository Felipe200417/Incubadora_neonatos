
package pi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class DatosAmbiente extends JFrame{
    
    PI obj;
    JButton jbVolver;
    float TempAmb = 0 , Humedad = 0;
      
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
        
        
        String Hum = ""+Humedad+"%";
        String TempAmbiente = ""+TempAmb+" °C";
        
        JLabel jlTemp = new JLabel(TempAmbiente);
        jlTemp.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlTemp.setBounds(500, 165, 300, 48);
        jlTemp.setForeground(Color.white);
        add(jlTemp);
        
        JLabel jlHum = new JLabel(Hum);
        jlHum.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlHum.setBounds(500, 230, 300, 48);
        jlHum.setForeground(Color.white);
        add(jlHum);
        
        ImageIcon Img1 = new ImageIcon(getClass().getResource("../Imagenes/DatosAmbiente.png"));
        JLabel jlTitulo = new JLabel(Img1);
        jlTitulo.setBounds(250, 0, 500, 300);
        add(jlTitulo);

        Salir();

    } 
}
