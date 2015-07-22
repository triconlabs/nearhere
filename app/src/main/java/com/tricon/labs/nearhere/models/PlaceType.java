package com.tricon.labs.nearhere.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gautam on 7/20/2015.
 */
public class PlaceType implements Parcelable{

    public String name;

    public String type;

    public PlaceType(Parcel in) {
        name = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
    }

    public static final Creator<PlaceType> CREATOR = new Creator<PlaceType>() {
        @Override
        public PlaceType createFromParcel(Parcel in) {
            return new PlaceType(in);
        }

        @Override
        public PlaceType[] newArray(int size) {
            return new PlaceType[size];
        }
    };
}
