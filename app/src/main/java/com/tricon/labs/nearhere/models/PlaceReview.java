package com.tricon.labs.nearhere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gautam on 7/20/2015.
 */
public class PlaceReview implements Parcelable {

    @SerializedName("author_name")
    public String authorName;

    public float rating;

    @SerializedName("text")
    public String review;

    public long time;

    public PlaceReview(Parcel in) {
        authorName = in.readString();
        rating = in.readFloat();
        review = in.readString();
        time = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(authorName);
        dest.writeFloat(rating);
        dest.writeString(review);
        dest.writeLong(time);
    }

    public static final Creator<PlaceReview> CREATOR = new Creator<PlaceReview>() {
        @Override
        public PlaceReview createFromParcel(Parcel in) {
            return new PlaceReview(in);
        }

        @Override
        public PlaceReview[] newArray(int size) {
            return new PlaceReview[size];
        }
    };
}
