
package pi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Parar extends JFrame{
    
    PI obj;
    JButton jbVolver;

    public Parar(PI obj1){
        super("Parar");
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

        Salir();

    }
    
}
