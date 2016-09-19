package com.example.micaflor.cs2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/* For now this serves as a way to receive data input from the user. They are prompted to fill
in First Input Position, Second Input Position, and Input Component Type.

Input First Position and Input Second Input must have the following input:
	-a letter between “A-J” (main grid) or the phrase, “IO53” or “IO54” (I/O)
	-a period (formerly used colon) to serve as delimiter
	-a number from “1-4” for I/O selections or “1-63” for main grid \n
Input Component is restricted to:
	-"resistor" "cap" "led" and "wire"

 The code as it stands is robust.

-

What it does accomplish is:
-Checks if required number of delimiters (.) is true
-removes white spaces from input
-Limit the Number Range for main grid, 1-63
-Limit Number Range from 1-4 for I/O phrases
-Notes for inputs already added
-Checks for empty data
-Letter range is not limited from "A-J" or with Phrases "IO53","IO54", "io53","io54"
-Limits the component type albeit through text, eventually, it should be through the button
-Catches if number nodes are not numeric
-Catches if First and Second Input can have the same value
 */


public class Data_Input extends AppCompatActivity {


    Button save;        // allows to add input into addarray
    Button assemble;    // moves to next activity, Assembly.java
    ArrayList<String> addarray = new ArrayList<String>();
    EditText txt,txt2,txt3;
    ListView show;
    int actual_endRange, actual_endRange2; //numNodes
    String comp; //Input component with no spaces
    public void TextFieldClicked(View v)
    {
        if (v.getId()==R.id.Text1);
        {
            txt.setText("");
            txt2.setText("");
            txt3.setText("");
        }
        if(v.getId()==R.id.Text2)
        {
            txt2.setText("");
            txt.setText("");
            txt3.setText("");
        }
        if(v.getId()==R.id.Text3)
        {
            txt.setText("");
            txt2.setText("");
            txt3.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        txt = (EditText)findViewById(R.id.Text1);
        txt2 = (EditText)findViewById(R.id.Text2);
        txt3 = (EditText)findViewById(R.id.Text3);

        show = (ListView)findViewById(R.id.listView);
        save = (Button)findViewById(R.id.arraybutton);
        assemble =  (Button)findViewById(R.id.assemble);


        save.setOnClickListener(new View.OnClickListener() { // Allows User Input
            @Override
            public void onClick(View v) {

                String getInput = txt.getText().toString();
                String getInput2 = txt2.getText().toString();
                String getInput3 = txt3.getText().toString();

                if (getInput.trim().equals("") || getInput2.trim().equals("") ||
                        getInput3.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Input data is Empty", Toast.LENGTH_LONG).show();
                    return;
                } else if(!getInput.contains(".") || !getInput2.contains(".")) { //does not contain period
                    Toast.makeText(getBaseContext(), "Missing Required Period. No Changes Made", Toast.LENGTH_LONG).show();
                    return;
                } else if(getInput.indexOf(".") != getInput.lastIndexOf(".") ||
                        getInput2.indexOf(".") != getInput2.lastIndexOf(".")) {//Contains period, but is there only one?
                    Toast.makeText(getBaseContext(), "Too Many Periods per Input. No Changes Made", Toast.LENGTH_LONG).show();
                    return;
                }

                //process letterNodes and numberNodes only if there are valid inputs
                String t1 = getInput.replaceAll("\\s+", ""); // strings are immutable, need as new strings as outcome
                String t2 = getInput2.replaceAll("\\s+", "");//remove whitespaces
                String comp = getInput3.replaceAll("\\s+", "");

                String colon1, colon2;   //again, strings are immutable
                colon1 = ".".concat(t1); //insert delimiters
                colon2 = ".".concat(t2);

                // numberNodes respectively
                String endRange = colon1.substring(colon1.lastIndexOf('.') + 1);
                String endRange2 = colon2.substring(colon2.lastIndexOf('.') + 1);
                //All println are for debugging purposes
                // System.out.println("endRange " +endRange);
                //System.out.println("endRange2 " +endRange2);


                //letterNodes respectively
                String letRange1 = colon1.substring(colon1.indexOf('.')+1,colon1.lastIndexOf('.'));
                String letRange2 = colon2.substring(colon2.indexOf('.')+1,colon2.lastIndexOf('.'));

                /*System.out.println("e1 " + colon1);
                System.out.println("e2 " + colon2);
                System.out.println("letRange1 " + letRange1);
                System.out.println("letRange2 " + letRange2);
                */

                // Create int of numRanges for getInput and getInput2
                try {
                    actual_endRange = Integer.parseInt(endRange.trim());
                    //actual_endRange2 = Integer.parseInt(endRange2.trim());//debugging test
                }
                catch (NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(), "Nodes have invalid values. No Changes Made",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    //actual_endRange = Integer.parseInt(endRange.trim());
                    actual_endRange2 = Integer.parseInt(endRange2.trim());
                }
                catch (NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(), "Nodes have invalid values. No Changes Made",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //System.out.println("actual_endRange1 " + actual_endRange);
                //System.out.println("actual_endRange2 " + actual_endRange2);
                if (!(letRange1.equals("IO54")  ||  letRange1.equals("IO53") ||
                        letRange1.equals("io54") ||  letRange1.equals("io53") ||
                        (((int)letRange1.charAt(0) >= 65 && (int)letRange1.charAt(0) <= 74)))){
                    Toast.makeText(getBaseContext(), "1st Letter Nodes are not within Range. No Changes Made",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!(letRange2.equals("IO54")  ||  letRange2.equals("IO53") ||
                        letRange2.equals("io54") ||  letRange2.equals("io53") ||
                        (((int)letRange2.charAt(0) >= 65 && (int)letRange2.charAt(0) <= 74)))){
                    Toast.makeText(getBaseContext(), "2nd Letter Nodes are not within Range. No Changes Made",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if(letRange1.equals("IO54") || letRange1.equals("IO53")|| letRange1.equals("io54")
                        || letRange1.equals("io53")) {
                    if (!(actual_endRange >= 1 && actual_endRange <= 4)) {//Not within range of 1-4
                        Toast.makeText(getBaseContext(), "1st Nodes are not within Range. No Changes Made",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if(letRange2.equals("IO54") || letRange2.equals("IO53")||letRange2.equals("io54")
                        || letRange2.equals("io53")) {
                    if ((!(actual_endRange2 >= 1 && actual_endRange2 <= 4))) {//Not within range of 1-4
                        Toast.makeText(getBaseContext(), "2nd Nodes are not within Range. No Changes Made",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                if(((int)letRange1.charAt(0) >= 65 && (int)letRange1.charAt(0) <= 74) &&
                        !letRange1.equals("IO53") && !letRange1.equals("IO54") &&
                        !letRange1.equals("io53") && !letRange1.equals("io54")){
                    if (!(actual_endRange >= 1 && actual_endRange <= 63))
                    // Not within range of 1- 63
                    {
                        Toast.makeText(getBaseContext(), "1st Nodes are not within Range. No Changes Made",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                if(((int)letRange2.charAt(0) >= 65 && (int)letRange2.charAt(0) <= 74) &&
                        !letRange2.equals("IO53") && !letRange2.equals("IO54") &&
                        !letRange2.equals("io53") && !letRange2.equals("io54") ){
                    if (!(actual_endRange2 >= 1 && actual_endRange2 <= 63))
                    // Not within range of 1- 63
                    {
                        Toast.makeText(getBaseContext(), "2nd Nodes are not within Range. No Changes Made",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                if (addarray.contains(getInput) || addarray.contains(getInput2)) {
                    Toast.makeText(getBaseContext(), "Item already added. No Changes Made",
                            Toast.LENGTH_LONG).show();
                }

                else if(getInput.equals(getInput2) ){
                    Toast.makeText(getBaseContext(), "Not Double Ended. No Changes Made",
                            Toast.LENGTH_LONG).show();

                }
                else if (!comp.equals("resistor") && !comp.equals("cap") &&
                        !comp.equals("led") && !comp.equals("wire"))
                //!= operator will compare the references to the strings not the string themselves.
                {
                    Toast.makeText(getBaseContext(), "Inapplicable Component. No Changes Made",
                            Toast.LENGTH_LONG).show();
                } else {
                    // Set up for Assembly: Pwr and GND have specific orderings between endpoints
                    if (letRange1.equals("IO53") || letRange1.equals("io53")) {
                        String temp = getInput;
                        getInput = getInput2;
                        getInput2 = temp;
                        System.out.println("getInput " + getInput);
                        System.out.println("getInput2 " + getInput2);
                    }
                    if ((letRange2.equals("IO54") || letRange2.equals("io54"))) {
                        String temp = getInput;
                        getInput = getInput2;
                        getInput2 = temp;
                        System.out.println("getInput " + getInput);
                        System.out.println("getInput2 " + getInput2);
                    }

                    Toast.makeText(getBaseContext(), "Input Added!",
                            Toast.LENGTH_LONG).show();
                    addarray.add(getInput);
                    addarray.add(getInput2);
                    addarray.add(getInput3);
                }
                txt.setHint("Input First Position");
                txt2.setHint("Input Second Position");
                txt3.setHint("Input Component");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Data_Input.this,
                        android.R.layout.simple_list_item_1, addarray);
                show.setAdapter(adapter);

                View g = new View(Data_Input.this);
                g.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 1));
                show.addFooterView(g);
                //System.out.println("l1 " + (int) letRange1.charAt(0));

            }

        });

    }
    public void assembleFunction(View view) {
        Intent moveToAssembly = new Intent(this, Assembly1.class);
        moveToAssembly.putStringArrayListExtra("inputs", addarray);
        startActivity(moveToAssembly);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
} // BA
