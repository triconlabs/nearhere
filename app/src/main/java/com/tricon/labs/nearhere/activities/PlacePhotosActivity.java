package com.tricon.labs.nearhere.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black_transparency)));

        ViewPager vpPlacePhotos = (ViewPager) findViewById(R.id.vp_place_photos);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        final List<PlacePhoto> placePhotos = getIntent().getParcelableArrayListExtra(KEY_PLACE_PHOTOS);
        PlacePhotoPagerAdapter placePhotoPagerAdapter = new PlacePhotoPagerAdapter(getSupportFragmentManager(), placePhotos);
        vpPlacePhotos.setAdapter(placePhotoPagerAdapter);

        if (actionBar != null) {
            actionBar.setTitle("1/"+placePhotos.size());
        }

        vpPlacePhotos.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (actionBar != null) {
                    actionBar.setTitle((position+1)+"/"+placePhotos.size());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
