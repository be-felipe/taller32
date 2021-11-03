package com.example.taller32;

import androidx.annotation.RawRes;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.taller32.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final String TAG = MainActivity.class.getName();
    private Logger logger = Logger.getLogger(TAG);
    private FusedLocationProviderClient fusedLocationClient;
    private Geocoder mGeocoder;


    double latitud;
    double longitud;
    private final int LOCATION_PERMISSION_ID = 103;
    public static final int REQUEST_CHECK_SETTINGS = 201;
    String locationPerm = Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera


        try {
            JSONObject n1= new JSONObject(readRawResource(R.raw.locations));
            String title = n1.getString("locations");
            JSONArray jsonArray= n1.getJSONArray("locations");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                jo_inside.get("latitude");

                LatLng sydney = new LatLng(Double.valueOf( jo_inside.get("latitude").toString()),Double.valueOf( jo_inside.get("longitude").toString()));
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

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
}