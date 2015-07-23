package com.tricon.labs.nearhere.adapters;

import android.location.Location;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.activities.PlacesActivity;
import com.tricon.labs.nearhere.init.NearHereApplication;
import com.tricon.labs.nearhere.models.Place;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by gautam on 6/18/2015.
 */
public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder>{

    private List<Place> places;
    private ImageLoader imageLoader;

    private static final DecimalFormat mDecimalFormat = new DecimalFormat("#.##");

    public PlaceListAdapter(List<Place> places) {
        this.places = places;
        this.imageLoader = ImageLoader.getInstance();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_place_list_item, parent, false));
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        if(0.0d == place.distance) {
            float[] distance = new float[1];
            Location.distanceBetween(NearHereApplication.currentLocation.getLatitude(), NearHereApplication.currentLocation.getLongitude(), place.geometry.location.lat, place.geometry.location.lng, distance);
            place.distance = distance[0]/1000;
        }
        if(null != place.photos && place.photos.size() > 0) {
            imageLoader.displayImage(place.photos.get(0).getPhotoReference(), holder.ivPlaceCoverPhoto);
        } else {
            imageLoader.displayImage("", holder.ivPlaceCoverPhoto);
        }
        holder.tvPlaceName.setText(place.name);
        holder.tvRating.setText(place.rating + "");
        holder.tvDistance.setText(mDecimalFormat.format(place.distance)+"km");
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public Place getItem(int position) {
        return places.get(position);
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivPlaceCoverPhoto;
        public TextView tvPlaceName;
        public TextView tvRating;
        public TextView tvDistance;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ivPlaceCoverPhoto = (ImageView) itemView.findViewById(R.id.iv_place_cover_photo);
            tvPlaceName = (TextView) itemView.findViewById(R.id.tv_place_name);
            tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
            tvDistance = (TextView) itemView.findViewById(R.id.tv_distance);
            CardView cvCategoryCard = (CardView) itemView.findViewById(R.id.cv_place);
            cvCategoryCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((PlacesActivity) v.getContext()).startPlaceDetailsActivity(getAdapterPosition());
        }
    }
}
