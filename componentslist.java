package com.example.micaflor.cs2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class componentslist extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentslist);

        Button switchButton= (Button) findViewById(R.id.button7);
        switchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(componentslist.this, Data_Input.class);
                startActivity(intent);
            }
        });

        Button switchButton1= (Button) findViewById(R.id.button8);
        switchButton1.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(componentslist.this, Data_Input.class);
                startActivity(intent);
            }
        });

        Button switchButton2= (Button) findViewById(R.id.button9);
        switchButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(componentslist.this, Data_Input.class);
                startActivity(intent);
            }
        });

        Button switchButton3= (Button) findViewById(R.id.button10);
        switchButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(componentslist.this, Data_Input.class);
                startActivity(intent);
            }
        });


    }
} // BA
