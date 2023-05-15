
package pi;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Entidad extends JFrame {

    JButton jbEntidad, jbAcerca, jbListado, jbSalir, jbIncubadora,jbVolver;
    int ContGenM = 0, ContGenF = 0, nn = 0 , ContGenASi = 0,ContGenANo = 0,ContGenAlN = 0, ContGenAlS = 0, ContGenAlSI = 0, ContGenAlSP = 0,ContGenAlSS = 0,ContGenAlSA = 0;
    String pagina = "";
    PI obj;

    public Entidad(PI obj1) {
        super("Aplicacion Entidad");
        obj = obj1;
        setSize(700, 500);
        setLocationRelativeTo(null);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        setLayout(null);
        CrearGUI();

        setVisible(true);
    }


    public void CrearGUI() {
        
        jbEntidad = new JButton("Registro paciente");
        jbEntidad.setBounds(170, 60, 300, 30);
        ImageIcon ImgRegistro = new ImageIcon(getClass().getResource("../Imagenes/registro-en-linea.png"));
        jbEntidad.setIcon(new ImageIcon(ImgRegistro.getImage().getScaledInstance(64, jbEntidad.getHeight(), Image.SCALE_SMOOTH)));
        jbEntidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoJbEntidad();
            }
        });
        add(jbEntidad);

        jbListado = new JButton("Registro medico");
        jbListado.setBounds(170, 120, 300, 30);
        ImageIcon ImgEstadisticas = new ImageIcon(getClass().getResource("../Imagenes/vigilancia.png"));
        jbListado.setIcon(new ImageIcon(ImgEstadisticas.getImage().getScaledInstance(64, jbListado.getHeight(), Image.SCALE_SMOOTH)));
        jbListado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoJbListado();
            }
        });

        add(jbListado);
        
        jbIncubadora = new JButton("Registro incubadora");
        jbIncubadora.setBounds(170, 180, 300, 30);
        ImageIcon ImgIncubadora = new ImageIcon(getClass().getResource("../Imagenes/asistance.png"));
        jbIncubadora.setIcon(new ImageIcon(ImgIncubadora.getImage().getScaledInstance(64, jbIncubadora.getHeight(), Image.SCALE_SMOOTH)));
        jbIncubadora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventojbIncubadora();
            }
        });

        add(jbIncubadora);

        jbAcerca = new JButton("Acerca de");
        jbAcerca.setBounds(170, 240, 300, 30);
        ImageIcon ImgAcerca = new ImageIcon(getClass().getResource("../Imagenes/integridad.png"));
        jbAcerca.setIcon(new ImageIcon(ImgAcerca.getImage().getScaledInstance(64, jbAcerca.getHeight(), Image.SCALE_SMOOTH)));
        jbAcerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoJbAcerca();
            }
        });
        add(jbAcerca);

        jbSalir = new JButton("Salir");
        jbSalir.setBounds(170, 300, 300, 30);
        ImageIcon ImgSalir = new ImageIcon(getClass().getResource("../Imagenes/salida.png"));
        jbSalir.setIcon(new ImageIcon(ImgSalir.getImage().getScaledInstance(64, jbSalir.getHeight(), Image.SCALE_SMOOTH)));
        jbSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoJbSalir();
            }
        });
        add(jbSalir);
        
        Salir();

    }

    public void eventoJbEntidad() {

        Registro r = new Registro(this);
        setVisible(false);

    }

    public void eventoJbListado() {
        
        RegistroMed rm = new RegistroMed(this);
        setVisible(false);

    }
    
    public void eventojbIncubadora(){
        
        RegistroIncubadora regI = new RegistroIncubadora(this);
        setVisible(false);
    }

    public void eventoJbAcerca() {
        AcercaDe ac = new AcercaDe(this);
    }

    public void eventoJbSalir() {

        System.exit(0);

    }
    
    public void Salir() {
        ImageIcon ImgVolver = new ImageIcon(getClass().getResource("../Imagenes/volver.png"));
        jbVolver = new JButton(ImgVolver);
        jbVolver.setBounds(600, 400, 54, 54);
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

}
