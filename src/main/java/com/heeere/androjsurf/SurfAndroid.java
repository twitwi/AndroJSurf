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
    
    public static IntensityProvider intensityProvider(final Bitmap bitmap) {
        return new IntensityProvider() {

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
                // TODO might try a custom version of it
                return hsv[2];
            }
        };
    }
    
}
