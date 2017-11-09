package com.example.gabrieltopsey.cryptoconvert;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class selectCountry extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyprefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.layout_1:
                //do work
                addCurrency("EUR");
                break;
            case R.id.layout_2:
                //do work
                addCurrency("NZD");
                break;
            case R.id.layout_3:
                //do work
                addCurrency("ZWD");
                break;
            case R.id.layout_4:
                //do work
                addCurrency("GBP");
                break;
            case R.id.layout_5:
                //do work
                addCurrency("SEK");
                break;
            case R.id.layout_6:
                //do work
                addCurrency("ZAR");
                break;
            case R.id.layout_7:
                //do work
                addCurrency("RUB");
                break;
            case R.id.layout_8:
                //do work
                addCurrency("PLN");
                break;
            case R.id.layout_9:
                //do work
                addCurrency("MXN");
                break;
            case R.id.layout_10:
                //do work
                addCurrency("KRW");
                break;
            case R.id.layout_11:
                //do work
                addCurrency("CZK");
                break;
            case R.id.layout_12:
                //do work
                addCurrency("CNY");
                break;
            case R.id.layout_13:
                //do work
                addCurrency("NGN");
                break;
            case R.id.layout_14:
                //do work
                addCurrency("USD");
                break;
            case R.id.layout_15:
                //do work
                addCurrency("INR");
                break;
            case R.id.layout_16:
                //do work
                addCurrency("HKD");
                break;
            case R.id.layout_17:
                //do work
                addCurrency("AUD");
                break;
            case R.id.layout_18:
                //do work
                addCurrency("BRL");
                break;
            case R.id.layout_19:
                //do work
                addCurrency("CAD");
                break;
            case R.id.layout_20:
                //do work
                addCurrency("JPY");
                break;
        }

        Toast.makeText(selectCountry.this, "Currency Added Successfully!!!", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    public void addCurrency(String id)
    {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("currencyList", "");

        List<String> list = new ArrayList<>();

        if(!restoredText.equals("")) {
            String[] currencyList = restoredText.split(",");
            list = new ArrayList<>(Arrays.asList(currencyList));

            Log.d("testing", list.toString());
            if (!list.contains(id)) {
                list.add(id);
            }else{
                Toast.makeText(selectCountry.this, "Currency Already Added!!!", Toast.LENGTH_SHORT).show();
            }
        }else{
            list.add(id);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            sb.append(list.get(i)).append(",");
        }

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("currencyList", sb.toString());
        editor.apply();
    }

//    public boolean contains(ArrayList<List> list, String name) {
//        for (List item : list) {
//            if (item.toString().equals(name)) {
//                return true;
//            }
//        }
//        return false;
//    }

}
