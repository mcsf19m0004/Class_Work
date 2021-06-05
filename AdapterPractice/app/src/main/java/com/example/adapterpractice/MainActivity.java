package com.example.adapterpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> friendArrayList;
    EditText editText;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.myListView);
        Button button=findViewById(R.id.btnAdd);
        editText=findViewById(R.id.editTextAdd);

        String[] friendList={"sana" ,"rabia" ,"hifza"};
        friendArrayList=new ArrayList<String>();
        friendArrayList.add("sana");
        friendArrayList.add("rabia");
        friendArrayList.add("hifza");

        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,friendArrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Log.d("You Clicked",friendArrayList.get(i));
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("Friend",friendArrayList.get(i));
                startActivity(intent);
            }
        });
    }


    public void Add(View view) {
        friendArrayList.add(editText.getText().toString());
        arrayAdapter.notifyDataSetChanged();
    }
}