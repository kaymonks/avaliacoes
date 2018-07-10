package com.example.kaymon.gastronomiakaymon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MapaActivity extends AppCompatActivity {

    private LocationListener listenerCoordendas;
    String categoria;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Log.d("TAG", "Entrou aqui !:--------oioooooooo666");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
            }, 0);
        }

        categoria = getIntent().getExtras().getString("categoria");

        //
        onClickCoordenada();

    }

    @SuppressLint("MissingPermission")
    public void onClickCoordenada() {
        LocationManager gerenciadorLocalizacao = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Log.d("TAG", "categoria:"+categoria);
        if(listenerCoordendas == null){
            Toast.makeText(getApplicationContext(),
                    "Iniciou coordenadas",
                    Toast.LENGTH_SHORT).show();

            listenerCoordendas = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.d("TAG", "Lat:"+location.getLatitude()+" - Long:"+location.getLongitude());

                    Toast.makeText(getApplicationContext(),
                            "Lat: "+location.getLatitude()+" - Long: "+location.getLongitude(),
                            Toast.LENGTH_SHORT).show();

                    Log.d("TAG:", "Lat"+location.getLatitude()+ "- Long:"+location.getLongitude());
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }

            };

            gerenciadorLocalizacao.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    listenerCoordendas
            );
        }else{
            gerenciadorLocalizacao.removeUpdates( listenerCoordendas );
            listenerCoordendas = null;
            Toast.makeText(getApplicationContext(),
                    "Parou coordenadas",
                    Toast.LENGTH_SHORT).show();
        }


    }
}
