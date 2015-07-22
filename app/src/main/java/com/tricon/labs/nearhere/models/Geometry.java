package com.tricon.labs.nearhere.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gautam on 7/20/2015.
 */
public class Geometry implements Parcelable {

    public Location location;

    public Geometry(Parcel in) {
        location = in.readParcelable(Location.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(location, flags);
    }

    public static final Parcelable.Creator<Geometry> CREATOR = new Parcelable.Creator<Geometry>() {
        @Override
        public Geometry createFromParcel(Parcel source) {
            return new Geometry(source);
        }

        @Override
        public Geometry[] newArray(int size) {
            return new Geometry[size];
        }
    };
}
