package com.example.mypaintapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
    MainActivity activity = this;
    Mediator mediator;
    CanvasView canvasView;
    ImageButton btnRect,btnReset,btnOval,btnColor,btnHenda,btnStar,btnSelect,btnDelete;
//    Button btnReset,btnOval,btnColor;
    TextView statusText;
    int time_pressed;
    AlertDialog.Builder builder;

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
        btnHenda = findViewById(R.id.hendaButton);
        btnStar = findViewById(R.id.starButton);
        btnSelect = findViewById(R.id.selectButton);
        btnDelete = findViewById(R.id.deleteButton);
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
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Create new canvas?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                canvasView.reset();
                                mediator.setStatusText("New canvas created");
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
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
        btnHenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.setState(new HendaState(mediator));
                mediator.setStatusText("Hendacagonal");
            }
        });
        btnHenda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Custom polygon");
                return true;
            }
        });
        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something
                mediator.setState(new StarState(mediator));
                mediator.setStatusText("Star");
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do
                mediator.setState(new SelectState(mediator));
                mediator.setStatusText("Select drawing(s)");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediator.removeSelected()){
                    mediator.setStatusText("Removed selected drawing(s)");
                }else{
                    mediator.setStatusText("Nothing to delete");
                }

            }
        });
    }


    @Override
    public void onColorSelected(int dialogId, int color) {
        if(dialogId==2){
            if(!mediator.selectedDrawings.isEmpty()){
                mediator.setStatusText("Color changed");
//                mediator.getLastDrawing().fillColor = color;
                mediator.changeSelectedFillColor(color);
                mediator.repaint();
            }else{
                mediator.setStatusText("Nothing is selected. Please select something");
            }

        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
    }
}
