package com.heeere.androjsurfbasedonjavasurf;

public class InterestPoint {

    private float x;
    private float y;
    private float scale;
    private int xOrientation;
    private int yOrientation;
    private double orientationRadius;
    private float[] descriptorOfTheInterestPoint;

    public InterestPoint(float x, float y, float _scale, int xOrientation, int yOrientation, float[] descriptorOfTheInterestPoint) {
        this.x = x;
        this.y = y;
        this.scale = _scale;
        this.xOrientation = xOrientation;
        this.yOrientation = yOrientation;
        this.orientationRadius = Math.atan2(yOrientation - y, xOrientation - x);
        this.descriptorOfTheInterestPoint = descriptorOfTheInterestPoint;
    }

    public InterestPoint(float x, float y, float scale, int xOrientation, int yOrientation) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.xOrientation = xOrientation;
        this.yOrientation = yOrientation;
        this.orientationRadius = Math.atan2(yOrientation - y, xOrientation - x);
    }

    public InterestPoint(float x, float y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.xOrientation = 0;
        this.yOrientation = 0;
        this.orientationRadius = 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getScale() {
        return scale;
    }

    public double getOrientationRadius() {
        return orientationRadius;
    }

    public void setOrientationRadius(double orientationRadius) {
        this.orientationRadius = orientationRadius;
    }

    public void setxOrientation(int xOrientation) {
        this.xOrientation = xOrientation;
    }

    public int getyOrientation() {
        return yOrientation;
    }

    public void setyOrientation(int yOrientation) {
        this.yOrientation = yOrientation;
    }

    public double getOrientation_radius() {
        return orientationRadius;
    }

    public void setOrientation(int orientation_x, int orientation_y) {
        this.xOrientation = orientation_x;
        this.yOrientation = orientation_y;
        this.orientationRadius = Math.atan2(orientation_y - y, orientation_x - x);
    }

    public float[] getDescriptorOfTheInterestPoint() {
        return descriptorOfTheInterestPoint;
    }

    public void setDescriptorOfTheInterestPoint(float[] descriptor) {
        descriptorOfTheInterestPoint = new float[descriptor.length];
        for (int i = 0; i < descriptor.length; i++) {

            this.descriptorOfTheInterestPoint[i] = descriptor[i];

        }
    }

    public String getInterestPointInformationAsAString() {
        String information = new String();
        information += x + " " + y + " ";
        for (int i = 0; i < descriptorOfTheInterestPoint.length; i++) {

            information += (descriptorOfTheInterestPoint[i] + " ");

        }
        return information;
    }

}
