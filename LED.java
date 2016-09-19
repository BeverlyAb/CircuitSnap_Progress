package com.example.micaflor.cs2;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * LED inherits Wire's color(string), ends(Endpoints)
 * new private variables include: resistance measured in Ohms(float)
 *                                polarity(bool)
 */
public class LED extends Wire implements Parcelable{
    private static double vdrop;        // innate voltage drop of diodes
    private boolean polarity;           // checks if orientation is from (-) to (+)

    private LED(String color, Endpoints ends, double resistance, boolean polarity) {
        super(color, ends);
        vdrop= 0.7;   // 0.7 V is a typical voltage drop for LED. Treat as constant
        // but consider this for calculations
    }

    protected LED(Parcel in){
        vdrop = in.readDouble();
        polarity = in.readByte() != 0; // polarity == true if byte != 0
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {;
        dest.writeDouble(vdrop);
        dest.writeByte((byte) (polarity ? 1 : 0)); // polarity == true if byte != 0
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<LED> CREATOR =
            new Parcelable.Creator<LED>() {

                @Override
                public LED createFromParcel(Parcel in) {
                    return new LED(in);
                }

                @Override
                public LED[] newArray(int size) {
                    return new LED[size];
                }
            };

    public double getVDrop() {
        return vdrop;
    }
    public boolean getPolarity() {
        return polarity;
    }
} // BA

