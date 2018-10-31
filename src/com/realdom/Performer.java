package com.realdom;

import java.awt.Point;

public class Performer {
    private int idnum;
    private String label; // perhaps label and idnum can be consolidated
    private String name; // optional?
    private String instrument; // optional?
    private Point[] posarray; // use doubles

    public Performer(int length) {
        idnum = 0;
        label = "defaultlabel";
        name = "defaultname";
        instrument = "defaultinstrument";
        posarray = new Point[length];
    }

    public Performer(int length, int idnum, String label) {
        this.idnum = idnum;
        this.label = label;
        name = "defaultname";
        instrument = "defaultinstrument";
        posarray = new Point[length];
    }

    public Point getPos(int idx) {
        // I'm not actually sure what .getLocation() does
        // Java documentation says it "returns location"
        // it may be more useful to write two seperate methods to get x and y
        return posarray[idx].getLocation();
    }

    public double getX(int idx) {
        // returns x value of point at posarray idx
        return posarray[idx].getX();
    }

    public double getY(int idx) {
        // returns y value of point array at posarray idx
        return posarray[idx].getY();
    }

    public void setPos(int idx, double x, double y) {
        posarray[idx].setLocation(x, y);
    }
}
