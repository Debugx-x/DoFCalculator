package com.vaibhavs.depthoffieldcalculator.Model;

/**
 * Lens class models the information about Camera lens
 * Data includes lens make, maximum aperture, and focal length.
 */
public class Lens {
    private final String make;
    private final double maximum_aperture;
    private final int focal_length;

    //default constructor
    public Lens(String make, double maximum_aperture, int focal_length) {
        this.make = make;
        this.maximum_aperture = maximum_aperture;
        this.focal_length = focal_length;
    }
    // checks if selected aperture is larger than
    // lens maximum aperture and returns true
    public static boolean check_aperture(Lens ln,double aperture) {
        if(ln.getMaximum_aperture() < aperture) {
            return true;
        }
        return false;
    }
    // returns maximum aperture of the lens
    public double getMaximum_aperture() {
        return maximum_aperture;
    }
    // returns focal length of the lens
    public int getFocal_length() {
        return focal_length;
    }

    @Override
    public String toString() {
        return make +
                " " + focal_length + "mm"+
                " " + "F"+ maximum_aperture ;
    }
}
