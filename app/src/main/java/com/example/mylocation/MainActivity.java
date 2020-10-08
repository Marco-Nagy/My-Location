package com.example.mylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit= new Retrofit
                .Builder()
                .baseUrl(" http://ip-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallableInt callable =retrofit.create(CallableInt.class);
        Call<IpModel> modelCall= callable.getData();
        modelCall.enqueue(new Callback<IpModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                ProgressBar pb =findViewById(R.id.pb);
                pb.setVisibility(View.INVISIBLE);
                IpModel ipModel=response.body();
                assert ipModel != null;
                String country=ipModel.getCountry();
                double lat=ipModel.getLat();
                double lon=ipModel.getLon();
                Toast.makeText(MainActivity.this, country+lat+lon, Toast.LENGTH_SHORT).show();
                TextView tvlat =findViewById(R.id.tlat);
                TextView tvlon =findViewById(R.id.tlon);
                tvlat.setText("Lat:"+lat);
                tvlon.setText("Lon: "+lon);
                /**
                Uri location =Uri.parse("google.streetview:cbll=lat,lon");
                Intent map = new Intent(Intent.ACTION_VIEW, location);
                map.setPackage("com.google.android.apps.maps");
                startActivity(map);**/

            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                ProgressBar pb =findViewById(R.id.pb);
                pb.setVisibility(View.INVISIBLE);
                Log.d("json",t.getMessage());

            }
        });
    }

    public void Location(View view) {
        Retrofit retrofit= new Retrofit
                .Builder()
                .baseUrl(" http://ip-api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallableInt callable =retrofit.create(CallableInt.class);
        Call<IpModel> modelCall= callable.getData();
        modelCall.enqueue(new Callback<IpModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                ProgressBar pb = findViewById(R.id.pb);
                pb.setVisibility(View.INVISIBLE);
                IpModel ipModel = response.body();
                assert ipModel != null;
                String country = ipModel.getCountry();
                double lat = ipModel.getLat();
                double lon = ipModel.getLon();
                Uri location =Uri.parse("geo:q=lat,lon,country");
                Intent map = new Intent(Intent.ACTION_VIEW, location);
                map.setPackage("com.google.android.apps.maps");
                startActivity(map);

            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                ProgressBar pb = findViewById(R.id.pb);
                pb.setVisibility(View.INVISIBLE);
                Log.d("json", t.getMessage());
            }
        });

}
}