package com.edufree.harare.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.edufree.harare.R;
import com.edufree.harare.models.Event;
import com.edufree.harare.models.dataManager;
import com.edufree.harare.models.tourGuide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    private Event selectedEvent=null;
    private tourGuide selectedTourGuide=null;
    private static final String TAG="MapActivity";
    private static final String FINE_LOCATION= Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE=1234;
    private static final float DEFAULT_ZOOM=15f;

    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps,mNetwokError;

    //vars
    private boolean mLocationPermisionGranted=false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final LatLngBounds LAT_LNG_BOUNDS=new LatLngBounds(new LatLng(-40,-168),new LatLng(71,136));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                Log.d(TAG,"onMapReady : map ios ready now");

                LatLng Harare=new LatLng(-17.8252,31.0335);
                mMap.addMarker(new MarkerOptions().position(Harare).title("Marker in Harare"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Harare,DEFAULT_ZOOM));
            }
        });

        Intent inforIntent=getActivity().getIntent();
        selectedEvent=inforIntent.getParcelableExtra(dataManager.PASS_EVENT_KEY);
        selectedTourGuide=inforIntent.getParcelableExtra(dataManager.PASS_TOUR_GUIDE_KEY);

        mSearchText=(AutoCompleteTextView)root.findViewById(R.id.input_search);
        mGps=(ImageView)root.findViewById(R.id.ic_gps);
        mNetwokError=(ImageView)root.findViewById(R.id.network_error);
        mNetwokError.setVisibility(View.GONE);

        getLocationPermision();
        init();

        if(!isInternetConnected()){
            mNetwokError.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(),"Please connect device to the internet",Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void init(){
        Log.d(TAG,"init : initialiasing ");

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH ||
                        actionId==EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction()==KeyEvent.ACTION_DOWN ||
                        keyEvent.getAction()==KeyEvent.KEYCODE_ENTER ){
                    geolocate();

                }
                return false;
            }
        });

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick : clicked gps icon");
                getDeviceLocation();
            }
        });

    }
    //isInterConnected checks to se if the device is connected on the internet ,if not an error is displayed
    private boolean isInternetConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
    //geolocate updates the camera and and marker on map to the location seached by the user
    private void geolocate() {
        Log.d(TAG,"geoLocate : geolocating");
        String searchString=mSearchText.getText().toString();
        Toast.makeText(getActivity(),searchString,Toast.LENGTH_SHORT).show();
        Geocoder geocoder=new Geocoder(getActivity());
        List<Address> list=new ArrayList<>();
        //hide the Soft keyboard
        hideSoftKeyboard();
        try{
            list=geocoder.getFromLocationName(searchString,1);
        }catch (Exception e){
            Log.d(TAG,"geoLocate : IOException "+e.getMessage());
        }

        if(list.size()>0){
            Address address=list.get(0);
            Log.d(TAG,"geoLocate : found a location "+address);
            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),DEFAULT_ZOOM,address.getAddressLine(0));
            //Toast.makeText(getApplicationContext(),searchString,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),address.toString(),Toast.LENGTH_SHORT).show();
            // Add a marker in Sydney, Australia, and move the camera.
            //LatLng sydney = new LatLng(-34, 151);
            //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            //LatLng place=new LatLng(address.getLatitude(),address.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(place).title(selectedTourGuide.getTourName()));
        }

    }
    private void geolocate2(String searchText) {
        Log.d(TAG,"geoLocate : geolocating");
        String searchString=searchText;
        Toast.makeText(getActivity(),searchString,Toast.LENGTH_SHORT).show();
        Geocoder geocoder=new Geocoder(getActivity());
        List<Address> list=new ArrayList<>();
        //hide the Soft keyboard
        hideSoftKeyboard();
        try{
            list=geocoder.getFromLocationName(searchString,1);
        }catch (Exception e){
            Log.d(TAG,"geoLocate : IOException "+e.getMessage());
        }

        if(list.size()>0){
            Address address=list.get(0);
            Log.d(TAG,"geoLocate : found a location "+address);
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
            LatLng place=new LatLng(address.getLatitude(),address.getLongitude());
            MarkerOptions options=new MarkerOptions()
                    .position(place)
                    .title("fuck");
            mMap.addMarker(options);
            hideSoftKeyboard();
            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),DEFAULT_ZOOM,address.getAddressLine(0));
        }

    }
    //moveCamera moves the camera to the give latitude and longitude
    private void moveCamera(LatLng latLng,float zoom,String title){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        if(!title.equals("My Location")){
            MarkerOptions options=new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

    }
    //getDeviceLocation gets the location of the device and indicates that on the map given there was permision
    private void getDeviceLocation(){
        Log.d(TAG,"gettingDeviceLocation : getting the current devices location");
        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(getActivity());
        try{
            if(mLocationPermisionGranted){
                Task location=mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"onComplete : foundLocationYeah");
                            Location currentLocation=(Location)task.getResult();
                            moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),DEFAULT_ZOOM,"My Current Location");
                        }else{
                            Log.d(TAG,"onComplete : currentLocation is null");
                            Toast.makeText(getActivity(),"unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.d(TAG,"gettingDeviceLocation : securityException "+e.toString());
        }
    }

    private void getLocationPermision(){
        String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(getActivity(),FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getActivity(),COURSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mLocationPermisionGranted=true;
            }else{
                //ask for those persmisiions
                ActivityCompat.requestPermissions(getActivity(),permissions,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(getActivity(),permissions,LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermisionGranted=false;
        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length>0){
                    for(int a=0;a<grantResults.length;a++){
                        if(grantResults[a]!=PackageManager.PERMISSION_GRANTED){
                            mLocationPermisionGranted=false;
                            return;
                        }
                    }
                    mLocationPermisionGranted=true;
                    //initialize the map yeah ...
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        //InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}