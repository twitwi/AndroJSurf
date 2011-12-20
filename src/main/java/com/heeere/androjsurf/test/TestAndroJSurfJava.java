package com.heeere.androjsurf.test;

import com.heeere.androjsurf.IntensityProvider;
import com.heeere.androjsurf.SurfJava;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.stromberglabs.jopensurf.SURFInterestPoint;
import com.stromberglabs.jopensurf.Surf;
import java.awt.Color;
import java.util.List;

/**
 *
 */
public class TestAndroJSurfJava {

    public static void main(String argv[]) throws IOException {
        if (argv.length == 0) {
            argv = new String[]{"img.png", "imgsmall.png", "img2.png", "test.png"};
        }
        for (String a : argv) {
            new TestAndroJSurfJava().main(a);
        }
        for (int i = 0; i < 100; i++) {
            new TestAndroJSurfJava().justTime(argv[0]);
        }
    }

    public void justTime(String argv) throws IOException {
        File file = new File(argv);
        img = ImageIO.read(file);
        IntensityProvider iimg = SurfJava.intensityProvider(img);
        Surf mySURF = new Surf(iimg); //, balanceValue, threshold, octaves, iimg);
        long t = System.currentTimeMillis();
        interestPoints = mySURF.getFreeOrientedInterestPoints();
        System.err.println("Detected " + interestPoints.size() + " interest points in " + (System.currentTimeMillis() - t) + " ms");

    }
    List<SURFInterestPoint> interestPoints;
    /*
    float threshold = 600;
    float balanceValue = .81f;
    int octaves = 5;
     */
    BufferedImage img = null;
    boolean alsoDescriptors = true;

    public void main(String argv) {
        try {
            File file = new File(argv);
            img = ImageIO.read(file);
            IntensityProvider iimg = SurfJava.intensityProvider(img);
            Surf mySURF = new Surf(iimg); //, balanceValue, threshold, octaves, iimg);
            long t = System.currentTimeMillis();
            interestPoints = mySURF.getFreeOrientedInterestPoints();
            System.err.println("Detected " + interestPoints.size() + " interest points in " + (System.currentTimeMillis() - t) + " ms");
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
            Logger.getLogger(TestAndroJSurfJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(1);
        JLabel label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void drawInterestPoints() {
        System.out.println("Drawing Interest Points...");
        for (SURFInterestPoint ip : interestPoints) {
            SurfJava.drawPosition(img, ip, 3, Color.red);
        }
    }

    void drawDescriptors() {
        System.out.println("Drawing Descriptors...");
        for (SURFInterestPoint ip : interestPoints) {
            SurfJava.drawDescriptor(img, ip, new Color(0, 255, 0));
        }
    }
}
