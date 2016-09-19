package com.example.micaflor.cs2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Micaflor on 2/8/2016.
 */
public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh);
        Thread myThread=new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    Intent startMainScreen=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(startMainScreen);
                    finish();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        };
        myThread.start();
    }
}
