package com.heeere.androjsurf.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

/**
 *
 * @author mite
 */
public class Test {

    static ArrayList interest_points;
    static float threshold = 700;
    static float balanceValue = (float) 0.9;
    static int octaves = 5;

    public static void main(String argv[]) {


        BufferedImage img = null;


        try {
            File file = new File(argv[0]);
            img = ImageIO.read(file);
//            BufferedImage parent = img;
            ISURFfactory mySURF = SURF.createInstance(img, balanceValue, threshold, octaves, img);
            IDetector detector = mySURF.createDetector();
            interest_points = detector.generateInterestPoints();
            IDescriptor descriptor = mySURF.createDescriptor(interest_points);
            descriptor.generateAllDescriptors();

        } catch (Exception ex) {


            ex.printStackTrace();
        }
        drawInterestPoints();
        drawDescriptors();
        File out = new File(argv[0] + "-out.png");
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


    }

    static void drawInterestPoints() {

        System.out.println("Drawing Interest Points...");

        for (int i = 0; i < interest_points.size(); i++) {

            InterestPoint IP = (InterestPoint) interest_points.get(i);
            IP.drawPosition(5, new Color(200, 200, 200));

        }

    }

    static void drawDescriptors() {

        System.out.println("Drawing Descriptors...");

        for (int i = 0; i < interest_points.size(); i++) {

            InterestPoint IP = (InterestPoint) interest_points.get(i);
            IP.drawDescriptor(new Color(255, 0, 0));

        }

    }
}
