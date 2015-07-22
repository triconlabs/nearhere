package com.tricon.labs.nearhere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.tricon.labs.nearhere.utils.NearHereConstants;

/**
 * Created by gautam on 7/20/2015.
 */
public class PlacePhoto implements Parcelable{

    @SerializedName("photo_reference")
    private String photoReference;

    public static final String PLACE_PHOTO_URL = NearHereConstants.PLACES_API_BASE_URL + "photo?maxheight=400&key=" + NearHereConstants.BROWSER_API_KEY + "&photoreference=";

    public String getPhotoReference() {
        return (PLACE_PHOTO_URL + photoReference);
    }

    public PlacePhoto(Parcel in) {
        photoReference = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoReference);
    }

    public static final Creator<PlacePhoto> CREATOR = new Creator<PlacePhoto>() {
        @Override
        public PlacePhoto createFromParcel(Parcel in) {
            return new PlacePhoto(in);
        }

        @Override
        public PlacePhoto[] newArray(int size) {
            return new PlacePhoto[size];
        }
    };

}
