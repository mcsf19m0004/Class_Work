package com.example.gomart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gomart.R;
import com.example.gomart.adapters.AddressAdapter;
import com.example.gomart.models.AddressModel;
import com.example.gomart.models.NewProoductsModel;
import com.example.gomart.models.PopularModel;
import com.example.gomart.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddres {


    Button addAddres, addAddressBtn, paymentBtn;

    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Toolbar toolbar;
    String mAddress="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);



        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        toolbar=findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //get data from detailed activity
        Object obj=getIntent().getSerializableExtra("item");


        recyclerView=findViewById(R.id.address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        addressModelList=new ArrayList<>();
        addressAdapter=new AddressAdapter(getApplicationContext(),addressModelList,this );
        recyclerView.setAdapter(addressAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for(DocumentSnapshot doc : task.getResult().getDocuments())
                    {
                        AddressModel addressModel=doc.toObject(AddressModel.class);
                        addressModelList.add(addressModel);
                        addressAdapter.notifyDataSetChanged();

                    }
                }
            }
        });

        paymentBtn=findViewById(R.id.payment_btn);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double amount=0.0;
                if(obj instanceof NewProoductsModel)
                {
                    NewProoductsModel newProoductsModel=(NewProoductsModel) obj;
                    amount=newProoductsModel.getPrice();
                }
                if(obj instanceof PopularModel)
                {
                    PopularModel popularModel=(PopularModel) obj;
                    amount=popularModel.getPrice();
                }
                if(obj instanceof ShowAllModel)
                {
                    ShowAllModel showAllModel=(ShowAllModel) obj;
                    amount=showAllModel.getPrice();
                }
                Intent intent=new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("amount" , amount);
                startActivity(intent);

            }
        });
        addAddres=findViewById(R.id.add_address_btn);

        addAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));

            }
        });
    }

    @Override
    public void setAddress(String address) {

        mAddress=address;

    }
}