/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heeere.androjsurf;

import com.stromberglabs.jopensurf.SURFInterestPoint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
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

    public static void drawPosition(BufferedImage image, SURFInterestPoint ip, float sizeInPx, Color c) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(c);
        float x = ip.getX();
        float y = ip.getY();
        g2d.fill(new Rectangle2D.Float(x - sizeInPx / 2, y - sizeInPx / 2, sizeInPx, sizeInPx));
    }

    public static void drawDescriptor(BufferedImage image, SURFInterestPoint ip, Color strokeColor) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(strokeColor);
        float x = ip.getX();
        float y = ip.getY();
        float s = ip.getScale();
        g2d.draw(new Ellipse2D.Float(x - s / 2, y - s / 2, s, s));
    }
}
