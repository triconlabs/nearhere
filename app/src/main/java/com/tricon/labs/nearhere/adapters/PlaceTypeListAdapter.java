package com.tricon.labs.nearhere.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.activities.HomeActivity;
import com.tricon.labs.nearhere.models.PlaceType;

import java.util.List;

/**
 * Created by gautam on 7/20/2015.
 */
public class PlaceTypeListAdapter extends RecyclerView.Adapter<PlaceTypeListAdapter.PlaceTypeViewHolder> {

    private List<PlaceType> placeTypes;
    private String packageName;
    private Resources resources;

    public PlaceTypeListAdapter(Context context, List<PlaceType> placeTypes) {
        this.placeTypes = placeTypes;
        this.packageName = context.getPackageName();
        this.resources = context.getResources();
    }

    @Override
    public PlaceTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceTypeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_place_type_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PlaceTypeViewHolder holder, int position) {
        PlaceType placeType = getItem(position);
        holder.tvPlaceType.setText(placeType.name);
        holder.ivPlaceType.setImageResource(resources.getIdentifier(placeType.resId, "mipmap", packageName));
    }

    @Override
    public int getItemCount() {
        return placeTypes.size();
    }

    public PlaceType getItem(int position) {
        return placeTypes.get(position);
    }

    public static class PlaceTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivPlaceType;
        public TextView tvPlaceType;

        public PlaceTypeViewHolder(View itemView) {
            super(itemView);
            ivPlaceType = (ImageView) itemView.findViewById(R.id.iv_place_type);
            tvPlaceType = (TextView) itemView.findViewById(R.id.tv_place_type);
            CardView cvCategoryCard = (CardView) itemView.findViewById(R.id.cv_place_type);
            cvCategoryCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((HomeActivity)v.getContext()).startPlaceListActivity(getAdapterPosition());
        }
    }

}
