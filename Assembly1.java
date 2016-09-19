package com.example.micaflor.cs2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *This code is in progress. Eventually, it should display Contents for Assembly for simple,
 closed series circuits that have one component connected to IO53(GND) and IO54(PWR).

 At this assembly stage, Data_Input should have filtered out the mistakes and fulfilled the
 requirements of having one endpoint at power and another at ground. The client emphasized the value
 of real time evaluation which works well for Data_Input.java

 The circuits passed here must be closed series circuits and this will simply order the contents
 so that the component connected to power is first and sequentially connects to the others until
 it reaches ground.

 This applies Logic.java and Endpoints.java
 Note: Commented out Executable code is useful for debugging purpose. They yo' friends.
 */
public class Assembly1 extends AppCompatActivity {

    ListView show;
    int actual_numRange1, actual_numRange2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly1);

        TextView test = (TextView) findViewById(R.id.AssComp);

        show = (ListView) findViewById(R.id.listView3);

        Intent readAddArray = getIntent();
        ArrayList<String> addarray = readAddArray.getStringArrayListExtra("inputs");

         if(addarray.isEmpty()) {
            Toast.makeText(getBaseContext(), "There aren't any components to assemble.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(Assembly1.this,
                android.R.layout.simple_list_item_1, addarray);
        show.setAdapter(adapter);


        View g = new View(Assembly1.this);
        g.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 1));
        show.addFooterView(g); */

        ArrayList<String> Temp = new ArrayList<String>();

     /*   Temp.add("IO54.3");// NOTE:  IO54.3.... is Last in list<Endpoints> Test before sorted
        Temp.add("D.5");
        Temp.add("resistor");

        Temp.add("E.9");
        Temp.add("H.60");
        Temp.add("wire");

        Temp.add("E.60");
        Temp.add("H.57");
        Temp.add("resistor");

        Temp.add("J.5");
        Temp.add("F.9");
        Temp.add("cap");

        Temp.add("B.57");   //NOTE:, B.63..... is First in list<Endpoints> Test before sorted
        Temp.add("IO53.2");
        Temp.add("led");

    */
        Logic Test = new Logic(new Endpoints(), new Endpoints(), new LinkedList<Endpoints>(), new ListIterator<Endpoints>() {
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
        });

        //refers to Logic.java.  Gave this constructor parameters, since default is uncooperative

        for (int i = 0; i <= addarray.size() - 3; ) {
        //for (int i = 0; i <= Temp.size() - 3; ) {
            String e1; // endpoint 1: String letStart, int numStart
            String e2; // endpoint 2: similar to  ^^^^
            String comp;

            e1 = addarray.get(i++); // index 0 to 1
            e2 = addarray.get(i++); // index 1 to 2
            comp = addarray.get(i++);

         /*   e1 = Temp.get(i++); // index 0 to 1
            e2 = Temp.get(i++); // index 1 to 2
            comp = Temp.get(i++);// index 2 on...
        */
            String colon1, colon2;
            colon1 = ".".concat(e1);
            colon2 = ".".concat(e2);

            //numberNodes respectively
            String numRange1 = colon1.substring(colon1.lastIndexOf('.') + 1);
            String numRange2 = colon2.substring(colon2.lastIndexOf('.') + 1);
            System.out.println("numRange1 " + numRange1);
            System.out.println("numRange2 " + numRange2);

            String letRange1 = colon1.substring(colon1.indexOf('.') + 1, colon1.lastIndexOf('.'));
            String letRange2 = colon2.substring(colon2.indexOf('.') + 1, colon2.lastIndexOf('.'));
            //Create int of numRanges for getInput and getInput2
            actual_numRange1 = Integer.parseInt(numRange1);
            actual_numRange2 = Integer.parseInt(numRange2);

            System.out.println("e1 " + colon1);
            System.out.println("e2 " + colon2);
            System.out.println("letRange1 " + letRange1);
            System.out.println("letRange2 " + letRange2);
            System.out.println("comp " + comp);

            Endpoints actualIn = new Endpoints(letRange1, actual_numRange1, letRange2, actual_numRange2, comp);
         //System.out.println("letRange1 " + actualIn.getLetStart());
         //System.out.println("letRange2 " + actualIn.getLetEnd());
            Test.add(actualIn);
          //  System.out.println("Test Size " + Test.getSize());
        }
        Test.cleanUp();
        if(!Test.closedCkt() || Test.getList().size() <= 2) {
            Toast.makeText(getBaseContext(), "These components do not create a closed series.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Endpoints[] AllOrder = Test.getList().toArray(new Endpoints[Test.getList().size()]);
        ArrayList<String> CompOrder = new ArrayList<>();
        for (int i = 0; i < AllOrder.length; i++) { //only gets the component as a string
            CompOrder.add(i, AllOrder[i].getType());
        }
         //System.out.println("AllOrder " + AllOrder.length);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(Assembly1.this,
                 android.R.layout.simple_list_item_1, CompOrder);
        show.setAdapter(adapter);


        View g = new View(Assembly1.this);
         g.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 1));
         show.addFooterView(g);

        test.setText("Assembly Completed");
    }
} // BA
