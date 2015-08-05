package com.tricon.labs.nearhere.activities;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.init.NearHereApplication;

public class SplashActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, ResultCallback<LocationSettingsResult> {

    private ProgressBar pbSplash;

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final int CHECK_SETTINGS_REQUEST = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pbSplash = (ProgressBar) findViewById(R.id.pb_splash);

        pbSplash.setVisibility(View.VISIBLE);

        if (checkPlayServices()) {

            //Step 1 : build GoogleApiClient object
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();

            //Step 2 : build LocationRequest object
            locationRequest = new LocationRequest();
            locationRequest.setInterval(8000);
            locationRequest.setFastestInterval(4000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            //Step 3 : build LocationSettingsRequest object
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);
            LocationSettingsRequest locationSettingsRequest = builder.build();

            //Step 4 : check Location Settings
            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, locationSettingsRequest);
            result.setResultCallback(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient != null && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case CHECK_SETTINGS_REQUEST:
                switch (resultCode) {
                    case RESULT_OK:
                        //User agreed to make required location settings changes.
                        startLocationUpdates();
                        break;
                    case RESULT_CANCELED:
                        //User choose not to make required location settings changes.
                        finish();
                        break;
                }
                break;
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(this, "This device is not supported.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    private void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("", "");
    }

    @Override
    public void onLocationChanged(Location location) {
        pbSplash.setVisibility(View.GONE);
        NearHereApplication.currentLocation = location;
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                //All location settings are satisfied.
                startLocationUpdates();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                //Location settings are not satisfied. Show the user a dialog to upgrade location settings
                try {
                    // Show the dialog by calling startResolutionForResult(), and check the result
                    // in onActivityResult().
                    status.startResolutionForResult(SplashActivity.this, CHECK_SETTINGS_REQUEST);
                } catch (IntentSender.SendIntentException e) {
                    //PendingIntent unable to execute request.
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                //Location settings are inadequate, and cannot be fixed here. Dialog not created.
                break;
        }
    }
}
