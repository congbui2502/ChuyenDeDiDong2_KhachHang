package com.example.shipper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {
    private MarkerOptions place1, place2;
    private LatLng lnFirst,lnLast;
    private Polyline currentPolyline;
    private boolean isPermissionGrated;
    public static GoogleMap mGoogleMap;
    private FloatingActionButton fab;
    private FusedLocationProviderClient mLocationClient;
    private String TAG = "so47492459";
    private LatLng lnFinish;

    private ThongTinShop thongTinShop;
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };

    private static final int INITIAL_REQUEST=1337;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();
        if(bundle!=null)
        {
            if(bundle.getSerializable("tts")!=null)
            {
                thongTinShop= (ThongTinShop) bundle.getSerializable("tts");
                lnFinish=getLocationFromAddress(GoogleMapActivity.this,thongTinShop.getShopAddress());
            }else if(bundle.getSerializable("dc")!=null)
            {
                   String     diaChi= bundle.getString("dc");
                lnFinish=getLocationFromAddress(GoogleMapActivity.this,diaChi);
            }

        }

//        requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        fab=findViewById(R.id.fab);
        isPermissionGrated=true;
        initMap();


        if (!canAccessLocation() || !canAccessContacts()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }else {
            finish();
        }

        mLocationClient=new FusedLocationProviderClient(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(GoogleMapActivity.this,"Vui òng cấp quyền truy cập",Toast.LENGTH_SHORT).show();

                }else {
                    getCurrLoc();
                }


            }
        });

    }

    @SuppressLint("MissingPermission")
    private void getCurrLoc() {
        mLocationClient.getLastLocation().addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                Location location = task.getResult();
                if(location!=null)
                {
                    LatLng latLng =new LatLng(location.getLatitude(),location.getLongitude());
                    if(latLng!=null)
                    {
                        gotoLocation(latLng);
                    }else {
                        Toast.makeText(GoogleMapActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }


                }else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(GoogleMapActivity.this);
                    builder1.setMessage("Vui lòng bật định vị của bạn!");
                    builder1.setCancelable(true);

                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            }
        });
    }

    private void gotoLocation(LatLng lnFirst) {
//        LatLng latLng = new LatLng(latitude,longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(lnFirst,18);
        mGoogleMap.moveCamera(cameraUpdate);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        place1 = new MarkerOptions().position(lnFirst).title("Location 1");
        LatLng latLng = getLocationFromAddress(GoogleMapActivity.this,"cà phê thiên Đường, Pơng Drang , Krông Buk , Đăk Lăk");
        place2 = new MarkerOptions().position(lnFinish).title("Location 2");
//        new FetchURL(MainActivity2.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

        mGoogleMap.addMarker(place1);
        mGoogleMap.addMarker(place2);
        List<LatLng> path =new ArrayList<>();
        path.add(lnFirst);
        path.add(lnFinish);
//        //Draw the polyline
//        setAddress(lnFirst,lnLast);

        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            if(opts!=null)
            {
                mGoogleMap.addPolyline(opts);
            }

        }

        LatLng lnLast1= getLocationFromAddress(GoogleMapActivity.this,"thôn 15, pơng drang,Krông Buk, Đắk Lăk");
        LatLng lnLast2= getLocationFromAddress(GoogleMapActivity.this,"thôn 9, pơng drang,Krông Buk, Đắk Lăk");

        CalculationByDistance(lnLast1,lnLast2);

    }

    private void initMap() {
        if(isPermissionGrated)
        {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            supportMapFragment.getMapAsync(this);
        }
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" +
                getString(R.string.google_maps_key);
        return url;
    }



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
      mGoogleMap=googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);


        } else {
            Toast.makeText(GoogleMapActivity.this,"Vui òng cấp quyền truy cập",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private void setAddress(LatLng lnFirst,LatLng laLast)
    {
        List<LatLng> path = new ArrayList();


        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(getString(R.string.google_maps_key))
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, "41.385064,2.173403", "40.416775,-3.70379");
        try {
            com.google.maps.model.DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            Log.e(TAG, "error");
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mGoogleMap.addPolyline(opts);
        }

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

    }



    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }



    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    private boolean canAccessContacts() {
        return(hasPermission(Manifest.permission.READ_CONTACTS));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }


//    @Override
//    public void onTaskDone(Object... values) {
//        if (currentPolyline != null)
//            currentPolyline.remove();
//        currentPolyline = mGoogleMap.addPolyline((PolylineOptions) values[0]);
//    }

    public int CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius=6371;//radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult= Radius*c;
        double km=valueResult/1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec =  Integer.valueOf(newFormat.format(km));
        double meter=valueResult%1000;
        int  meterInDec= Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value",""+valueResult+"   KM  "+kmInDec+" Meter   "+meterInDec);
        Toast.makeText(getApplicationContext(),(int) (Radius * c*1000)+ "",Toast.LENGTH_SHORT).show();
        return ((int) (Radius * c*1000));
    }

    private class DirectionsResult {
    }
}