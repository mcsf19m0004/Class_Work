package com.example.gomart.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.gomart.R;

import java.util.Objects;

public class SliderAdapter extends PagerAdapter {

   Context context;

   LayoutInflater layoutInflater;
   public SliderAdapter(Context context)
   {
       this.context=context;
   }

   int imagesArray[]={
        R.drawable.slider1,
           R.drawable.slider2,
           R.drawable.slider3
   };
   int headingArray[]={
        R.string.first_slide,
           R.string.seond_slide,
           R.string.third_slide
   };
    int descriptionArray[]={
            R.string.description,
            R.string.description,
            R.string.description
    };
    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(ConstraintLayout) object;
    }

    @Nullable
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliding_layout, container, false);
        ImageView imageView=view.findViewById(R.id.slider_img);
        TextView heading=view.findViewById(R.id.heading);
        TextView description=view.findViewById(R.id.description);

        imageView.setImageResource(imagesArray[position]);
        heading.setText(headingArray[position]);
        description.setText(descriptionArray[position]);
       container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @Nullable Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
