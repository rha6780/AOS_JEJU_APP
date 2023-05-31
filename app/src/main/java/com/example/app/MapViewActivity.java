package com.example.app;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.LocationResult;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.LocationButtonView;
import com.naver.maps.map.widget.ZoomControlView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int Location_PERMISSION_REQUEST_CODE = 1000;
    private NaverMap naverMap;
    private MapView mapView;
    private FusedLocationSource locationSource;
    private double longitude = 126.54777;
    private double latitude = 33.46916;


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


        //아이템 가져오기
        RecyclerView station_list = (RecyclerView) findViewById(R.id.station_list);
        ArrayList<StationItem> arr = new ArrayList<StationItem>();



        stationBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentslayout.getVisibility() == View.VISIBLE) {
                    contentslayout.setVisibility(View.INVISIBLE);

                }
                else {
                    contentslayout.setVisibility(View.VISIBLE);
                    String resultstr = "[Null]";
                    System.out.println("구분선 === ");

                    Location loc = locationSource.getLastLocation();
                    if ( loc != null ) {
                        longitude = loc.getLongitude();
                        latitude = loc.getLatitude();
                    }
                    Log.d("location : ", longitude +" "+ latitude);

                    try {
                        resultstr = new CallAPI().execute().get();
                        JSONObject jsonObject = null;
                        JSONArray jsonArray = null;
                        jsonArray = new JSONArray(resultstr);

                        Log.d("check : ", String.valueOf(jsonArray.get(1)));

                        if (jsonArray!=null) {
                            int count =0;
                            for(int i=0; i<jsonArray.length(); i++) {
                                JSONObject jsonObject1 = null;
                                jsonObject1 = jsonArray.getJSONObject(i);
                                double lon1 = Double.parseDouble(jsonObject1.get("longitude").toString());
                                double lat1 = Double.parseDouble(jsonObject1.get("latitude").toString());
                                Log.d("check : ", lon1+" "+lat1);
                                if(Math.abs(longitude -lon1)<=0.005 && Math.abs(latitude -lat1)<=0.005 && count<10) {
                                    Log.d("check:", Math.abs(longitude -lon1)+"/"+lon1+" "+Math.abs(latitude -lat1)+"/"+lat1);
                                    arr.add(new StationItem(jsonObject1.get("stationname").toString(), Integer.parseInt(jsonObject1.get("stationid").toString())));
                                    count ++;
                                }

                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    StationListAdapter adapter = new StationListAdapter(mapView.getContext(), R.layout.stationitem, arr);
                    station_list.setLayoutManager(new LinearLayoutManager(mapView.getContext()));
                    station_list.setAdapter(adapter);
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

