/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heeere.androjsurf;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author twilight
 */
public class SurfJava {

    public static GrayPixelRectangle image(final BufferedImage img) {
        return new GrayPixelRectangle() {

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
                int what = img.getRGB(x, y);
                float[] hsb = new float[3];
                Color.RGBtoHSB((what >> 16) & 0xff, (what >> 8) & 0xff, what & 0xff, hsb);
                return hsb[2];
            }
        };
    }

    public static void drawPosition(BufferedImage image, InterestPoint ip, int sizeInPx, Color c) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(c);
        g2d.fillRect((int) ip.getX() - sizeInPx / 2, (int) ip.getY() - sizeInPx / 2, sizeInPx, sizeInPx);
    }

    public static void drawDescriptor(BufferedImage image, InterestPoint ip, Color strokeColor) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(strokeColor);
        g2d.drawRect((int) ip.getX() - (int) ip.getScale() * 10, (int) ip.getY() - (int) ip.getScale() * 10, (int) ip.getScale() * 20, (int) ip.getScale() * 20);
    }
}
