package com.tricon.labs.nearhere.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tricon.labs.nearhere.fragments.PlacePhotoPageFragment;
import com.tricon.labs.nearhere.models.PlacePhoto;

import java.util.List;

/**
 * Created by gautam on 7/29/2015.
 */
public class PlacePhotoPagerAdapter extends FragmentStatePagerAdapter {

    private List<PlacePhoto> placePhotos;

    public PlacePhotoPagerAdapter(FragmentManager fm, List<PlacePhoto> placePhotos) {
        super(fm);
        this.placePhotos = placePhotos;
    }

    @Override
    public Fragment getItem(int position) {
        return PlacePhotoPageFragment.newInstance(placePhotos.get(position));
    }

    @Override
    public int getCount() {
        return placePhotos.size();
    }
}
