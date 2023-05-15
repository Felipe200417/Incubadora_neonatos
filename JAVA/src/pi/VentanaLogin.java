package pi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class VentanaLogin extends JFrame{
    
    JTextField jtLogin;
    JPasswordField jpPass;
    JButton jbCancelar, jbIngreso;
    JToggleButton jbVerPass;
    ImageIcon img_ver, img_no_ver;
    int contador = 0;
    int bandera_modo = 0;
    PI obj;
    
    
    public VentanaLogin(PI obj1){
        super("Ingreso al sistema");
        obj = obj1;
        bandera_modo = 0;
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0, 51, 153));
        setResizable(false);
        setLayout(null);
        crearGUI();
        setVisible(true);
    }
    
    public void crearGUI(){
        
        ImageIcon img = new ImageIcon(getClass().getResource("../imagenes/App-login-manager-icon.png"));        
        JLabel jlTitulo = new JLabel(img);
        jlTitulo.setBounds(100, 30, 200, 52);
        add(jlTitulo);
        
        JLabel jlLogin = new JLabel("Login:");
        jlLogin.setBounds(10, 100, 100, 30);
        jlLogin.setForeground(Color.WHITE);
        jlLogin.setHorizontalAlignment(JLabel.RIGHT);
        add(jlLogin);
        
        jtLogin = new JTextField();
        jtLogin.setBounds(120, 100, 150, 30);
        jtLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpPass.requestFocus();
            }
        });
        add(jtLogin);
                        
        JLabel jlPass = new JLabel("Password:");
        jlPass.setBounds(10, 150, 100, 30);
        jlPass.setForeground(Color.WHITE);
        jlPass.setHorizontalAlignment(JLabel.RIGHT);
        add(jlPass);
        
        jpPass = new JPasswordField();
        jpPass.setBounds(120, 150, 150, 30);
        jpPass.setEchoChar('*');        
        jpPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbIngreso();
            }
        });
        add(jpPass);
        
        img_ver = new ImageIcon(getClass().getResource("../imagenes/ver.png"));
        img_no_ver = new ImageIcon(getClass().getResource("../imagenes/no_ver.png"));
        
        jbVerPass = new JToggleButton(img_no_ver); // para ver/ocultar el password
        jbVerPass.setBounds(275, 150, 50, 30);        
        jbVerPass.setBorderPainted(false); // quitar el borde del boton
        jbVerPass.setContentAreaFilled(false); // quitar el color de fondo del boton     
        jbVerPass.setFocusPainted(false); // quitar el recuadro del foco del boton
        jbVerPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbVerPass();
            }
        });
        add(jbVerPass);
        
        jbCancelar = new JButton("Salir");
        jbCancelar.setBounds(45, 240, 150, 35);
        jbCancelar.setMnemonic('S');
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                obj.setVisible(true);
            }
        });
        add(jbCancelar);
        
        jbIngreso = new JButton("Ingresar");
        jbIngreso.setBounds(205, 240, 150, 35);
        jbIngreso.setMnemonic('I');
        jbIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbIngreso();
            }
        });
        add(jbIngreso);
    }
    
    private void evento_jbVerPass() {        
        if(jbVerPass.isSelected()){ // si el JToggleButton esta hundido
            jbVerPass.setIcon(img_ver); // asignar la imagen de ver al boton
            jpPass.setEchoChar((char)0); // permitir ver los caracteres escritos en el JPasswordField
        }else{
            jbVerPass.setIcon(img_no_ver); // asignar la imagen de no_ver al boton
            jpPass.setEchoChar(' '); // ocultar ver los caracteres escritos en el JPasswordField
        }
        jpPass.requestFocus(); // poner el cursor en el JPasswordField
    }
    
    public void evento_jbIngreso(){
        String login = jtLogin.getText();
        char pw[] = jpPass.getPassword(); // obtener los caracteres escritos en el JPasswordField como un arreglo de tipo char[]
        String pass = String.valueOf(pw); // convertir el arreglo char[] a String
                
        System.out.println(pass); // mostrar el password
        
        if(login.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(
                        this, 
                        "Login y/o password vacios", 
                        "Advertencia", 
                        JOptionPane.WARNING_MESSAGE);
        }else{
            if ((login.equals("admin") && 
                    pass.equals("12345")) || 
                 (login.equals("enfermera") && 
                    pass.equals("54321"))) {
                //System.out.println("Correcto");
                
                Modo mp = new Modo(this,obj);
                bandera_modo = 1;
                setVisible(false); // ocultar VentanaLogin 
                limpiar();
            } else {
                contador++;
                if (contador == 3) {
                    JOptionPane.showMessageDialog(
                            this, 
                            "Se agotaron los intentos.\nLacuenta se bloqueo", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(
                            this, 
                            "Login y/o password incorrecto", 
                            "Advertencia", 
                            JOptionPane.WARNING_MESSAGE);
                    limpiar();
                }
            }
        }
    }
    
    public void limpiar(){
        jtLogin.setText("");
        jpPass.setText("");
        jtLogin.requestFocus();
    }
     
}
