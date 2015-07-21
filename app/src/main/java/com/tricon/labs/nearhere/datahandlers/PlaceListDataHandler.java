package com.tricon.labs.nearhere.datahandlers;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.tricon.labs.nearhere.init.NearHereApplication;
import com.tricon.labs.nearhere.models.Place;
import com.tricon.labs.nearhere.utils.NearHereConstants;
import com.tricon.labs.nearhere.volley.GsonRequest;

import java.util.List;

/**
 * Created by gautam on 7/20/2015.
 */
abstract public class PlaceListDataHandler extends VolleyBaseDataHandler<List<Place>> {

    private static final String NEARBY_SEARCH_API = "nearbysearch";

    public void fetchNearbyPlacesByType(String type) {
        StringBuilder url = new StringBuilder(NearHereConstants.PLACES_API_BASE_URL + NEARBY_SEARCH_API + NearHereConstants.PLACES_API_EXTENSION);
        url
                .append("&location=")
                .append(NearHereApplication.currentLocation.getLatitude())
                .append(",")
                .append(NearHereApplication.currentLocation.getLongitude())
                .append("&rankby=distance&types=")
                .append(type);
        GsonRequest<List<Place>> gsonRequest = new GsonRequest<>(Request.Method.GET, url.toString(), listener, errorListener, new TypeToken<List<Place>>(){}.getType());
        NearHereApplication.requestQueue.add(gsonRequest);
    }

}
