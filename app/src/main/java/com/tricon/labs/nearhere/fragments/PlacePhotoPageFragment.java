package com.tricon.labs.nearhere.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tricon.labs.nearhere.R;
import com.tricon.labs.nearhere.models.PlacePhoto;

/**
 * Created by gautam on 7/29/2015.
 */
public class PlacePhotoPageFragment extends Fragment {

    private static final String KEY_PLACE_PHOTO = "com.tricon.labs.nearhere.fragments.PlacePhotoPageFragment.PLACE_PHOTO";

    public static PlacePhotoPageFragment newInstance(PlacePhoto placePhoto) {
        Bundle args = new Bundle();
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
        final ProgressBar pbPlacePhotoPageFragment = (ProgressBar) view.findViewById(R.id.pb_place_photo_page_fragment);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(placePhoto.getPhotoReference(), ivPlacePhoto, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                pbPlacePhotoPageFragment.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                pbPlacePhotoPageFragment.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                pbPlacePhotoPageFragment.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                pbPlacePhotoPageFragment.setVisibility(View.GONE);
            }
        });
        return view;
    }
}
