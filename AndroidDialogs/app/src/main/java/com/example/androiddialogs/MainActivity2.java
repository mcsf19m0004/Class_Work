package com.example.androiddialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button showItemListButton;

    final CharSequence[] colors = { "Pink", "Red", "Yellow", "Blue" };
    ArrayList<Integer> slist = new ArrayList();
    boolean icount[] = new boolean[colors.length];
    String msg ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        showItemListButton= findViewById(R.id.buttonList);
    }

    public void showItemList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Choose Colors")
                .setMultiChoiceItems(colors,icount, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
                        if (arg2) {
                            // If user select a item then add it in selected items
                            slist.add(arg1);
                        } else if (slist.contains(arg1)) {
                            // if the item is already selected then remove it
                            slist.remove(Integer.valueOf(arg1));
                        }
                    }
                })      .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        msg = "";
                        for (int i = 0; i < slist.size(); i++) {
                            msg = msg + "\n" + (i + 1) + " : " + colors[slist.get(i)];
                        }
                        Toast.makeText(getApplicationContext(), "Total " + slist.size() + " Items Selected.\n" + msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity2.this,"No Option Selected",Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog dialog  = builder.create();
        dialog.show();
    }


}