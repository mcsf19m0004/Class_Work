package com.example.fraqments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity3  extends Fragment {



    public MainActivity3() {
        // Required empty public constructor
    }

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main3, container, false);

        button = view.findViewById(R.id.btn_frgm2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Fragment Two Button", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}