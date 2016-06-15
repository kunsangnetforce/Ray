package com.netforce.ray.search;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.Slider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.netforce.ray.R;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class AdvanceSearch extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    TextView textViewRange;
    Slider slider;
    TextView textViewAll, textViewNew, textViewFashion, textViewHome, textViewElectronics, textViewMovie, textViewBaby, textViewSports, textViewCars, textViewServices, textViewOther;
    private Toolbar toolbar;
    private Context context;
    private Location location;
    boolean all = true, newInArea = false, fashion = false, home = false, electronics = false, movie = false, baby = false, sports = false, cars = false, services = false, other = false;
    public static LatLng searchLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);
        setupToolBar();
        initView();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        slider.setOnValueChangedListener(new Slider.OnValueChangedListener() {
            @Override
            public void onValueChanged(int i) {
                switch (i) {
                    case 0:
                        setupMap(i, 13.0f);
                        break;
                    case 1:
                        setupMap(i, 12.8810f);
                        break;
                    case 2:
                        setupMap(i, 11.8410f);
                        break;
                    case 3:
                        setupMap(i, 11.117f);
                        break;
                    case 4:
                        setupMap(i, 10.5410f);
                        break;
                    case 5:
                        setupMap(i, 10.4410f);
                        break;
                    case 6:
                        setupMap(i, 10.26308410f);
                        break;
                    case 7:
                        setupMap(i, 9.9297f);
                        break;
                    case 8:
                        setupMap(10, 9.150988f);
                        break;
                    case 9:
                        setupMap(20, 8.3101f);
                        break;
                    case 10:
                        setupMap(30, 7.928f);
                        break;
                    case 11:
                        setupMap(60, 6.8633f);
                        break;
                    case 12:
                        setupMap(100, 6.1690f);
                        break;
                    case 13:
                        setupMap(200, 5.1910f);
                        break;
                    case 14:
                        setupMap(300, 4.47150f);
                        break;
                    case 15:
                        setupMap(400, 4.08555f);
                        break;
                    case 16:
                        setupMap(500, 3.566f);
                        break;
                    case 17:
                        setupMap(1000, 3.0f);
                        break;


                }
            }
        });
    }

    private void initView() {
        context = this;
        slider = (Slider) findViewById(R.id.slider);
        textViewRange = (TextView) findViewById(R.id.textviewrange);
        textViewAll = (TextView) findViewById(R.id.textviewAll);
        textViewNew = (TextView) findViewById(R.id.textviewNew);
        textViewFashion = (TextView) findViewById(R.id.textviewFashion);
        textViewHome = (TextView) findViewById(R.id.textviewHome);
        textViewElectronics = (TextView) findViewById(R.id.textviewElectronics);
        textViewMovie = (TextView) findViewById(R.id.textviewMovie);
        textViewBaby = (TextView) findViewById(R.id.textviewBaby);
        textViewSports = (TextView) findViewById(R.id.textviewSports);
        textViewCars = (TextView) findViewById(R.id.textviewCars);
        textViewServices = (TextView) findViewById(R.id.textviewServices);
        textViewOther = (TextView) findViewById(R.id.textviewOther);
        textViewAll.setOnClickListener(this);
        textViewNew.setOnClickListener(this);
        textViewFashion.setOnClickListener(this);
        textViewHome.setOnClickListener(this);
        textViewElectronics.setOnClickListener(this);
        textViewMovie.setOnClickListener(this);
        textViewBaby.setOnClickListener(this);
        textViewSports.setOnClickListener(this);
        textViewCars.setOnClickListener(this);
        textViewServices.setOnClickListener(this);
        textViewOther.setOnClickListener(this);
    }

    private void setupMap(int i, float zoomlevel) {
        if (i == 0) {
            textViewRange.setText("Everywhere");
            if (location != null) {
                try {
                    mMap.clear();
                } catch (Exception ex) {

                }
                mMap.addMarker(new MarkerOptions().position(searchLatLng).title("My Current Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchLatLng, zoomlevel));
            }
        } else {
            textViewRange.setText(i + "");
            if (location != null) {
                try {
                    mMap.clear();
                } catch (Exception ex) {

                }
                mMap.addMarker(new MarkerOptions().position(searchLatLng).title("My Current Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchLatLng, zoomlevel));
                mMap.addCircle(new CircleOptions()
                        .center(searchLatLng)
                        .radius(i * 1000)
                        .strokeWidth(0f)
                        .fillColor(ContextCompat.getColor(context, R.color.greentranparent)));


            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.i("zoom_level", "" + cameraPosition.zoom);
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(getApplicationContext(), ChooseLocation.class);
                startActivity(intent);
            }
        });
        getLocation();
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Advance Search";
        getSupportActionBar().setTitle(teams);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                return true;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    private void getLocation() {
        SmartLocation.with(context).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        AdvanceSearch.this.location = location;
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        searchLatLng = currentLatLng;
                        adjustMarker(searchLatLng);

                    }
                });
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onResume() {
        try {
            adjustMarker(searchLatLng);
        } catch (Exception ex) {

        }
        Log.i("Onresume", "executed");
        super.onResume();
    }

    private void adjustMarker(LatLng searchLatLng) {
        mMap.addMarker(new MarkerOptions().position(searchLatLng).title("Search Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchLatLng, 9.0f));

    }

    private void showMessage(String s) {
        Toast.makeText(AdvanceSearch.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textviewAll:
                changeState(textViewAll, true);
                setAllFalse();
                changeState(textViewNew, false);
                changeState(textViewFashion, false);
                changeState(textViewHome, false);
                changeState(textViewElectronics, false);
                changeState(textViewMovie, false);
                changeState(textViewBaby, false);
                changeState(textViewSports, false);
                changeState(textViewCars, false);
                changeState(textViewServices, false);
                changeState(textViewOther, false);
                break;
            case R.id.textviewNew:
                if (newInArea) {
                    changeState(textViewNew, false);
                } else {
                    changeState(textViewNew, true);
                }
                newInArea = !newInArea;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewFashion:
                if (fashion) {
                    changeState(textViewFashion, false);
                } else {
                    changeState(textViewFashion, true);
                }
                fashion = !fashion;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewHome:
                if (home) {
                    changeState(textViewHome, false);
                } else {
                    changeState(textViewHome, true);
                }
                home = !home;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewElectronics:
                if (electronics) {
                    changeState(textViewElectronics, false);
                } else {
                    changeState(textViewElectronics, true);
                }
                electronics = !electronics;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewMovie:
                if (movie) {
                    changeState(textViewMovie, false);
                } else {
                    changeState(textViewMovie, true);
                }
                movie = !movie;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewBaby:
                if (baby) {
                    changeState(textViewBaby, false);
                } else {
                    changeState(textViewBaby, true);
                }
                baby = !baby;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewSports:
                if (sports) {
                    changeState(textViewSports, false);
                } else {
                    changeState(textViewSports, true);
                }
                sports = !sports;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewCars:
                if (cars) {
                    changeState(textViewCars, false);
                } else {
                    changeState(textViewCars, true);
                }
                cars = !cars;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewServices:
                if (services) {
                    changeState(textViewServices, false);
                } else {
                    changeState(textViewServices, true);
                }
                services = !services;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
            case R.id.textviewOther:
                if (other) {
                    changeState(textViewOther, false);
                } else {
                    changeState(textViewOther, true);
                }
                other = !other;
                all = checkAllFalse();
                changeState(textViewAll, all);
                break;
        }
    }

    private void setAllFalse() {
        newInArea = false;
        fashion = false;
        home = false;
        electronics = false;
        movie = false;
        baby = false;
        sports = false;
        cars = false;
        services = false;
        other = false;
    }

    private boolean checkAllFalse() {
        if (!newInArea && !fashion && !home && !electronics && !movie && !baby && !sports && !cars && !services && !other) {
            return true;
        } else {
            return false;
        }
    }

    public void changeState(TextView textView, boolean state) {
        if (!state) {
            textView.setBackgroundResource(R.color.grey);
            textView.setTextColor(ContextCompat.getColor(context, R.color.primary));
        } else {
            textView.setBackgroundResource(R.color.colorPrimary);
            textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
    }
}
