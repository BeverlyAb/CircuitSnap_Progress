package com.example.micaflor.cs2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ListIterator;

/** BA
 *  Endpoints: used for Logic.java
 *  Includes:
 *  -letStart and numStart, which are the Letters and numbers of the beginning node. Ex ) A 7
 *  -letEnd and numEnd, which are Letters and numbers of the end node. Ex ) A 26
 *  -type : Component Type Ex ) LED
 */
public class Endpoints implements Parcelable, ListIterator<Endpoints> {
    private String letStart;
    private int numStart;
    private String letEnd;
    private int numEnd;
    private String type;

    protected Endpoints(Parcel in) {
        letStart = in.readString();
        numStart = in.readInt();
        letEnd = in.readString();
        numEnd = in.readInt();
        type = in.readString();
    }

    public static final Creator<Endpoints> CREATOR = new Creator<Endpoints>() {
        @Override
        public Endpoints createFromParcel(Parcel in) {
            return new Endpoints(in);
        }

        @Override
        public Endpoints[] newArray(int size) {
            return new Endpoints[size];
        }
    };

    public Endpoints() {
        letStart = letEnd = type = "";
        numStart = numEnd = -1;
    }

    public Endpoints(String letStart, int numStart, String letEnd, int numEnd, String type) {
        this.letStart=letStart;
        this.numStart=numStart;
        this.letEnd=letEnd;
        this.numEnd=numEnd;
        this.type=type;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(letStart);
        dest.writeInt(numStart);
        dest.writeString(letEnd);
        dest.writeInt(numEnd);
        dest.writeString(type);
    }


    public String getLetStart() {
        return letStart;
    }
    public String getLetEnd() {
        return letEnd;
    }
    public int getNumStart() {
        return numStart;
    }
    public int getNumEnd() {
        return numEnd;
    }
    public String getType() {
        return type;
    }
// The following lines are not used and should be modified if wished to be implemented
    @Override
    public void add(Endpoints object) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Endpoints next() {
        return null;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public Endpoints previous() {
        return null;
    }

    @Override
    public int previousIndex() {
        return 0;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(Endpoints object) {

    }
} // BA
