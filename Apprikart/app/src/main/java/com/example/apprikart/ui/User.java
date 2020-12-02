package com.example.apprikart.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apprikart.R;

public class User extends AppCompatActivity {

    TextView ename, edesignation, mobNo, eaddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        ename = (TextView) findViewById(R.id.empName);
        edesignation = (TextView) findViewById(R.id.empDeg);
        mobNo = (TextView) findViewById(R.id.empMob);
        eaddress = (TextView) findViewById(R.id.empAdd);
        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("name");
        String value2 = extras.getString("designation");
        String value3 = extras.getString("mobileno");
        String value4 = extras.getString("address");
        ename.setText(value1);
        edesignation.setText(value2);
        mobNo.setText(value3);
        eaddress.setText(value4);


    }
}
