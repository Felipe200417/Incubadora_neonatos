package pi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DatosBebe extends JFrame {

    PI obj;
    JButton jbVolver;
    float Temperatura = 00, peso = 00000;
    int Oxi = 0;
    

    public DatosBebe(PI obj1) {
        
        super("Datos del bebe");
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
        
        ImageIcon Img1 = new ImageIcon(getClass().getResource("../Imagenes/DatosBebe.png"));
        JLabel jlTitulo = new JLabel(Img1);
        jlTitulo.setBounds(300, 10, 300, 91);
        add(jlTitulo);
        
        String temp = "" + Temperatura + " °C";
        String oxi = ""+Oxi+" %SpO2";
        String Peso = ""+peso+" gr";
        
        JLabel jlTemperatura = new JLabel(temp);
        jlTemperatura.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlTemperatura.setBounds(220, 150, 300, 48);
        jlTemperatura.setForeground(Color.white);
        add(jlTemperatura);
        obj.TempBebe = jlTemperatura;
        
        
        ImageIcon Img2 = new ImageIcon(getClass().getResource("../Imagenes/Temperatura.png"));
        JLabel jlTemp = new JLabel(Img2);
        jlTemp.setBounds(10, 150, 300, 48);
        add(jlTemp);
        
        
        
        JLabel jlOxigeno = new JLabel(oxi);
        jlOxigeno.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlOxigeno.setBounds(220, 250, 300, 48);
        jlOxigeno.setForeground(Color.white);
        add(jlOxigeno);
        obj.Oxigeno_Sangre = jlOxigeno;
        
        ImageIcon Img3 = new ImageIcon(getClass().getResource("../Imagenes/Oxi.png"));
        JLabel jloxi = new JLabel(Img3);
        jloxi.setBounds(10, 250, 360, 48);
        add(jloxi);
        
        JLabel jlPeso = new JLabel(Peso);
        jlPeso.setFont(new Font("Tahoma", Font.BOLD, 26));
        jlPeso.setBounds(120, 350, 300, 48);
        jlPeso.setForeground(Color.white);
        add(jlPeso);
        obj.Peso = jlPeso;
        
        
        ImageIcon Img4 = new ImageIcon(getClass().getResource("../Imagenes/Peso.png"));
        JLabel jlpeso = new JLabel(Img4);
        jlpeso.setBounds(10, 350, 248, 48);
        add(jlpeso);
        
        ImageIcon Img5 = new ImageIcon(getClass().getResource("../Imagenes/ELECTROCARDIOGRAMA.png"));
        JLabel jlEDG = new JLabel(Img5);
        jlEDG.setBounds(390, 190, 450, 195);
        add(jlEDG); //falta funcion para imprimir electrocardiograma
        
        
        
        

        Salir();

    }

}
