package com.example.mypaintapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
    MainActivity activity = this;
    Mediator mediator;
    CanvasView canvasView;
    ImageButton btnRect,btnReset,btnOval,btnColor,btnHenda,btnStar,btnSelect,btnDelete,btnCopy,btnCut,btnSave,btnShadow;
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
        btnSave = findViewById(R.id.saveButton);
        btnShadow = findViewById(R.id.shadowButton);
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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.selectedDrawings.removeAllElements();
                mediator.repaint();
                saveImage(getBitmapFromView(canvasView),"placeholder_only");
            }
        });
        btnSave.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Save canvas to Pictures");
                return true;
            }
        });
        btnShadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediator.toggleShadow();
            }
        });
        btnShadow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mediator.setStatusText("Help: Toggle shadow for selected drawing");
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

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private void saveImage(Bitmap finalBitmap, String image_name) {

        Calendar cal = Calendar.getInstance();
                /*
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(Intent.createChooser(i, "Choose directory"), 9999);
                System.out.println(i.getData());
                System.out.println("_"+cal.get(YEAR)+cal.get(MONTH)+cal.get(DAY_OF_MONTH)+cal.get(Calendar.HOUR_OF_DAY)+"-"+cal.get(Calendar.MINUTE)+"-"+cal.get(Calendar.SECOND));
*/
        String name = "_"+cal.get(YEAR)+cal.get(MONTH)+cal.get(DAY_OF_MONTH)+cal.get(Calendar.HOUR_OF_DAY)+"-"+cal.get(Calendar.MINUTE)+"-"+cal.get(Calendar.SECOND);




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "/Canvas" + name+ ".jpg";
//        String fname = "/Canvas" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);

        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            mediator.setStatusText("Saved to:"+root+fname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
