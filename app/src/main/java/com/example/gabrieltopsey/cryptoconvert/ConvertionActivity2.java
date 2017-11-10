package com.example.gabrieltopsey.cryptoconvert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConvertionActivity2 extends AppCompatActivity {

    TextView countryShortCode;

    ImageView thumbnail;
    TextView btcConvert;
    TextView ethConvert;

    EditText convertInput;
    Button convertBtn;

    float eth = 0, btc = 0;

    String code;

    RequestQueue queue;

    public static final String MY_PREFS_NAME = "MyprefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertion2);

        code = getIntent().getStringExtra("code");
        int image = getIntent().getIntExtra("image", 0);

        countryShortCode = (TextView) findViewById(R.id.currency_text);

        thumbnail = (ImageView) findViewById(R.id.country_thumb);
        btcConvert = (TextView) findViewById(R.id.btc_convert);
        ethConvert = (TextView) findViewById(R.id.eth_convert);

        convertInput = (EditText) findViewById(R.id.convert_input);
        convertBtn = (Button) findViewById(R.id.convert_btn);

        thumbnail.setImageResource(image);
        countryShortCode.setText(code);
    }

    public void onClick(View view)
    {
        float newBTC, newETH;
        String inputToConvert = convertInput.getText().toString();

        if(!inputToConvert.equals("")){
            float convertValue = Float.parseFloat(inputToConvert);

            newBTC = convertValue * btc;
            newETH = convertValue * eth;

            String strBtc = "" + newBTC;
            String strEth = "" + newETH;

            btcConvert.setText(strBtc);
            ethConvert.setText(strEth);
        }else{
            Toast.makeText(ConvertionActivity2.this, "Please Input Any Amount!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onStart()
    {
        super.onStart();
        String btc_url = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=" + code;
        String eth_url = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=" + code;

        queue = Volley.newRequestQueue(this);

        connect(btc_url, "BTC");
        connect(eth_url, "ETH");
    }

    private void connect(String url, final String codeName){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub
                Log.d("Response json", "response: "+response.toString());
                try {
                    convertJSONtoObject(response, codeName);
                } catch (JSONException e) {
                    Log.d("Response json", "Error process the method... ");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                getSavedValues();
                Toast.makeText(ConvertionActivity2.this, "Error in connection!!!", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonRequest);
    }


    private void convertJSONtoObject(JSONObject resp,String curr) throws JSONException {

        double value = resp.getDouble(code);

        //SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        //String restoredText = prefs.setString(curr + "_" + code, "");

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putFloat(curr + "_" + code, (float) value);
        editor.apply();

        if (curr.equals("BTC")) {
            btc = (float) value;
        }else{
            eth = (float) value;
        }
    }

    private void getSavedValues()
    {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        btc = prefs.getFloat("BTC_" + code, 0);
        eth = prefs.getFloat("ETH_" + code, 0);
    }
}
