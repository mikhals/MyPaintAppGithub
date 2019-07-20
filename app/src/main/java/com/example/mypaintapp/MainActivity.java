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
    ImageButton btnRect,btnReset,btnOval,btnColor,btnHenda,btnStar,btnSelect,btnDelete,btnCopy,btnCut;
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
        btnCopy = findViewById(R.id.copyButton);
        btnCut = findViewById(R.id.cut_button);
        mediator.setStatusbar(statusText);
        mediator.setFilerDir(getFilesDir());

        btnRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.setState(new RectState(mediator));
                mediator.setStatusText("Rectangle");
            }
        });
        btnRect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Create rectangle shape");
                return true;
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
        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Create new canvas");
                return true;
            }
        });
        btnOval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.setState(new OvalState(mediator));
                mediator.setStatusText("Oval");
            }
        });
        btnOval.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Create Oval shape");
                return true;
            }
        });
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.newBuilder().setColor(Color.WHITE).setDialogId(2).show(activity);
            }
        });
        btnColor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Set color for selected drawing and next drawing");
                return true;
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
                mediator.setStatusText("Help: Create 11-sided polygon");
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
        btnStar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Create star shape");
                return true;
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
        btnSelect.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Select drawing(s)");
                return true;
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
        btnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Delete selected drawing(s)");
                return true;
            }
        });
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
//                mediator.savepic(getFilesDir().getAbsolutePath());
                //
                if(mediator.selectedDrawings.isEmpty()){
                    mediator.setStatusText("Nothing is selected");
                }else{
                    mediator.copy();
                    mediator.setState(new CopyState(mediator));
                    mediator.setStatusText("Copied to buffer, ready to be paste");
                }
            }
        });
        btnCopy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Copy selected drawing(s) to buffer");
                return true;
            }
        });
        btnCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediator.selectedDrawings.isEmpty()){
                    mediator.setStatusText("Nothing is selected");
                }else{
                    mediator.cut();
                    mediator.setState(new CutState(mediator));
                    mediator.setStatusText("Copied to buffer, ready to be paste");
                }
            }
        });
        btnCut.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Cut selected drawing(s) to buffer");
                return true;
            }
        });
    }


    @Override
    public void onColorSelected(int dialogId, int color) {
        if(dialogId==2){
            btnColor.setBackgroundColor(color);
            mediator.currentColor = color;
            if(!mediator.selectedDrawings.isEmpty()){
                mediator.setStatusText("Color changed");
//                mediator.getLastDrawing().fillColor = color;
                mediator.changeSelectedFillColor(color);
                mediator.repaint();
            }else{
//                mediator.setStatusText("Nothing is selected. Please select something");
            }

        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
    }
}
