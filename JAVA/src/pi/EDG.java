package pi;

import java.awt.Color;
import javax.swing.JFrame;

public class EDG extends JFrame {

    public EDG() {

        super("EDG");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 51, 153));
        //setLayout(null);
        CrearGUI();
        setVisible(true);

        
    }
    
    

    private void CrearGUI() {
        
        Electrocardiograma ed = new Electrocardiograma();
        ed.setBounds(0, 0, 500, 400);
        add(ed);
    }

    
}
