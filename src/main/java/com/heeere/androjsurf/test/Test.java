package com.heeere.androjsurf.test;

import com.heeere.androjsurf.GrayPixelRectangle;
import com.heeere.androjsurf.SurfJava;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.heeere.androjsurf.IDescriptor;
import com.heeere.androjsurf.IDetector;
import com.heeere.androjsurf.ISURFfactory;
import com.heeere.androjsurf.InterestPoint;
import com.heeere.androjsurf.SURF;
import java.awt.Color;
import java.util.List;

/**
 *
 */
public class Test {

    public static void main(String argv[]) {
        if (argv.length == 0) {
            argv = new String[]{"img.png", "imgsmall.png", "img2.png", "test.png"};
        }
        for (String a : argv) {
            new Test().main(a);
        }
    }
    List<InterestPoint> interestPoints;
    float threshold = 600;
    float balanceValue = .81f;
    int octaves = 5;
    BufferedImage img = null;
    boolean alsoDescriptors = false;

    public void main(String argv) {


        try {
            File file = new File(argv);
            img = ImageIO.read(file);
            GrayPixelRectangle iimg = SurfJava.image(img);
            ISURFfactory mySURF = SURF.createInstance(iimg, balanceValue, threshold, octaves, iimg);
            IDetector detector = mySURF.createDetector();
            interestPoints = detector.generateInterestPoints();
            System.err.println("Detected " + interestPoints.size() + " interest points");
            if (alsoDescriptors) {
                IDescriptor descriptor = mySURF.createDescriptor(interestPoints);
                descriptor.generateAllDescriptors();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        drawInterestPoints();
        if (alsoDescriptors) {
            drawDescriptors();
        }
        File out = new File(argv + "-out.png");
        try {
            ImageIO.write(img, "PNG", out);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(1);
        JLabel label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SURF.unsetSingleton();
    }

    void drawInterestPoints() {
        System.out.println("Drawing Interest Points...");
        Graphics g = img.getGraphics();
        g.setColor(Color.red);
        for (int i = 0; i < interestPoints.size(); i++) {
            InterestPoint ip = (InterestPoint) interestPoints.get(i);
            int x = (int) ip.getX();
            int y = (int) ip.getY();
            g.drawRect(x - 1, y - 1, 3, 3);
        }
    }

    void drawDescriptors() {
        System.out.println("Drawing Descriptors...");
        for (int i = 0; i < interestPoints.size(); i++) {
            InterestPoint ip = (InterestPoint) interestPoints.get(i);
            SurfJava.drawDescriptor(img, ip, new Color(0, 255, 0));
        }
    }
}
