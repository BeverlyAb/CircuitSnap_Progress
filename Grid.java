package com.example.micaflor.cs2;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Grid extends Activity {
    ImageView imageZoom;
    Matrix matrix = new Matrix();
    Float scale = 1f;
    ScaleGestureDetector SGD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        imageZoom = (ImageView) findViewById(R.id.imageView2);
        SGD = new ScaleGestureDetector(this, new ScaleListener());

        Button switchButton= (Button) findViewById(R.id.button);
        switchButton.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Grid.this, componentslist.class);
                startActivity(intent);
            }
        });


    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector){
            scale = scale * detector.getScaleFactor();
            scale= Math.max(0.1f, Math.min(scale, 5f));
            matrix.setScale(scale, scale);
            imageZoom.setImageMatrix(matrix);
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        SGD.onTouchEvent(event);
        return true;

    }
}
