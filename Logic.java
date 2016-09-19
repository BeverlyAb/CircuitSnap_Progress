package com.example.micaflor.cs2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;
import java.util.ListIterator;

/*
 * Using the board merely as reference, this will require two types of inputs
 * (2 endpoints, and component type) from the user.
 * Component type includes: wire, resistor, capacitor, and  LED
 *
 * There should also be letterNodes with phrases "IO53" (GND), and "IO54" (+5V)
 *
 * This sorts closed, series circuits only! And checks if it is indeed closed.
 *
 * Uses: Endpoints.java
 * Note: Commented out Executable code is useful for debugging purpose. They yo' friends.
 *
 * For further versions:
 * After specifying the component type, followup characteristics will be asked.
 * Ex) If Component Type is Resistor, then ask for Resistance given in ohms.
 */
public class Logic implements Parcelable {
    private Endpoints current;
    private Endpoints next;
    private LinkedList<Endpoints> list;
    private ListIterator<Endpoints> it;
    private Endpoints temp;

    protected Logic(Parcel in) {
        current = in.readParcelable(Assembly1.class.getClassLoader());
        next = in.readParcelable(Assembly1.class.getClassLoader());
        list = new LinkedList<Endpoints>(); // Weird, I know
        in.readList(list, null);
    }

    public static final Creator<Logic> CREATOR = new Creator<Logic>() {
        @Override
        public Logic createFromParcel(Parcel in) {
            return new Logic(in);
        }

        @Override
        public Logic[] newArray(int size) {
            return new Logic[size];
        }
    };

    public Logic() {
        current = temp = list.getFirst();
        list = new LinkedList<Endpoints>();
        it = list.listIterator();
        if(it.hasNext()) {
            next = it.next();
        }
    }

    public Logic(Endpoints e1,Endpoints e2, LinkedList<Endpoints> l, ListIterator<Endpoints> it){
        current = temp = e1;
        next = e2;
        list = l;
        this.it =it;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(current, flags);
        dest.writeParcelable(next, flags);
        dest.writeTypedList(list);
    }

    public boolean closedCkt() { // Returns true if all beginnings meet an end.
        // Volt. and GND checks are handled in Data_Input.java
        //This can be used for Parallel Circuit Implementation
        //Must Have Voltage and GND
        cleanUp();
        if(list.size() <= 2) {
            return false;
        }

        if((!list.getFirst().getLetStart().equals("IO54") ||!list.getLast().getLetEnd().equals("IO53")) &&
        (!list.getFirst().getLetStart().equals("io54") || !list.getLast().getLetEnd().equals("io53"))){
            System.out.println("1");
            return false;
        }

        //Inner most outer ends connects to Volt. and GND
        if(list.getFirst().getNumEnd() != list.get(1).getNumStart() ||
                list.getLast().getNumStart() !=(list.get(list.size() - 2).getNumEnd())) {
            System.out.println("2");
            return false;
        }

        //Check intermediate connections
            it = list.listIterator(list.indexOf(list.getFirst())); // it is at current position
            it.next();
            current = it.next();
            next = it.next();
            int size = 1;
            while(it.hasNext() && size < list.size() -1 ) { // Don't include GND
                if (current.getNumEnd() != (next.getNumStart())) { // find next link, exclude GND
                    size++;
                    System.out.println("current " + current.getNumEnd());
                    System.out.println("next "+ next.getNumStart());
                    System.out.println("3");
                    return false;
                }
                it = list.listIterator(list.indexOf(current));
                it.next();
                current = it.next();
                next = it.next();
            }
            System.out.println("4");
            return true;

    }

    public void cleanUp() {
    /* Creates closed series circuit by ordering the contents
    so that the component connected to volt is first and sequentially connects to the others until
    it reaches ground.*/
        if(it.hasNext() && list.size() > 2) {
            it = this.list.listIterator(list.indexOf(list.getFirst()));
            current = list.getFirst();
            next = it.next();

            int j = 0;
            // Make volt into the first link
            if (!list.getFirst().getLetStart().equals("IO54")|| !list.getFirst().getLetStart().equals("io54")) {
                //I'm sure there's a better way to find the link containing volt
                for (; j < list.size(); j++)
                    if ((list.get(j).getLetStart()).equals("IO54")|| (list.get(j).getLetStart()).equals("io54")) {
                        break;
                    }
                current = list.get(j); // equals to volt
                temp = list.getFirst(); // switching volt link and first link
                list.set(0, current);
                list.set(j, temp);
                //  System.out.println("CURRENT " + current.getLetStart());
            }

            it = list.listIterator(list.indexOf(current));
            //Make last link GND , again there's more efficient way to search for it
            // Try out Collections.binarySort(list) yourself, since I ain't got time for this -3-
            if ((!list.getLast().getLetEnd().equals("IO53")) || !list.getLast().getLetEnd().equals("io53")) {
                j = 0;
                for (; j < list.size(); j++)
                    if ((list.get(j).getLetEnd()).equals("IO53") || (list.get(j).getLetEnd()).equals("io53")) {
                        break;
                    }
                System.out.println("j " + j);
                current = list.get(j); // equals to GND
                temp = list.getLast(); // switching GND link and last link
                list.set(list.size() - 1, current);
                list.set(j, temp);

            }
            //Sort Intermediates
            if (it.hasNext()) {
                current = list.getFirst();
                it = list.listIterator(list.indexOf(current)); // it is at current position
                next = it.next();
                System.out.println("Should be one " + list.indexOf(next));

                while (it.hasNext()) {
/*
                    if( current.getNumEnd() == (next.getNumEnd())) { // switch endpoints of single component to follow sort algorithm
                        // create link with correct endpoint orientation
                        it.next();
                        next = it.next();
                        temp = new Endpoints(next.getLetStart(),next.getNumEnd(),next.getLetEnd(),
                                next.getNumStart(),next.getType());

                       // get rid of link, and replace with the temp
                        list.set(list.indexOf(next),temp);
                        //set next to this value and it to follow
                        next = list.get(list.indexOf(temp));
                    }
*/
                    if (current.getNumEnd() != (next.getNumStart())) { // Sort Algorithm: find next link, exclude GND
                        next = it.next();

                        //   System.out.println("Current Num " + current.getNumEnd());
                        //   System.out.println("Next Num " + next.getNumStart());

                    } else {   // found link,connect and move on;
                        it = list.listIterator(list.indexOf(current));
                       if(it.hasNext())
                           it.next();
                        temp = it.next();
                        System.out.println("Temp " + list.indexOf(temp));
                        list.set(list.indexOf(temp), next); // switch temp links into link wanted
                        list.set(list.lastIndexOf(next), temp);

                        current = list.get((list.indexOf(next)));
                        it = list.listIterator(list.indexOf(temp));
                        next = it.next();
                        System.out.println("CURRENTTT " + list.indexOf(current));
                    }
                }
            }
        }
        return;
    }

    public int getSize() {
        return list.size();
    }

    public void add(Endpoints in) { // adds to beginning of list
        list.push(in);
        current = in;
        it =list.listIterator(list.indexOf(current));
        if(it.hasNext())
            next = it.next();// points to null
    }
    public void removeFirst() {
        list.removeFirst();

    }
    public Endpoints getCurrent(){
        return current;
    }

    public LinkedList<Endpoints> getList() { // returns list regardless if open or closed state
        cleanUp();
        return list;
    }
} // BA
