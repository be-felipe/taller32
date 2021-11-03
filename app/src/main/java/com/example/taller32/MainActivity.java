package com.example.taller32;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {
   TextView r1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     r1=findViewById(R.id.hola);


        try {
            JSONObject n1= new JSONObject(readRawResource(R.raw.locations));
            String title = n1.getString("locations");
            JSONArray jsonArray= n1.getJSONArray("locations");
          for(int i=0;i<jsonArray.length();i++){
              JSONObject jo_inside = jsonArray.getJSONObject(i);
              jo_inside.get("latitude");
              r1.setText(jo_inside.get("latitude").toString());
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void eventMapas(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private String leer_JSON() {


        String json_string = readRawResource(R.raw.locations);
        return json_string;

    }
    public String readRawResource(@RawRes int res) {
        return readStream(this.getResources().openRawResource(res));
    }

    private String readStream(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

/*
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/


}