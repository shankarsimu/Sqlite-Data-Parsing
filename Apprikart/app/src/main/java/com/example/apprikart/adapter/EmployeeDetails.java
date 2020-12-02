package com.example.apprikart.adapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.apprikart.R;
import com.example.apprikart.database.DbHandler;
import com.example.apprikart.ui.MainActivity;
import com.example.apprikart.ui.User;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeDetails extends AppCompatActivity {
    Intent intent;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employeelist);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        final ListAdapter adapter = new SimpleAdapter(EmployeeDetails.this, userList, R.layout.employee,
                new String[]{"name", "designation", "mobileno", "address"},
                new int[]{R.id.empName, R.id.empDeg, R.id.empMob, R.id.empAdd});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Object o = lv.getItemAtPosition(position);
                    HashMap<String,String>hashMap= (HashMap<String, String>) lv.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EmployeeDetails.this, User.class);
                intent.putExtra("name", hashMap.get("name"));
                intent.putExtra("designation", hashMap.get("designation"));
                intent.putExtra("mobileno", hashMap.get("mobileno"));
                intent.putExtra("address", hashMap.get("address"));
                startActivity(intent);
            }
        });
        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(EmployeeDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
