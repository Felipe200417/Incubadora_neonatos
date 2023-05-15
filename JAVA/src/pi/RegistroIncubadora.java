package pi;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RegistroIncubadora extends JFrame {

    Entidad obj;
    JButton jbVolver, jbGuardar, jbLimpiar;
    JRadioButton jrbAlarmaSI, jrbAlarmaNO, jrbO;
    JLabel jlTempA, jlTempC, jlPaciente, jlMedico, jlAlarma;
    JTextField jtPaciente, jtMedico;
    JSlider jsTempA, jsTempC;
    JSpinner jsFecha;
    SpinnerDateModel sdm;
    String diaCal, mesCal, añoCal, horaCal, TempA="26", TempC="37", codP="", codM="", Alarma="Alarmas Desactivadas";
    String pagina = "";

    public RegistroIncubadora(Entidad obj1) {
        super("Formulario");
        obj = obj1;
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        CrearGUI();
        setVisible(true);
    }

    public void CrearGUI() {
        jlAlarma = new JLabel("¿Alarmas activadas?");
        jlAlarma.setBounds(10, 240, 250, 30);
        add(jlAlarma);
        
        jrbAlarmaSI = new JRadioButton("Si");
        jrbAlarmaSI.setBounds(250, 240, 50, 30);
        add (jrbAlarmaSI);
        jrbAlarmaNO = new JRadioButton("No");
        jrbAlarmaNO.setBounds(300, 240, 50, 30);
        add (jrbAlarmaNO);
        jrbO = new JRadioButton("");
        add (jrbO);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbAlarmaSI);
        bg.add(jrbAlarmaNO);
        bg.add(jrbO);
        
        JLabel jlPaciente = new JLabel("Número de ID del paciente: ");
        jlPaciente.setBounds(10, 160, 250, 30);
        add(jlPaciente);
        
        jtPaciente = new JTextField("");
        jtPaciente.setBounds(250, 160, 300, 30);
        add(jtPaciente);
        
        JLabel jlMedico = new JLabel("Número de ID del médico: ");
        jlMedico.setBounds(10, 200, 250, 30);
        add(jlMedico);
        
        jtMedico = new JTextField("");
        jtMedico.setBounds(250, 200, 300, 30);
        add(jtMedico);
        
        jbLimpiar = new JButton("Limpiar");
        jbLimpiar.setBounds(40, 400, 150, 30);
        ImageIcon ImgList = new ImageIcon(getClass().getResource("../Imagenes/limpieza-de-datos.png"));
        jbLimpiar.setIcon(new ImageIcon(ImgList.getImage().getScaledInstance(40, jbLimpiar.getHeight(), Image.SCALE_SMOOTH)));
        jbLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Limpiar();
            }
        });
        add(jbLimpiar);
        
        JLabel jlTitulo = new JLabel("Datos Incubadora");
        jlTitulo.setBounds(250, 0, 150, 30);
        add(jlTitulo);

        JLabel jlTempA = new JLabel("Valor de la temperatura ambiental (°C): ");
        jlTempA.setBounds(10, 40, 250, 30);
        add(jlTempA);

        JLabel jlTempC = new JLabel("Valor de la temperatura corporal (°C): ");
        jlTempC.setBounds(10, 100, 250, 30);
        add(jlTempC);

        JSlider jsTempA = new JSlider(SwingConstants.HORIZONTAL, 0, 30, 26);
        jsTempA.setBounds(250, 40, 300, 40);
        jsTempA.setMajorTickSpacing(10);//poner cada cuanto aparecen las lineas grandes
        jsTempA.setMinorTickSpacing(2);//poner cuantas lineas pequeñas aparecen entre cada espacio
        jsTempA.setPaintTicks(true);//poner las lineas
        jsTempA.setPaintLabels(true);//poner los numeros
        jsTempA.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                TempA = ""+jsTempA.getValue();
            }
        });
        add(jsTempA);

        JSlider jsTempC = new JSlider(SwingConstants.HORIZONTAL, 30, 40, 37);
        jsTempC.setBounds(250, 100, 300, 40);
        jsTempC.setMajorTickSpacing(1);//poner cada cuanto aparecen las lineas grandes
        jsTempC.setMinorTickSpacing(1);//poner cuantas lineas pequeñas aparecen entre cada espacio
        jsTempC.setPaintTicks(true);//poner las lineas
        jsTempC.setPaintLabels(true);//poner los numeros
        jsTempC.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                TempC = ""+jsTempC.getValue();
            }
        });
        add(jsTempC);

        Calendar cal = Calendar.getInstance();
        Date initDate = cal.getTime();
        cal.add(Calendar.YEAR, -100);
        Date finDate = cal.getTime();
        cal.add(Calendar.YEAR, 200);
        Date actual = cal.getTime();
        sdm = new SpinnerDateModel(initDate, finDate, actual, Calendar.YEAR);

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

    public void evento_Fecha() {
        Date fecha = sdm.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        diaCal = "" + cal.get(Calendar.DATE);
        mesCal = "" + (cal.get(Calendar.MONTH) + 1);
        añoCal = "" + cal.get(Calendar.YEAR);
        horaCal = "" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
    }

    public void guardar() {
        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter(
                    "Incubadora.csv", true);
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de crear el archivo");
        }
        if (!error) {
            evento_Fecha();
            codP = jtPaciente.getText();
            codM = jtMedico.getText();
            if (jrbAlarmaSI.isSelected()){
                Alarma = "Alarmas Activadas";
            }
            try {

                fw.write(codP + ";" + codM + ";" + TempA + ";" + TempC + ";" + diaCal + ";" + mesCal + ";" + añoCal + ";" + horaCal + ";" + Alarma + "\r\n");
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
    
    public void Limpiar(){
        jtPaciente.setText("");
        jtMedico.setText("");
        jrbO.setSelected(true);
    }
}