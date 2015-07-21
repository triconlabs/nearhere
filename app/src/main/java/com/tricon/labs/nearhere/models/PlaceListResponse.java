package com.tricon.labs.nearhere.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gautam on 7/21/2015.
 */
public class PlaceListResponse {

    @SerializedName("results")
    public List<Place> places;

}
