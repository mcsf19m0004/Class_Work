package com.example.gomart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gomart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText name,address,city,postalCode,phonrNumber;
    Toolbar toolbar;
    Button addAddressBtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        toolbar=findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //previous navigation
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        name=findViewById(R.id.ad_name);
        address=findViewById(R.id.ad_address);
        city=findViewById(R.id.ad_city);
        postalCode=findViewById(R.id.ad_name);
        phonrNumber=findViewById(R.id.ad_phone);

        addAddressBtn=findViewById(R.id.ad_add_address);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=name.getText().toString();
                String userAddress=address.getText().toString();
                String userCity=city.getText().toString();
                String userCode=postalCode.getText().toString();
                String userNumber=phonrNumber.getText().toString();

                String final_address="" ;

                if(!userName.isEmpty())
                {
                    final_address+=userName;
                }
                if(!userAddress.isEmpty())
                {
                    final_address+=userAddress;
                }
                if(!userCity.isEmpty())
                {
                    final_address+=userCity;
                }
                if(!userCode.isEmpty())
                {
                    final_address+=userCode;
                }
                if(!userNumber.isEmpty())
                {
                    final_address+=userNumber;
                }

                if(!userName.isEmpty()&& !userAddress.isEmpty()&& !userCity.isEmpty()&& !userCode.isEmpty()&& !userNumber.isEmpty())
                {
                    Map<String,String> map=new HashMap<>();
                    map.put("userAddres", final_address);

                    firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                    .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(AddAddressActivity.this,"Address Added",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(AddAddressActivity.this, DetailedActivity.class));
                                finish();
                            }
                        }
                    });

                }else
                {
                    Toast.makeText(AddAddressActivity.this,"Kindly fill all fields",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}