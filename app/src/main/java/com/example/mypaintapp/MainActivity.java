package com.example.mypaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
    MainActivity activity = this;
    Mediator mediator;
    CanvasView canvasView;
    ImageButton btnRect,btnReset,btnOval,btnColor;;
//    Button btnReset,btnOval,btnColor;
    TextView statusText;
    int time_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        time_pressed=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRect = findViewById(R.id.rect_button);
        btnReset = findViewById(R.id.button_reset);
        canvasView = findViewById(R.id.canvas);
        mediator = canvasView.getMediator();
        statusText = findViewById(R.id.textView);
        btnOval = findViewById(R.id.oval_button);
        btnColor = findViewById(R.id.colorButton);
        mediator.setStatusbar(statusText);


        btnRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.setState(new RectState(mediator));
                mediator.setStatusText("Rectangle");
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasView.reset();
                mediator.setStatusText("Reset");
            }
        });
        btnOval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.setState(new OvalState(mediator));
                mediator.setStatusText("Oval");
            }
        });
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder().setColor(Color.BLUE).setDialogId(2).show(activity);
            }
        });
    }


    @Override
    public void onColorSelected(int dialogId, int color) {
        if(dialogId==2){
            if(!mediator.drawings.isEmpty()){
                mediator.setStatusText("Color changed");
                mediator.getLastDrawing().fillColor = color;
                mediator.repaint();
            }

        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}
