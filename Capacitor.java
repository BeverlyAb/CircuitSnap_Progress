package com.example.micaflor.cs2;

import android.os.Parcel;
import android.os.Parcelable;

/*
 *Capacitor inherits Wire's color(string), ends(Endpoints)
 * new private variables include: capacitance measured in Farads (float)
 *                                polarity(bool);
 * new public functions include:  getCap(), returning a float
                                  getPolarity(), returning bool
 */

public class Capacitor extends Wire implements Parcelable {
    private float capacitance;
    private boolean polarity;   // checks if orientation is from (-) to (+)

    public Capacitor(String color, Endpoints ends, float capacitance, boolean polarity) {
        super(color, ends);
        this.capacitance = capacitance;
        this.polarity = polarity;
    }

    protected Capacitor(Parcel in){
        capacitance = in.readFloat();
        polarity = in.readByte() != 0; // polarity == true if byte != 0
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(capacitance);
        dest.writeByte((byte) (polarity ? 1 : 0)); // polarity == true if byte != 0
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Capacitor> CREATOR
            = new Parcelable.Creator<Capacitor>() {

        @Override
        public Capacitor createFromParcel(Parcel in) {
            return new Capacitor(in);
        }

        @Override
        public Capacitor[] newArray(int size) {
            return new Capacitor[size];
        }
    };
    public float getCap() {
        return capacitance;
    }
    public boolean getPolarity() {
        return polarity;
    }
} // BA
