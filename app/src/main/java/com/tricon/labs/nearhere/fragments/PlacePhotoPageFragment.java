package com.tricon.labs.nearhere.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.models.PlacePhoto;

/**
 * Created by gautam on 7/29/2015.
 */
public class PlacePhotoPageFragment extends Fragment{

    private static final String KEY_PLACE_PHOTO = "com.tricon.labs.nearhere.fragments.PlacePhotoPageFragment.PLACE_PHOTO";

    public static PlacePhotoPageFragment newInstance(PlacePhoto placePhoto) {
        Bundle args =  new Bundle();
        args.putParcelable(KEY_PLACE_PHOTO, placePhoto);
        PlacePhotoPageFragment placePhotoPageFragment = new PlacePhotoPageFragment();
        placePhotoPageFragment.setArguments(args);
        return placePhotoPageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_photo_page, container, false);
        ImageView ivPlacePhoto = (ImageView) view.findViewById(R.id.iv_place_photo);
        PlacePhoto placePhoto = getArguments().getParcelable(KEY_PLACE_PHOTO);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(placePhoto.getPhotoReference(), ivPlacePhoto);
        return view;
    }
}
