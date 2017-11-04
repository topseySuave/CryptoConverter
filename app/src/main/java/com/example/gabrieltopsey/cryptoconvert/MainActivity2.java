package com.example.gabrieltopsey.cryptoconvert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Context context;
    public RecyclerView mRecyclerView;

    public static final String MY_PREFS_NAME = "MyprefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getBaseContext(), selectCountry.class);
                add.putExtra("ADD_NEW", true);
                startActivity(add);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("currencyList", "");

        if(restoredText != ""){
            String[] currencyList = restoredText.split(",");
            List<String> list = Arrays.asList(currencyList);

            List<DevData> content = new ArrayList<>();
            Log.d("testing",list.toString());
            for (int i = 0; i < list.size(); i++)
            {
                Log.d("testing",list.get(i));
                content.add(new DevData(getCountryName(list.get(i)),getImageName(list.get(i)),list.get(i)));
            }
            Log.d("testing",content.toString());
            mRecyclerView = (RecyclerView) findViewById(R.id.circle_view);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);

            DevRecyclerViewAdapter mAdapter = new DevRecyclerViewAdapter(this, content);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public int getImageName(String code)
    {
        if(code == "EUR"){
            return R.drawable.eur;
        }else if(code == "NZD"){
            return R.drawable.new_zealand;
        }else if(code == "ZWD"){
            return R.drawable.zwd;
        }else if(code == "GBP"){
            return R.drawable.uk;
        }else if(code == "SEK"){
            return R.drawable.sweden;
        }else if(code == "ZAR"){
            return R.drawable.south_africa;
        }else if(code == "RUB"){
            return R.drawable.russia;
        }else if(code == "PLN"){
            return R.drawable.poland;
        }else if(code == "MXN"){
            return R.drawable.mxn;
        }else if(code == "KRW"){
            return R.drawable.krw;
        }else if(code == "CZK"){
            return R.drawable.czech;
        }else if(code == "CNY"){
            return R.drawable.china;
        }else if(code == "NGN"){
            return R.drawable.ngn;
        }else if(code == "USD"){
            return R.drawable.usa;
        }else if(code == "INR"){
            return R.drawable.india;
        }else if(code == "HKD"){
            return R.drawable.hong_kong;
        }else if(code == "AUD"){
            return R.drawable.aud;
        }else if(code == "BRL"){
            return R.drawable.brazil;
        }else if(code == "CAD"){
            return R.drawable.canada;
        }else if(code == "JPY"){
            return R.drawable.japan;
        }
        return 0;
    }

    public String getCountryName(String code)
    {
        if(code == "EUR"){
            return "Euro";
        }else if(code == "NZD"){
            return "New Zealand Dollar";
        }else if(code == "ZWD"){
            return "Zimbabwean Dollar";
        }else if(code == "GBP"){
            return "British Pounds";
        }else if(code == "SEK"){
            return "Swedish Krona";
        }else if(code == "ZAR"){
            return "South African Rand";
        }else if(code == "RUB"){
            return "Russian Rouble";
        }else if(code == "PLN"){
            return "Polish Zloty";
        }else if(code == "MXN"){
            return "Mexican Peso";
        }else if(code == "KRW"){
            return "Korean won";
        }else if(code == "CZK"){
            return "Czech Koruna";
        }else if(code == "CNY"){
            return "Chinese Yuan";
        }else if(code == "NGN"){
            return "Nigerian Naira";
        }else if(code == "USD"){
            return "United states Dollars";
        }else if(code == "INR"){
            return "Indian Rupees";
        }else if(code == "HKD"){
            return "Hong Kong Dollars";
        }else if(code == "AUD"){
            return "Australian Dollars";
        }else if(code == "BRL"){
            return "Brazilian Real";
        }else if(code == "CAD"){
            return "Canadian Dollars";
        }else if(code == "JPY"){
            return "Japanese Yen";
        }

        return null;
    }
}
