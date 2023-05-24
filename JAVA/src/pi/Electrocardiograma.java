package pi;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Electrocardiograma extends JPanel {
    
    
    
    private static final int AMPLITUDE = 100; // Amplitud de la señal
    private static final double FREQUENCY = 1; // Frecuencia de la señal en Hz
    private static final int SAMPLE_RATE = 100; // Tasa de muestreo en Hz
    private static final int DELAY = 10; // Retardo entre actualizaciones en milisegundos

    private double time = 0; // Tiempo transcurrido
    
    PI obj;

    public Electrocardiograma(PI obj1) {
        
        obj = obj1;
        setBackground(Color.WHITE);
        startAnimation();
        CrearGUI();
    }



    private void startAnimation() {
        Timer timer = new Timer(DELAY, e -> {
            time += 1.0 / (SAMPLE_RATE * FREQUENCY);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.RED);

        int xPrev = 0;
        int yPrev = centerY;

        for (int x = 0; x < getWidth(); x++) {
            double t = time + (double) x / SAMPLE_RATE;
            int y = centerY + (int) (obj.ECG_bebe);

            g.drawLine(xPrev, yPrev, x, y);

            xPrev = x;
            yPrev = y;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    public void CrearGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Electrocardiograma");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

           
            frame.add(frame);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
