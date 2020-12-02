package com.example.apprikart.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apprikart.database.DbHandler;
import com.example.apprikart.adapter.EmployeeDetails;
import com.example.apprikart.R;

public class MainActivity extends AppCompatActivity {

    EditText ename, edesignation, mobNo, eaddress;
    Button addButton;
    Intent intent;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ename=(EditText) findViewById(R.id.empName);
        edesignation=(EditText)findViewById(R.id.empDeg);
        mobNo=(EditText)findViewById(R.id.empMob);
        eaddress=(EditText)findViewById(R.id.empAdd);
        addButton=(Button) findViewById(R.id.empSaveBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = ename.getText().toString() ;
                String designation = edesignation.getText().toString();
                String mobileno = mobNo.getText().toString();
                String address = eaddress.getText().toString();
                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(name, designation,mobileno,address);
                intent = new Intent(getApplicationContext(), EmployeeDetails.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();


                //-----------------------------------------------------//
//                intent = new Intent(getApplicationContext(), EmployeeDetails.class);
//                startActivity(intent);
            }
        });
    }

}