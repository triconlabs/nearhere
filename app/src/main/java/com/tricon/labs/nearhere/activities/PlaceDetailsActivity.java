package com.tricon.labs.nearhere.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.datahandlers.PlaceDetailsDataHandler;
import com.tricon.labs.nearhere.models.Place;
import com.tricon.labs.nearhere.models.PlaceDetailsResponse;
import com.tricon.labs.nearhere.models.PlacePhoto;
import com.tricon.labs.nearhere.models.PlaceReview;
import com.tricon.labs.nearhere.utils.NearHereConstants;
import com.tricon.labs.nearhere.utils.NearHereUtils;

import java.util.ArrayList;

public class PlaceDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Place place;
    private boolean isDetailsAvailable = false;

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
        TextView tvRating = (TextView) findViewById(R.id.tv_rating);
        TextView tvOpen = (TextView) findViewById(R.id.tv_open);

        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        mapFragment.getView().setClickable(false);

        place = getIntent().getParcelableExtra(KEY_PLACE);
        ImageLoader imageLoader = ImageLoader.getInstance();

        collapsingToolbarLayout.setTitle(place.name);
        tvRating.setText(place.rating + "");
        if(null != place.photos && place.photos.size() > 0) {
            imageLoader.displayImage(place.photos.get(0).getPhotoReference(), ivPlaceCoverPhoto);
        }
        if(null == place.openingInfo || place.openingInfo.openNow) {
            tvOpen.setText("Yes");
        } else {
            tvOpen.setText("No");
        }

        PlaceDetailsDataHandler placeDetailsDataHandler = new PlaceDetailsDataHandler() {
            @Override
            public void resultReceived(PlaceDetailsResponse placeDetailsResponse) {
                place = placeDetailsResponse.place;
                updateUI();
            }

            @Override
            public void errorReceived(String msg) {
                Toast.makeText(PlaceDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        placeDetailsDataHandler.fetchPlaceDetails(place.placeId);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //map.getUiSettings().setAllGesturesEnabled(false);
        if(isDetailsAvailable) {
            updateMap();
        }
    }

    private void updateUI() {
        isDetailsAvailable = true;
        if(null != map) {
            updateMap();
        }
        if(null != place.phoneNo) {
            TextView tvPhoneNo = (TextView) findViewById(R.id.tv_phone_no);
            LinearLayout rlPhoneNo = (LinearLayout) findViewById(R.id.rl_phone_no);
            tvPhoneNo.setText(place.phoneNo);
            rlPhoneNo.setVisibility(View.VISIBLE);
        }
        if(null != place.website) {
            TextView tvWebsite = (TextView) findViewById(R.id.tv_website);
            LinearLayout rlWebsite = (LinearLayout) findViewById(R.id.rl_website);
            tvWebsite.setText(place.website);
            rlWebsite.setVisibility(View.VISIBLE);
        }
        if(null != place.photos && place.photos.size() > 1) {
            ImageView ivPlacePhoto = (ImageView) findViewById(R.id.iv_place_photo);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(place.photos.get(1).getPhotoReference(), ivPlacePhoto);
            ivPlacePhoto.setVisibility(View.VISIBLE);
            ivPlacePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlaceDetailsActivity.this, PlacePhotosActivity.class);
                    intent.putParcelableArrayListExtra(PlacePhotosActivity.KEY_PLACE_PHOTOS, (ArrayList<PlacePhoto>) place.photos);
                    startActivity(intent);
                }
            });
        }
        if(null != place.reviews && place.reviews.size() > 0) {
            findViewById(R.id.tv_label_reviews).setVisibility(View.VISIBLE);
            LinearLayout llReviews = (LinearLayout) findViewById(R.id.ll_reviews);
            for(PlaceReview placeReview : place.reviews) {
                View view = LayoutInflater.from(this).inflate(R.layout.view_review_list_item, null);
                AppCompatRatingBar rbRating = (AppCompatRatingBar) view.findViewById(R.id.rb_rating);
                TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
                TextView tvAuthorName = (TextView) view.findViewById(R.id.tv_author_name);
                TextView tvReview = (TextView) view.findViewById(R.id.tv_review);

                rbRating.setRating(placeReview.rating);
                tvAuthorName.setText(placeReview.authorName);
                tvReview.setText(placeReview.review);
                if(0.0 != placeReview.time) {
                  tvDate.setText(NearHereUtils.getDate(placeReview.time * 1000, NearHereConstants.DATE_FORMAT));
                }
                llReviews.addView(view);
            }
        }
    }

    private void updateMap() {
        LatLng latLng = new LatLng(place.geometry.location.lat, place.geometry.location.lng);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        Marker marker = map.addMarker(new MarkerOptions().position(latLng));
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.custom_title, null);
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                tvTitle.setText(place.address);
                return view;
            }
        });
        marker.showInfoWindow();
    }
}
