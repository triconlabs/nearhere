package com.tricon.labs.nearhere.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.adapters.PlaceListAdapter;
import com.tricon.labs.nearhere.datahandlers.PlaceListDataHandler;
import com.tricon.labs.nearhere.models.Place;
import com.tricon.labs.nearhere.models.PlaceListResponse;
import com.tricon.labs.nearhere.models.PlaceType;

public class PlacesActivity extends AppCompatActivity {

    private RecyclerView rvPlaces;
    private ProgressBar pbPlaces;

    private PlaceListAdapter placeListAdapter;

    public static final String KEY_PLACE_TYPE = "com.tricon.labs.nearhere.activities.PlaceListActivity.PLACE_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        rvPlaces = (RecyclerView) findViewById(R.id.rv_places);
        pbPlaces = (ProgressBar) findViewById(R.id.pb_places);

        PlaceType placeType = getIntent().getParcelableExtra(KEY_PLACE_TYPE);

        getSupportActionBar().setTitle(placeType.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPlaces.setLayoutManager(linearLayoutManager);
        pbPlaces.setVisibility(View.VISIBLE);

        PlaceListDataHandler placeListDataHandler = new PlaceListDataHandler() {
            @Override
            public void resultReceived(PlaceListResponse placeListResponse) {
                placeListAdapter = new PlaceListAdapter(placeListResponse.places);
                rvPlaces.setAdapter(placeListAdapter);
                onLoadComplete(null);
            }

            @Override
            public void errorReceived(String msg) {
                onLoadComplete(msg);
            }
        };
        placeListDataHandler.fetchNearbyPlacesByType(placeType.type);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_places, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    private void onLoadComplete(String msg) {
        pbPlaces.setVisibility(View.GONE);
        if(null != msg) {
            Toast.makeText(PlacesActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void startPlaceDetailsActivity(int position) {
        Place place = placeListAdapter.getItem(position);
        Intent intent = new Intent(this, PlacesActivity.class);
        intent.putExtra(PlaceDetailsActivity.KEY_PLACE, place);
        startActivity(intent);
    }
}
