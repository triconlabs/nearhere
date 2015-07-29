package com.tricon.labs.nearhere.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.adapters.PlacePhotoPagerAdapter;
import com.tricon.labs.nearhere.models.PlacePhoto;

import java.util.List;

public class PlacePhotosActivity extends AppCompatActivity {

    public static final String KEY_PLACE_PHOTOS = "com.tricon.labs.nearhere.activities.PlacePhotosActivity.PLACE_PHOTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_photos);

        ViewPager vpPlacePhotos = (ViewPager) findViewById(R.id.vp_place_photos);

        List<PlacePhoto> placePhotos = getIntent().getParcelableArrayListExtra(KEY_PLACE_PHOTOS);
        PlacePhotoPagerAdapter placePhotoPagerAdapter = new PlacePhotoPagerAdapter(getSupportFragmentManager(), placePhotos);
        vpPlacePhotos.setAdapter(placePhotoPagerAdapter);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_photos, menu);
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
}
