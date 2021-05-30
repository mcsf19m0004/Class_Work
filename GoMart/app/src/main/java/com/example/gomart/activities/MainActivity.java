package com.example.gomart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.gomart.R;
import com.example.gomart.fraqments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    Fragment homeFraqment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFraqment=new HomeFragment();
        loadFraqment(homeFraqment);
    }

    private void loadFraqment(Fragment homeFraqment)
    {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homeFraqment);
        transaction.commit();
    }
}