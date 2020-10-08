package com.vaibhavs.depthoffieldcalculator.Model;

/**
 * Lens class models the information about Camera lens1
 * Data includes lens1 make, maximum aperture, and focal length.
 */
public class Lens {
    private String make;
    private double maximum_aperture;
    private int focal_length;
    private int imgID;


    //default constructor

    public Lens(String make, double maximum_aperture, int focal_length, int imgID) {
        this.make = make;
        this.maximum_aperture = maximum_aperture;
        this.focal_length = focal_length;
        this.imgID = imgID;
    }


    // checks if selected aperture is larger than
    // lens1 maximum aperture and returns true
    public static boolean check_aperture(Lens ln,double aperture) {
        if(ln.getMaximum_aperture() < aperture) {
            return true;
        }
        return false;
    }
    // returns maximum aperture of the lens1
    public double getMaximum_aperture() {
        return maximum_aperture;
    }
    // returns focal length of the lens1
    public int getFocal_length() {
        return focal_length;
    }

    public String getMake() { return make; }

    public int getImgID() { return imgID; }

    public void setMake(String make) {
        this.make = make;
    }

    public void setMaximum_aperture(double maximum_aperture) {
        this.maximum_aperture = maximum_aperture;
    }

    public void setFocal_length(int focal_length) {
        this.focal_length = focal_length;
    }

    @Override
    public String toString() {
        return make +
                " " + focal_length + "mm"+
                " " + "F"+ maximum_aperture ;
    }
}
