package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onBlueButtonClick(View view)
    {
        LinearLayout screenLayout = (LinearLayout) findViewById(R.id.img_layout);
        screenLayout.setBackgroundColor(Color.BLUE);
        Toast.makeText(this, "Hello blue", Toast.LENGTH_SHORT).show();
    }
    public void onRedButtonClick(View view)
    {
        LinearLayout screenLayout = (LinearLayout) findViewById(R.id.img_layout);
        screenLayout.setBackgroundColor(Color.RED);
        Toast.makeText(this, "Hello red", Toast.LENGTH_SHORT).show();
    }
    public void onYellowButtonClick(View view)
    {
        LinearLayout screenLayout = (LinearLayout) findViewById(R.id.img_layout);
        screenLayout.setBackgroundColor(Color.YELLOW);
        Toast.makeText(this, "Hello yellow", Toast.LENGTH_SHORT).show();
    }
    public void onGreenButtonClick(View view)
    {
        LinearLayout screenLayout = (LinearLayout) findViewById(R.id.img_layout);
        screenLayout.setBackgroundColor(Color.GREEN);
        Toast.makeText(this, "Hello green", Toast.LENGTH_SHORT).show();
    }
    public void onImgButtonClick(View view)
    {
        LinearLayout screenLayout = (LinearLayout) findViewById(R.id.img_layout);
        screenLayout.setBackgroundResource(R.drawable.one);
        Toast.makeText(this, "Hello image", Toast.LENGTH_SHORT).show();
    }
    public void onDevButtonClick(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Разработчик")
                .setMessage("Сакович Д.В., гр. 820604")
                .setPositiveButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}