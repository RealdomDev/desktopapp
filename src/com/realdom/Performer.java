package com.realdom;

import java.awt.Point;

public class Performer {
    private int idnum;
    private String label; // perhaps label and idnum can be consolidated
    private String name; // optional?
    private String instrument; // optional?

    private Point[] posarray;
    private boolean[] keyframe;

    public Performer(int length) {
        idnum = 0;
        label = "defaultlabel";
        name = "defaultname";
        instrument = "defaultinstrument";

        posarray = new Point[length];
        keyframe = new boolean[length];

        for (int i = 0; i < length; i += 1) {
            keyframe[i] = false;
        }
    }

    public Performer(int length, int idnum, String label) {
        this.idnum = idnum;
        this.label = label;
        name = "defaultname";
        instrument = "defaultinstrument";

        posarray = new Point[length];
        keyframe = new boolean[length];

        for (int i = 0; i < length; i += 1) {
            keyframe[i] = false;
        }
    }

    /*
        default getter/setters
    */

    public void setid(int idnum) { this.idnum = idnum; }
    public void setLabel(String label) { this.label = label; }
    public void setName(String name) { this.name = name; }
    public void setInst(String insttrument) { this.instrument = instrument; }
    public void setPoint(int i, Point point) { posarray[i] = point; }
    public void setKeyframe(int i, boolean bool) { keyframe[i] = bool; }

    public int getid() { return idnum; }
    public String getLabel() { return label; }
    public String getName() { return name; }
    public String getInst() { return instrument; }
    public Point getPoint(int i) { return posarray[i]; }

    public boolean isKeyframe(int i) {
        return keyframe[i];
    }

    public int findCountsRight(int i) {
        /*
            finds counts (difference in index) between
            indexed keyframe and subsequent keyframe

            maybe not necessary to limit first argument to keyframes
        */
        if (i < 0) {
            throw new IllegalArgumentException("index is negative");
        }
        else if (!isKeyframe(i)) {
            throw new IllegalStateException("not a keyframe");
        }
        else {
            for (int j = i + 1; j < keyframe.length; j += 1) {
                if (keyframe[j]) {
                    return j - i;
                }
            }
            throw new IllegalStateException("no subsequent keyframe");
        }
    }

    public int findCountsLeft(int i) {
        /*
            finds counts between indexed keyframe
            and previous keyframe

            again, maybe not necessary to limit to keyframes
        */
        if (i < 0) {
            throw new IllegalArgumentException("index is negative");
        }
        else if (!isKeyframe(i)) {
            throw new IllegalStateException("not a keyframe");
        }
        else {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (keyframe[j]) {
                    return i - j;
                }
            }
            throw new IllegalStateException("no previous keyframe");
        }

    }
}
