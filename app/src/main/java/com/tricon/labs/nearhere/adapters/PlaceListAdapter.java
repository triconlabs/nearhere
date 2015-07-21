package com.tricon.labs.nearhere.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.activities.PlacesActivity;
import com.tricon.labs.nearhere.init.NearHereApplication;
import com.tricon.labs.nearhere.models.Place;
import com.tricon.labs.nearhere.utils.NearHereConstants;

import java.util.List;

/**
 * Created by gautam on 6/18/2015.
 */
public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder>{

    private List<Place> places;
    private static final String PLACE_PHOTO_URL = NearHereConstants.PLACES_API_BASE_URL + "photo?maxheight=150&key=" + NearHereConstants.BROWSER_API_KEY + "&photoreference=";

    public PlaceListAdapter(List<Place> places) {
        this.places = places;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_place_list_item, parent, false));
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        if(null != place.photos && place.photos.size() > 0) {
            holder.ivPlaceCoverPhoto.setImageUrl(PLACE_PHOTO_URL + place.photos.get(0).photoReference, NearHereApplication.imageLoader);
        } else {
            holder.ivPlaceCoverPhoto.setImageResource(android.R.color.white);
        }
        holder.tvPlaceName.setText(place.name);
        holder.tvRating.setText(place.rating + "");
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public Place getItem(int position) {
        return places.get(position);
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public NetworkImageView ivPlaceCoverPhoto;
        public TextView tvPlaceName;
        public TextView tvRating;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ivPlaceCoverPhoto = (NetworkImageView) itemView.findViewById(R.id.iv_place_cover_photo);
            tvPlaceName = (TextView) itemView.findViewById(R.id.tv_place_name);
            tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
            CardView cvCategoryCard = (CardView) itemView.findViewById(R.id.cv_place);
            cvCategoryCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((PlacesActivity) v.getContext()).startPlaceDetailsActivity(getAdapterPosition());
        }
    }
}
