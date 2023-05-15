package pi;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RegistroMed extends JFrame {

    Entidad obj;
    JButton jbVolver, jbGuardar, jbListado;
    JTextField jtCc, jtApe, jtNom;
    JCheckBox jcb1, jcb2, jcb3, jcb4;
    

    public RegistroMed(Entidad obj1) {

        super("Datos del médico");
        obj = obj1;
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        CrearGUI();
        setVisible(true);
    }

    public void CrearGUI() {


        JLabel jlCedula = new JLabel("Ingrese su cédula: ");
        jlCedula.setBounds(140, 78, 130, 30);
        add(jlCedula);

        jtCc = new JTextField();
        jtCc.setBounds(250, 80, 150, 30);
        add(jtCc);

        JLabel jlApe = new JLabel("Ingrese su apellido: ");
        jlApe.setBounds(138, 118, 130, 30);
        add(jlApe);

        jtApe = new JTextField();
        jtApe.setBounds(250, 120, 150, 30);
        add(jtApe);

        JLabel jlNom = new JLabel("Ingrese su nombre: ");
        jlNom.setBounds(138, 158, 130, 30);
        add(jlNom);

        jtNom = new JTextField();
        jtNom.setBounds(250, 160, 150, 30);
        add(jtNom); 
        
        JLabel jlHorario = new JLabel("Ingrese el horario en el que labora:");
        jlHorario.setBounds(48, 250, 200, 30);
        add(jlHorario);
        
        jcb1 = new JCheckBox("Mañana");
        jcb1.setBounds(250, 200, 120, 30);
        add(jcb1);
       
        jcb2 = new JCheckBox("Tarde");
        jcb2.setBounds(250, 240, 120, 30);
        add(jcb2);
        
        jcb3 = new JCheckBox("Noche");
        jcb3.setBounds(250, 280, 120, 30);
        add(jcb3);
        
        jcb4 = new JCheckBox("Indefinido");
        jcb4.setBounds(250, 320, 120, 30);
        add(jcb4);
       
       
       
        
        jbGuardar = new JButton("Guardar");
        jbGuardar.setBounds(215, 400, 150, 30);
        ImageIcon ImgGuardar = new ImageIcon(getClass().getResource("../Imagenes/guardar.png"));
        jbGuardar.setIcon(new ImageIcon(ImgGuardar.getImage().getScaledInstance(40, jbGuardar.getHeight(), Image.SCALE_SMOOTH)));
        jbGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });

        add(jbGuardar);

        jbListado = new JButton("Limpiar");
        jbListado.setBounds(40, 400, 150, 30);
        ImageIcon ImgList = new ImageIcon(getClass().getResource("../Imagenes/limpieza-de-datos.png"));
        jbListado.setIcon(new ImageIcon(ImgList.getImage().getScaledInstance(40, jbListado.getHeight(), Image.SCALE_SMOOTH)));
        jbListado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
            }
        });

        add(jbListado);

        Salir();

    }

    public void Salir() {
        jbVolver = new JButton("Volver");
        jbVolver.setBounds(390, 400, 150, 30);
        ImageIcon ImgVolver = new ImageIcon(getClass().getResource("../Imagenes/volver.png"));
        jbVolver.setIcon(new ImageIcon(ImgVolver.getImage().getScaledInstance(38, jbVolver.getHeight(), Image.SCALE_SMOOTH)));
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

    public void Limpiar() {
        jtCc.setText(" ");
        jtApe.setText(" ");
        jtNom.setText(" ");
        jcb1.setSelected(false);
        jcb2.setSelected(false);
        jcb3.setSelected(false);
        jcb4.setSelected(false);
    }

    public void guardar() {

        FileWriter fw = null;
        boolean error = false;
        String items = "";
        if(jcb1.isSelected()) items+=jcb1.getText()+";";
        if(jcb2.isSelected()) items+=jcb2.getText()+";";
        if(jcb3.isSelected()) items+=jcb3.getText()+";";
        if(jcb4.isSelected()) items+=jcb4.getText()+"";
        
        try {
            fw = new FileWriter(
                    "Medico.csv", true);
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de crear el archivo");
        }
        if (!error) {
            String cc = jtCc.getText();
            String ape = jtApe.getText();
            String nom = jtNom.getText();

            try {
                fw.write(cc + ";" + ape + ";" + nom + ";"+ items +"\r\n");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al guardar en el archivo");
            }

            try {
                fw.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al cerrar el archivo");
            }
        }
        JOptionPane.showMessageDialog(null, "Guardado");

        Limpiar();
        
        
        
    }
}