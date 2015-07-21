package com.tricon.labs.nearhere.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.models.Place;

public class PlaceDetailsActivity extends AppCompatActivity {

    public static final String KEY_PLACE = "com.tricon.labs.nearhere.activities.PlaceDetailsActivity.PLACE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView ivPlaceCoverPhoto = (ImageView) findViewById(R.id.iv_place_cover_photo);

        Place place = getIntent().getParcelableExtra(KEY_PLACE);
        ImageLoader imageLoader = ImageLoader.getInstance();

        collapsingToolbarLayout.setTitle(place.name);
        if(null != place.photos && place.photos.size() > 0) {
            imageLoader.displayImage(place.photos.get(0).getPhotoReference(), ivPlaceCoverPhoto);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_details, menu);
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
