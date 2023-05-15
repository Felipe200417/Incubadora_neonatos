package pi;

import java.awt.Image;

import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Registro extends JFrame {

    Entidad obj;
    JButton jbVolver, jbGuardar, jbListado;
    JTextField jtCc, jtApe, jtNom,jtPeso,jtTalla;
    JSpinner jsFecha;
    SpinnerDateModel sdm;

    int dia, mes, ano, hora, min;
    JComboBox<String> Genero;

    public Registro(Entidad obj1) {

        super("Formulario");
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

        JLabel jlTitulo = new JLabel("Registro Paciente");
        jlTitulo.setBounds(250, 0, 150, 30);
        add(jlTitulo);

        JLabel jlCedula = new JLabel("Ingresa numero ID: ");
        jlCedula.setBounds(40, 78, 130, 30);
        add(jlCedula);

        jtCc = new JTextField();
        jtCc.setBounds(150, 80, 150, 30);
        add(jtCc);

        JLabel jlApe = new JLabel("Ingresa el apellido: ");
        jlApe.setBounds(40, 118, 130, 30);
        add(jlApe);

        jtApe = new JTextField();
        jtApe.setBounds(150, 120, 150, 30);
        add(jtApe);

        JLabel jlNom = new JLabel("Ingresa el nombre: ");
        jlNom.setBounds(40, 158, 130, 30);
        add(jlNom);

        jtNom = new JTextField();
        jtNom.setBounds(150, 160, 150, 30);
        add(jtNom);
        
        JLabel jlPeso = new JLabel("Ingresa el Peso: ");
        jlPeso.setBounds(304, 78, 130, 30);
        add(jlPeso);

               
        jtPeso = new JTextField();
        jtPeso.setBounds(400, 80, 150, 30);
        add(jtPeso);
        
        JLabel jlTalla = new JLabel("Ingresa el Talla: ");
        jlTalla.setBounds(304, 128, 130, 30);
        add(jlTalla);
        
        jtTalla = new JTextField();
        jtTalla.setBounds(400, 130, 150, 30);
        add(jtTalla);
        
        

        
        

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

        Genero = new JComboBox<>();
        Genero.addItem("Seleccione genero");
        Genero.addItem("Femenino");
        Genero.addItem("Masculino");
        Genero.setBounds(150, 30, 150, 30);

        add(Genero);

        Calendar cal = Calendar.getInstance();
        Date initDate = cal.getTime();
        cal.add(Calendar.YEAR, -100);
        Date finDate = cal.getTime();
        cal.add(Calendar.YEAR, 200);
        
        JLabel jlFecha  = new JLabel("Ingrese fecha de nacimiento, (no es necesario poner la hora)");
        jlFecha.setBounds(40, 270, 350, 30);
        add(jlFecha);

        Date actual = cal.getTime();
        sdm = new SpinnerDateModel(initDate, finDate, actual, Calendar.YEAR);
        jsFecha = new JSpinner(sdm);
        jsFecha.setBounds(100, 300, 150, 30);
        jsFecha.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                evento_jsFecha();
            }

        });
        add(jsFecha);

        Salir();

    }

    private void evento_jsFecha() {
        Date fecha = sdm.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        dia = cal.get(Calendar.DATE);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

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
        Genero.setSelectedItem("Seleccione genero");
        jtCc.setText(" ");
        jtApe.setText(" ");
        jtNom.setText(" ");
        jtPeso.setText(" ");
        jtTalla.setText(" ");

    }

    public void guardar() {

        evento_jsFecha();
   

        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter(
                    "Pacientes.csv", true);
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de crear el archivo");
        }
        if (!error) {
            String alergias = "", antecedentes = "";

            String selectedItem = (String) Genero.getSelectedItem();
            String cc = jtCc.getText();
            String ape = jtApe.getText();
            String nom = jtNom.getText();
            String peso = jtPeso.getText();
            String talla =jtTalla.getText();

            if (selectedItem == "Seleccione genero") {
                selectedItem = " ";
            }

            try {
                fw.write(selectedItem + ";" + cc + ";" + ape + ";" + nom + ";" + dia + ";" + mes + ";" + ano + ";"+ talla + ";" + peso + "\r\n");
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
