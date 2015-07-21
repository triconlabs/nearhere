package com.tricon.labs.nearhere.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gautam on 7/17/2015.
 */
public class Place {

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

}
