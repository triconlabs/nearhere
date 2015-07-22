package com.tricon.labs.nearhere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gautam on 7/17/2015.
 */
public class Place implements Parcelable {

    @SerializedName("place_id")
    public String placeId;

    public String name;

    @SerializedName("formatted_address")
    public String address;

    @SerializedName("international_phone_number")
    public String phoneNo;

    public String icon;

    @SerializedName("url")
    public String googlePlusUrl;

    public String vicinity;

    public String website;

    @SerializedName("price_level")
    public int priceLevel;

    public float rating;

    @SerializedName("permanently_closed")
    public boolean permanentlyClosed;

    public Geometry geometry;

    @SerializedName("opening_hours")
    public PlaceOpeningInfo openingInfo;

    public List<PlacePhoto> photos;

    public List<PlaceReview> reviews;

    public Place(Parcel in) {
        placeId = in.readString();
        name = in.readString();
        address = in.readString();
        phoneNo = in.readString();
        icon = in.readString();
        googlePlusUrl = in.readString();
        vicinity = in.readString();
        website = in.readString();
        priceLevel = in.readInt();
        rating = in.readFloat();
        permanentlyClosed = in.readByte() != 0;
        geometry = in.readParcelable(Geometry.class.getClassLoader());
        openingInfo = in.readParcelable(PlaceOpeningInfo.class.getClassLoader());
        photos = in.readArrayList(PlacePhoto.class.getClassLoader());
        reviews = in.readArrayList(PlaceReview.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeId);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phoneNo);
        dest.writeString(icon);
        dest.writeString(googlePlusUrl);
        dest.writeString(vicinity);
        dest.writeString(website);
        dest.writeInt(priceLevel);
        dest.writeFloat(rating);
        dest.writeByte((byte) (permanentlyClosed ? 1 : 0));
        dest.writeParcelable(geometry, flags);
        dest.writeParcelable(openingInfo, flags);
        dest.writeList(photos);
        dest.writeList(reviews);
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {

        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
