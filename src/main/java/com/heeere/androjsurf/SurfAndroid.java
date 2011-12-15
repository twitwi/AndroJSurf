/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heeere.androjsurf;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 *
 * @author twilight
 */
public class SurfAndroid {
    
    public static GrayPixelRectangle image(final Bitmap bitmap) {
        return new GrayPixelRectangle() {

            @Override
            public int getWidth() {
                return bitmap.getWidth();
            }

            @Override
            public int getHeight() {
                return bitmap.getHeight();
            }

            @Override
            public float getIntensity(int x, int y) {
                int c = bitmap.getPixel(x, y);
                float[] hsv = new float[3];
                Color.colorToHSV(c, hsv);
                return hsv[2];
            }
        };
    }
    
}
