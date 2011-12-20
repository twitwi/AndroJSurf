
package com.heeere.androjsurf;

/**
 *
 * @author twilight
 */
public interface IntensityProvider {

    public int getWidth();

    public int getHeight();

    public float getIntensity(int x, int y);
}
