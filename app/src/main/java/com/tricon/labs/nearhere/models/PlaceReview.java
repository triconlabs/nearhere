package com.tricon.labs.nearhere.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gautam on 7/20/2015.
 */
public class PlaceReview {

    @SerializedName("author_name")
    public String authorName;

    public int rating;

    @SerializedName("text")
    public String review;

    public long time;
}
