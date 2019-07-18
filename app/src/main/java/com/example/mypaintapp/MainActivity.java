package com.example.mypaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Mediator mediator;
    SeekBar seekBar;
    CanvasView canvasView;
    Button button,button2,btnOval;
    Switch switch1;
    TextView statusText;
    int time_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        time_pressed=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        canvasView = findViewById(R.id.canvas);
        mediator = canvasView.getMediator();
        seekBar = findViewById(R.id.seekBar);
        switch1 = findViewById(R.id.switch1);
        statusText = findViewById(R.id.textView);
        btnOval = findViewById(R.id.oval_button);

        System.out.println("this");

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
                mediator.setState(new RectState(mediator));
                if(mediator == mediator.state.getMediator()){
                    statusText.setText("btn 1 Same");
                }

//                canvasView.setCurrentDrawing(new MyDrawing(0,0, canvasView));
                seekBar.setVisibility(View.VISIBLE);
                switch1.setVisibility(View.INVISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediator == mediator.state.getMediator()){
                    System.out.println(mediator.state.mediator.state);
                    statusText.setText("btn2 Same");
                }
                canvasView.reset();
                seekBar.setVisibility(View.INVISIBLE);
                switch1.setVisibility(View.VISIBLE);
                try{
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    System.out.println(e);
                }
//                canvasView.setCurrentDrawing(new MyRectangle(0,0, canvasView));
            }
        });
        btnOval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.setState(new OvalState(mediator));
            }
        });
//        setContentView(new MyCanvas(this,mediator, canvasView));
    }
}
