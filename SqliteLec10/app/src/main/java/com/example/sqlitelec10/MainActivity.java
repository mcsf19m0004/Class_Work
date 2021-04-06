package com.example.sqlitelec10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd;
    Button buttonView;
    EditText editTextNmae;
    EditText editTextAge;
    Switch switchIsActive;
    ListView listViewDetails;
    DBHelper dbHelper;

    ArrayAdapter<CustomerModels> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd=findViewById(R.id.ButtonAdd);
        buttonView=findViewById(R.id.buttonView);
        editTextAge=findViewById(R.id.editTextAge);
        editTextNmae=findViewById(R.id.editTextName);
        switchIsActive=findViewById(R.id.switchIsActive);
        listViewDetails=findViewById(R.id.ListViewDetails);
        refreshData();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            CustomerModels customerModels;

            @Override
            public void onClick(View view) {


                try {
                    customerModels = new CustomerModels(editTextNmae.getText().toString(),
                            Integer.parseInt(editTextAge.getText().toString()), switchIsActive.isChecked(), 1);

                   // Toast.makeText(MainActivity.this, customerModels.toString(), Toast.LENGTH_LONG).show();
                   // Toast.makeText(MainActivity.this,"Add Record", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                boolean b = dbHelper.addStudent(customerModels);
                refreshData();
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();

            }

        });


    }




    private void refreshData() {
        DBHelper dbHelper=new DBHelper(MainActivity.this);
        List<CustomerModels> allStudents=dbHelper.getAllRecord();
        Toast.makeText(MainActivity.this,allStudents.toString(), Toast.LENGTH_LONG).show();
        arrayAdapter=new ArrayAdapter<CustomerModels>(MainActivity.this, android.R.layout.simple_list_item_1,allStudents);
        listViewDetails.setAdapter(arrayAdapter);
    }
}