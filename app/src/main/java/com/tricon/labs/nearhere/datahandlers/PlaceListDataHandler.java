package com.tricon.labs.nearhere.datahandlers;

import com.android.volley.Request;
import com.tricon.labs.nearhere.init.NearHereApplication;
import com.tricon.labs.nearhere.models.PlaceListResponse;
import com.tricon.labs.nearhere.utils.NearHereConstants;
import com.tricon.labs.nearhere.volley.GsonRequest;

/**
 * Created by gautam on 7/20/2015.
 */
abstract public class PlaceListDataHandler extends VolleyBaseDataHandler<PlaceListResponse> {

    private static final String NEARBY_SEARCH_API = "nearbysearch";

    public void fetchNearbyPlacesByType(String type, String pageToken) {
        StringBuilder url = new StringBuilder(NearHereConstants.PLACES_API_BASE_URL + NEARBY_SEARCH_API + NearHereConstants.PLACES_API_EXTENSION);
        if(null == pageToken) {
            url
                    .append("&location=")
                    .append(NearHereApplication.currentLocation.getLatitude())
                    .append(",")
                    .append(NearHereApplication.currentLocation.getLongitude())
                    .append("&radius=1500&rankby=prominence&types=")
                    .append(type);
        } else {
            url
                    .append("&pagetoken=")
                    .append(pageToken);
        }

        GsonRequest<PlaceListResponse> gsonRequest = new GsonRequest<>(Request.Method.GET, url.toString(), listener, errorListener, PlaceListResponse.class);
        NearHereApplication.requestQueue.add(gsonRequest);
    }

}
