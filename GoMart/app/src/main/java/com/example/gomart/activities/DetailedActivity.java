package com.example.gomart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gomart.R;
import com.example.gomart.models.NewProoductsModel;
import com.example.gomart.models.PopularModel;
import com.example.gomart.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating, name, description, price, quantity;
    Button addToCart, buyNow;
    ImageView addItems, removeItem;

    Toolbar toolbar;

    int totalQuantity=1;

    int totalPrice=0;
    //new product
    NewProoductsModel newProoductsModel= null;

    //popular product
    PopularModel popularModel=null;

    //show all product
    ShowAllModel showAllModel;

    FirebaseAuth auth;
    private FirebaseFirestore firestore=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar=findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        final  Object object=getIntent().getSerializableExtra("detailed");

        if(object instanceof NewProoductsModel)
        {
            newProoductsModel=(NewProoductsModel) object;
        }
        else if(object instanceof PopularModel)
        {
            popularModel=(PopularModel) object;

        }
        else if(object instanceof ShowAllModel)
        {
            showAllModel=(ShowAllModel) object;

        }


        detailedImg=findViewById(R.id.detailed_img);

        quantity=findViewById(R.id.quantity);
        rating=findViewById(R.id.rating);
        name=findViewById(R.id.detail_name);
        description=findViewById(R.id.detailed_desc);
        price=findViewById(R.id.detailed_price);

        addToCart=findViewById(R.id.add_to_cart);
        buyNow=findViewById(R.id.buy_now);

        addItems=findViewById(R.id.detailed_img);
        removeItem=findViewById(R.id.detailed_img);

        //new product
        if(newProoductsModel!=null)
        {
            Glide.with(getApplicationContext()).load(newProoductsModel.getImg_url()).into(detailedImg);
            name.setText(newProoductsModel.getName());
            rating.setText(newProoductsModel.getRating());
            description.setText(newProoductsModel.getDescription());
            price.setText(String.valueOf(newProoductsModel.getPrice()));

            totalPrice=newProoductsModel.getPrice()*totalQuantity;

        }

        //popular product
        if(popularModel!=null)
        {
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(detailedImg);
            name.setText(popularModel.getName());
            rating.setText(popularModel.getRating());
            description.setText(popularModel.getDescription());
            price.setText(String.valueOf(popularModel.getPrice()));

            totalPrice=popularModel.getPrice()*totalQuantity;

        }

        //show all product
        if(showAllModel!=null)
        {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));

            totalPrice=showAllModel.getPrice()*totalQuantity;

        }

        //Buy Now

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(DetailedActivity.this, AddressActivity.class);

               if(newProoductsModel!=null)
               {
                   intent.putExtra("item", newProoductsModel);

               }
               if(popularModel!=null)
               {
                   intent.putExtra("item", popularModel);

               }
               if(showAllModel!=null)
               {
                   intent.putExtra("item", showAllModel);

               }

                startActivity(intent);

            }
        });
        //addTo Cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });


        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity<10)
                {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if(newProoductsModel!=null)
                    {
                        totalPrice=newProoductsModel.getPrice()*totalQuantity;
                    }
                    if(popularModel!=null)
                    {
                        totalPrice=popularModel.getPrice()*totalQuantity;
                    }
                    if(showAllModel!=null)
                    {
                        totalPrice=showAllModel.getPrice()*totalQuantity;
                    }
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity>1)
                {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                }
            }
        });
    }


    private void addToCart()
    {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd, YYYY");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(currentDate.getTimeZone());

        final HashMap<String, Object> cartMap= new HashMap<>();
        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);


        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(DetailedActivity.this,"Added to cart",Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}