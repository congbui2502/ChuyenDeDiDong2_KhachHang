package fragment;



import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.shipper.R;
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
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GooGleFragment extends Fragment implements OnMapReadyCallback {
    private String diaChiQuan;
    private MarkerOptions place1, place2;
    private LatLng lnFirst,lnLast;
    private Polyline currentPolyline;
    private boolean isPermissionGrated;
    public static GoogleMap mGoogleMap;
    private FloatingActionButton fab;
    private FusedLocationProviderClient mLocationClient;
    private String TAG = "so47492459";
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final int INITIAL_REQUEST=1337;

    public GooGleFragment(String diaChiQuan) {
        this.diaChiQuan=diaChiQuan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.google_map_fragment,container,false);

        requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        fab=view.findViewById(R.id.fab);
        isPermissionGrated=true;
        initMap();


        if (!canAccessLocation() || !canAccessContacts()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }else {
            getActivity().finish();
        }

        mLocationClient=new FusedLocationProviderClient(getContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(),"Vui òng cấp quyền truy cập",Toast.LENGTH_SHORT).show();

                }else {
                    getCurrLoc();
                }


            }
        });
        return view;
    }

    private void initMap() {
        if(isPermissionGrated)
        {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
            supportMapFragment.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap=googleMap;
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            Toast.makeText(getContext(),"Vui lòng cấp quyền truy cập",Toast.LENGTH_SHORT).show();
        }
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
        return (PackageManager.PERMISSION_GRANTED == checkSelfPermission(getContext(),perm));
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
                    gotoLocation(latLng);
                }else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
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
        LatLng lnLast= getLocationFromAddress(getContext(),"cà phê Thiên đường, pơng drang,Krông Buk, Đắk Lăk");
        place2 = new MarkerOptions().position(lnLast).title("Location 2");




        mGoogleMap.addMarker(place1);
        mGoogleMap.addMarker(place2);
        List<LatLng> path =new ArrayList<>();
        path.add(lnFirst);
        path.add(lnLast);
//        //Draw the polyline
//        setAddress(lnFirst,lnLast);

        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mGoogleMap.addPolyline(opts);
        }

//        LatLng lnLast1= getLocationFromAddress(MainActivity2.this,"thôn 15, pơng drang,Krông Buk, Đắk Lăk");
//        LatLng lnLast2= getLocationFromAddress(MainActivity2.this,"thôn 9, pơng drang,Krông Buk, Đắk Lăk");
//
//        CalculationByDistance(lnLast1,lnLast2);

    }

}

