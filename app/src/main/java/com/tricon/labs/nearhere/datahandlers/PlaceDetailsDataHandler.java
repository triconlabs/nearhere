package com.tricon.labs.nearhere.datahandlers;

import com.android.volley.Request;
import com.tricon.labs.nearhere.init.NearHereApplication;
import com.tricon.labs.nearhere.models.Place;
import com.tricon.labs.nearhere.utils.NearHereConstants;
import com.tricon.labs.nearhere.volley.GsonRequest;

/**
 * Created by gautam on 7/20/2015.
 */
abstract public class PlaceDetailsDataHandler extends VolleyBaseDataHandler<Place> {

    private static final String PLACE_DETAILS_API = "details";

    public void fetchPlaceDetails(String placeId) {
        StringBuilder url = new StringBuilder(NearHereConstants.PLACES_API_BASE_URL + PLACE_DETAILS_API + NearHereConstants.PLACES_API_EXTENSION);
        url
                .append("&placeid=")
                .append(placeId);
        GsonRequest<Place> gsonRequest = new GsonRequest<>(Request.Method.GET, url.toString(), listener, errorListener, Place.class);
        NearHereApplication.requestQueue.add(gsonRequest);
    }

}
