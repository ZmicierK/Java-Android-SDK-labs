package com.example.lab7_1;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button myButton = new Button(this);
        myButton.setText("Права_Main");
        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.addView(myButton);
        setContentView(myLayout);

        myButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                StringBuilder permissions = new StringBuilder();
                permissions.append("Доступ к: ");

                if (hasPermission("android.permissions.CAMERA")) {
                    permissions.append("\n камере");
                }
                if (hasPermission("android.permissions.READ_CONTACTS")){
                    permissions.append("\n чтению контактов");
                }
                if (hasPermission("android.permissions.WRITE_CONTACTS")){
                    permissions.append("\n записи контактов");
                }
                if (hasPermission("android.permissions.WIFI_STATE")){
                    permissions.append("\n состоянию WI-FI");
                }
                if (hasPermission("android.permissions.BATTERY_STATE")){
                    permissions.append("\n состоянию баттареи");
                }
                MessageBox(permissions.toString());
            }
        });
    }

    public boolean hasPermission(String permission)
    {
        try {
            PackageInfo info = getPackageManager()
                    .getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.permissions != null) {
                for (int i = 0; i < info.permissions.length; i++) {
                    if (permission.equals(info.permissions[i].name)) return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}