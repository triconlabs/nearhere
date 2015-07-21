package com.tricon.labs.nearhere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gautam on 7/20/2015.
 */
public class PlaceOpeningInfo implements Parcelable {

    @SerializedName("open_now")
    public boolean openNow;

    public PlaceOpeningInfo(Parcel in) {
        openNow = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte)(openNow ? 1 : 0));
    }

    public static final Creator<PlaceOpeningInfo> CREATOR = new Creator<PlaceOpeningInfo>() {
        @Override
        public PlaceOpeningInfo createFromParcel(Parcel in) {
            return new PlaceOpeningInfo(in);
        }

        @Override
        public PlaceOpeningInfo[] newArray(int size) {
            return new PlaceOpeningInfo[size];
        }
    };
}
