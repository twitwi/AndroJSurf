/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heeere.androjsurf;

import com.stromberglabs.jopensurf.SURFInterestPoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author twilight
 */
public class SurfJava {

    public static IntensityProvider intensityProvider(final BufferedImage img) {
        return new IntensityProvider() {

            @Override
            public int getWidth() {
                return img.getWidth(null);
            }

            @Override
            public int getHeight() {
                return img.getHeight(null);
            }

            @Override
            public float getIntensity(int x, int y) {
                //float intensity = (0.299f * pixel[0] + 0.587f * pixel[1] + 0.114f * pixel[2]) / 255F;
                int what = img.getRGB(x, y);
                /*
                float[] hsb = new float[3];
                Color.RGBtoHSB((what >> 16) & 0xff, (what >> 8) & 0xff, what & 0xff, hsb);
                return hsb[2];
                /*/
                float intensity = (0.299f * ((what >> 16) & 0xff) + 0.587f * ((what >> 8) & 0xff) + 0.114f * (what & 0xff)) / 255F;
                return intensity;
                //*/

            }
        };
    }

    public static void drawPosition(BufferedImage image, SURFInterestPoint ip, int sizeInPx, Color c) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(c);
        g2d.fillRect((int) ip.getLocation()[0] - sizeInPx / 2, (int) ip.getLocation()[1] - sizeInPx / 2, sizeInPx, sizeInPx);
    }

    public static void drawDescriptor(BufferedImage image, SURFInterestPoint ip, Color strokeColor) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(strokeColor);
        g2d.drawRect((int) ip.getLocation()[0] - (int) ip.getScale() * 10, (int) ip.getLocation()[1] - (int) ip.getScale() * 10, (int) ip.getScale() * 20, (int) ip.getScale() * 20);
    }
}
