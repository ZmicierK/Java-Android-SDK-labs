package com.example.lab6_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;


public class MainActivity extends Activity {
    EditText fileName,lName, group;
    Spinner faculty;
    Button write,read;
    TextView filecon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = (EditText) findViewById(R.id.fileName);
        lName = (EditText) findViewById(R.id.lName);
        group = (EditText) findViewById(R.id.group);
        faculty = (Spinner) findViewById(R.id.faculty);
        filecon = (TextView) findViewById(R.id.filecon);

    }
    public void onWriteClick(View view)
    {
        String filename = fileName.getText().toString();

        FileOperations fop = new FileOperations();
        if(fop.write(fileName.getText().toString(), lName.getText().toString(), group.getText().toString(), faculty.getSelectedItem().toString())){
            Toast.makeText(getApplicationContext(),"Данные записаны в файл " + filename + ".txt", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Ошибка ввода/вывода", Toast.LENGTH_SHORT).show();

        }
    }
    public void onReadClick(View view) {
        String readfilename = fileName.getText().toString();

        FileOperations fop = new FileOperations();
        String text = fop.read(readfilename, lName.getText().toString(), group.getText().toString(), faculty.getSelectedItem().toString());
        if(text != null){
            filecon.setText(text);
        }
        else {
            Toast.makeText(getApplicationContext(), "Файл не найден", Toast.LENGTH_SHORT).show();
            filecon.setText(null);
        }

    }
}
