package com.example.micaflor.cs2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Resistor inherits Wire's color(string),ends(Endpoints)
 * new private variables include: resistance measured in Ohms (float)
 * new public functions include getOhms(), returning a float
 */
public class Resistor extends Wire implements Parcelable {
    private float resistance;

    public Resistor(String color, Endpoints ends, float resistance) { // Constructor
        super(color,ends);
        this.resistance = resistance;
    }

    protected Resistor(Parcel in){   // Constructor
        resistance = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {;
        dest.writeFloat(resistance);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Resistor> CREATOR = new Creator<Resistor>() {
        @Override
        public Resistor createFromParcel(Parcel in) {
            return new Resistor(in);
        }

        @Override
        public Resistor[] newArray(int size) {
            return new Resistor[0];
        }
    };
    public float getResistance() {
        return resistance;
    }
} // BA
