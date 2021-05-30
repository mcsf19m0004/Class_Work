package com.example.gomart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gomart.R;
import com.example.gomart.models.NewProoductsModel;
import com.example.gomart.models.PopularModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating, name, description, price;
    Button addToCart, buyNow;
    ImageView addItems, removeItem;


    //new product
    NewProoductsModel newProoductsModel= null;

    //popular product
    PopularModel popularModel=null;

    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        firestore=FirebaseFirestore.getInstance();
        final  Object object=getIntent().getSerializableExtra("detailed");

        if(object instanceof NewProoductsModel)
        {
            newProoductsModel=(NewProoductsModel) object;
        }
        else if(object instanceof PopularModel)
        {
            popularModel=(PopularModel) object;

        }
        detailedImg=findViewById(R.id.detailed_img);
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

        }

        //popular product
        if(popularModel!=null)
        {
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(detailedImg);
            name.setText(popularModel.getName());
            rating.setText(popularModel.getRating());
            description.setText(popularModel.getDescription());
            price.setText(String.valueOf(popularModel.getPrice()));

        }

    }
}