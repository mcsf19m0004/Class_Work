package com.example.dialogpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hotchemi.android.rate.AppRate;

public class RateApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        AppRate.with(this)
                .setInstallDays(0)//defaultvalue 10
                .setLaunchTimes(3)
                .setRemindInterval(2)//defualt value 1
        .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
        //AppRate.with(this).clearAgreeShowDialog();//if not thanks click
        AppRate.with(this).showRateDialog(this);
    }

    public void rateBack(View view) {
        Intent intent=new Intent(RateApp.this,MainActivity.class);
        startActivity(intent);
    }
}