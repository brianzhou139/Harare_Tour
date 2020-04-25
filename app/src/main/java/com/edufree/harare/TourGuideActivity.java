package com.edufree.harare;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edufree.harare.models.Guide;
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

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class TourGuideActivity extends AppCompatActivity {
    private static final String ADD_NOTE_KEY="com.edufree.harare_add_note";
    private static final String TAG="MapActivity";
    private static final String FINE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE=1234;
    private static final float DEFAULT_ZOOM=15f;

    private ArrayList<Guide> myGuideList;
    private int chosenGuide;
    private ArrayList<tourGuide> mTourGuideList=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TourGuideInnerAdapter adapter;

    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps,mTourImage;

    //vars
    private boolean mLocationPermisionGranted=false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final LatLngBounds LAT_LNG_BOUNDS=new LatLngBounds(new LatLng(-40,-168),new LatLng(71,136));

    //Views
    private RelativeLayout MapView;
    private RelativeLayout RecyListView;
    private  TextView mAdress,mPhone,mEditNoteText,mDetail;
    private LinearLayout mEdit;
    private Button vDetail;
    private dataManager data;
    private  ImageView mImage,mExit;
    private String currentOpenedTourName=null;
    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide);
        mTopToolbar=(Toolbar)findViewById(R.id.my_toolbar_tour);
        setSupportActionBar(mTopToolbar);

        mSearchText=(AutoCompleteTextView) findViewById(R.id.input_search);
        mGps=(ImageView)findViewById(R.id.ic_gps);
        mTourImage=(ImageView)findViewById(R.id.ic_tourImage);
        mDetail=(TextView)findViewById(R.id.mTour_detail_id);
        mExit=(ImageView)findViewById(R.id.exit_map_id);

        mAdress=(TextView)findViewById(R.id.address_id);
        mPhone=(TextView)findViewById(R.id.phone_id);
        mEdit=(LinearLayout)findViewById(R.id.editnote_linear_id);
        mEditNoteText=(TextView)findViewById(R.id.editNote_text);

        MapView=(RelativeLayout)findViewById(R.id.myMap_relative_view);
        RecyListView=(RelativeLayout)findViewById(R.id.myReyclerList_relative_view);


        Intent mIntent=getIntent();
        chosenGuide=mIntent.getIntExtra(dataManager.PASS_GUIDE_KEY,0);
        mRecyclerView=(RecyclerView)findViewById(R.id.tour_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new TourGuideInnerAdapter(mTourGuideList);
        mRecyclerView.setAdapter(adapter);

        getLocationPermision();
        init();
        MapView.setVisibility(View.GONE);
    }
    //initmap initializes  googleMap and moves camera to tour selected by user
    private void  initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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
    }
    //init handles onCliclicklisterners and guides selected by user
    private void init(){
        data=new dataManager(this);
        myGuideList=data.getGuides();
        ArrayList<tourGuide> mTempList=data.getTourGuides();
        Guide target=myGuideList.get(chosenGuide);
        getSupportActionBar().setTitle(target.getGuide_name());

        for(tourGuide tour:mTempList){
            if(tour.getTourCategory().matches(target.getGuide_name())){
                mTourGuideList.add(tour);
            }
        }

        Log.d(TAG,"init : initialiasing ");
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH ||
                        actionId==EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction()==KeyEvent.ACTION_DOWN ||
                        keyEvent.getAction()==KeyEvent.KEYCODE_ENTER ){
                    //execute method for searchign here yeah   .............
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

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle user click when edit is pressed yeah...
                Intent addIntent=new Intent(getApplicationContext(),AddNoteActivity.class);
                addIntent.putExtra(ADD_NOTE_KEY,currentOpenedTourName);
                startActivity(addIntent);
            }
        });

        mEditNoteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent=new Intent(getApplicationContext(),AddNoteActivity.class);
                addIntent.putExtra(ADD_NOTE_KEY,currentOpenedTourName);
                startActivity(addIntent);
            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TourGuideActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //onCreateOptions menu inflates menu xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tour_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.app_bar_search){
            //TODO : perfoming a search on the diplayed guide list
            return true;
        }else if(item.getItemId()==R.id.app_bar_exit){
            Intent intent=new Intent(TourGuideActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //geolocate updates the camera and and marker on map to the location seached by the user
    private void geolocate() {
        Log.d(TAG,"geoLocate : geolocating");
        String searchString=mSearchText.getText().toString();
        Toast.makeText(getApplicationContext(),searchString,Toast.LENGTH_SHORT).show();
        Geocoder geocoder=new Geocoder(this);
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
    //getDevicel gets the location of the device and indicates that on the map given there was permision
    private void getDeviceLocation(){
        Log.d(TAG,"gettingDeviceLocation : getting the current devices location");
        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
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
                            //initMap();
                        }else{
                            Log.d(TAG,"onComplete : currentLocation is null");
                            Toast.makeText(TourGuideActivity.this,"unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.d(TAG,"gettingDeviceLocation : securityException "+e.toString());
        }
    }
    //geoLocate2 is pretty much the same as geolocate but it makes searches from strings after clicks Tour guides to see details
    private void geolocate2(String searchText) {
        Log.d(TAG,"geoLocate : geolocating");
        String searchString=searchText;

        Geocoder geocoder=new Geocoder(this);
        List<Address> list=new ArrayList<>();
        try{
            list=geocoder.getFromLocationName(searchString,1);
        }catch (Exception e){
            Log.d(TAG,"geoLocate : IOException "+e.getMessage());
        }
        if(list.size()>0){
            Address address=list.get(0);
            Log.d(TAG,"geoLocate : found a location "+address);
            LatLng place=new LatLng(address.getLatitude(),address.getLongitude());
            MarkerOptions options=new MarkerOptions()
                    .position(place)
                    .title("fuck");
            mMap.addMarker(options);
            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),DEFAULT_ZOOM,address.getAddressLine(0));
        }

    }

    private void getLocationPermision(){
        String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COURSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mLocationPermisionGranted=true;
                initMap();
            }else{
                //ask for those persmisiions
                ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
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
                    initMap();
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private class TourGuideInnerAdapter extends RecyclerView.Adapter<TourGuideInnerAdapter.TourViewHolder>{
        private ArrayList<tourGuide> mTourGuideList;
        public TourGuideInnerAdapter(ArrayList<tourGuide> tourGuideList) {
            mTourGuideList = tourGuideList;
        }

        @NonNull
        @Override
        public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mView= LayoutInflater.from(TourGuideActivity.this).inflate(R.layout.tour_guide_list_item,parent,false);
            return new TourGuideInnerAdapter.TourViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
            final tourGuide currentTourGuide=mTourGuideList.get(position);
            holder.tourName.setText(currentTourGuide.getTourName());
            holder.tourAddress.setText(currentTourGuide.getTourAddress());
            holder.tourcategory.setText(currentTourGuide.getTourCategory());
            holder.tourFoto.setImageResource(currentTourGuide.getTourImage());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentOpenedTourName=currentTourGuide.getTourName();
                    geolocate2(currentTourGuide.getTourName());
                    mAdress.setText(currentTourGuide.getTourAddress());
                    mPhone.setText(currentTourGuide.getTourPhone());
                    mDetail.setText(currentTourGuide.getTourDetails());
                    mTourImage.setImageResource(currentTourGuide.getTourImage());
                    RecyListView.setVisibility(View.GONE);
                    MapView.setVisibility(View.VISIBLE);
                }
            });

            //TODO implementing onclick listeners for the individual items shown on the list_item inc not whole itemView
        }

        @Override
        public int getItemCount() {
            return mTourGuideList.size();
        }

        public class TourViewHolder extends RecyclerView.ViewHolder {
            private TextView tourName,tourcategory,tourAddress;
            private ImageView tourFoto,mSideMenu,mFavMenu;

            public TourViewHolder(@NonNull View itemView) {
                super(itemView);
                tourName=(TextView)itemView.findViewById(R.id.tg_name_id);
                tourcategory=(TextView) itemView.findViewById(R.id.tg_category_id);
                tourFoto=(ImageView)itemView.findViewById(R.id.tg_foto_id);
                tourAddress=(TextView)itemView.findViewById(R.id.tg_address_id);

                mSideMenu=(ImageView)itemView.findViewById(R.id.side_menu_id);
                mFavMenu=(ImageView)itemView.findViewById(R.id.fav_menu_id);
            }
        }
    }

}
