package com.example.micaflor.cs2;

import android.os.Parcel;
import android.os.Parcelable;
/*
 * Wire serves as the super class for double ended components, such as the capacitor, resistor, and
 * diode.
 * Because these three classes inherit from the class Wire they too will have the variables:
 * - String :color(changes color of component; limited to black, red, blue, green, and yellow)
 * - Endpoint: ends
    Includes: Endpoints.java
 */

public class Wire implements Parcelable {

    private String color;
    private Endpoints ends; // contains letStart, numStart, numEnd, and type

    public Wire() {
        color = "black";
        ends = new Endpoints(); // Treat -1 as null
    }

    public Wire(String colorStart, Endpoints e) {
        if (color == "black" || color == "red" || color == "blue" || color == "green"
                || color == "yellow") {
            color = colorStart;
        }
        ends = e;
    }

    protected Wire(Parcel in) {
        this.color = in.readString();
        this.ends = in.readParcelable(Endpoints.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(color); // color.toString();
        dest.writeParcelable(ends, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Wire> CREATOR
            = new Parcelable.Creator<Wire>() {

        @Override
        public Wire createFromParcel(Parcel in) {
            return new Wire(in);
        }

        @Override
        public Wire[] newArray(int size) {
            return new Wire[size];
        }
    };

    public String getColor() {
        return color;
    }

    public String getLetStart() {
        return ends.getLetStart();
    }
    public String getLetEnd() {
        return ends.getLetEnd();
    }
    public int getNumStart() {
        return ends.getNumStart();
    }

    public int getNumEnd() {
        return ends.getNumEnd();
        // same thing here
    }
    // include a display function which shows a wire connected from the ends
} // BA
