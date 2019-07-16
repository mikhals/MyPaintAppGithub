package com.example.mypaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Mediator mediator = new Mediator();
    SeekBar seekBar;
    CanvasView canvasView;
    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        canvasView = findViewById(R.id.canvas);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                canvasView.setcH(i);
                canvasView.setSize(i);
                button.setText(String.valueOf(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(getApplicationContext(),String.valueOf(seekBar.getProgress()),Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                canvasView.setCurrentDrawing(new MyDrawing(0,0, canvasView));
                canvasView.changeColor();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasView.setPaint();
                canvasView.reset();
//                canvasView.setCurrentDrawing(new MyRectangle(0,0, canvasView));
            }
        });
//        setContentView(new MyCanvas(this,mediator, canvasView));
    }
}
