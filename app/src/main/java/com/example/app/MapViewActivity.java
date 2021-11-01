package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.LocationButtonView;
import com.naver.maps.map.widget.ZoomControlView;

import java.util.ArrayList;

public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int Location_PERMISSION_REQUEST_CODE = 1000;
    private NaverMap naverMap;
    private MapView mapView;
    private FusedLocationSource locationSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment_layout);

        locationSource = new FusedLocationSource(this, Location_PERMISSION_REQUEST_CODE);
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        Button stationBnt= (Button) findViewById(R.id.station_button);
        Button busBnt = (Button) findViewById(R.id.busAI);
        Button addBnt = (Button) findViewById(R.id.add_button);
        RelativeLayout contentslayout = (RelativeLayout) findViewById(R.id.contents_layout);
        contentslayout.setVisibility(View.INVISIBLE);
        RecyclerView station_list = (RecyclerView) findViewById(R.id.station_list);


        ArrayList<StationItem> arr = new ArrayList<StationItem>();
        arr.add(new StationItem("fine", 0));
        StationListAdapter adapter = new StationListAdapter(this, R.layout.stationitem, arr);
        station_list.setLayoutManager(new LinearLayoutManager(this));
        station_list.setAdapter(adapter);

        stationBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentslayout.getVisibility() == View.VISIBLE) {
                    contentslayout.setVisibility(View.INVISIBLE);
                }
                else {
                    contentslayout.setVisibility(View.VISIBLE);
                }

            }
        });

        busBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentslayout.getVisibility() == View.VISIBLE) {
                    contentslayout.setVisibility(View.INVISIBLE);
                }
                else {
                    contentslayout.setVisibility(View.VISIBLE);
                }

            }
        });

        addBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentslayout.getVisibility() == View.VISIBLE) {
                    contentslayout.setVisibility(View.INVISIBLE);
                }
                else {
                    contentslayout.setVisibility(View.VISIBLE);
                }

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if(!locationSource.isActivated()) {
                naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(NaverMap naverMap) {
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomControlEnabled(false);
        uiSettings.setLocationButtonEnabled(false);

        LocationButtonView locationButton = findViewById(R.id.mapLocation);
        locationButton.setMap(naverMap);
        naverMap.setLocationSource(locationSource);
        ZoomControlView zoomControlView = findViewById(R.id.mapZoom);
        zoomControlView.setMap(naverMap);
    }
}

