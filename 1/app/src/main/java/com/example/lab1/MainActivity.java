package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMyButtonClick(View view)
    {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        TextView textElement = (TextView) findViewById(R.id.TextBoxHello);
        CheckBox checkboxMess=(CheckBox) findViewById(R.id.checkBoxShowMess);
        if(checkboxMess.isChecked()) {
            Toast toast = Toast.makeText(this, "HelloWorld!", Toast.LENGTH_SHORT);
            View viewT;
            viewT = toast.getView();
            assert viewT != null;
            viewT.setBackgroundColor(color);
            toast.show();
        }
        textElement.setTextColor(color);
    }
}