package com.tricon.labs.nearhere.datahandlers;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/**
 * Created by gautam on 7/20/2015.
 */
public abstract class VolleyBaseDataHandler<T> {

    public Response.Listener<T> listener;
    public Response.ErrorListener errorListener;

    public VolleyBaseDataHandler() {
        this.listener = new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                resultReceived(response);
            }
        };
        this.errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleError(error);
            }
        };
    }

    private void handleError(VolleyError error) {
        String msg;
        if(error instanceof NoConnectionError || error instanceof TimeoutError) {
            msg = "Please check your internet connection and try again!";
        } else {
            msg = "Oops! Something went wrong. Please try again after sometime.";
        }
        errorReceived(msg);
    }

    abstract public void resultReceived(T obj);

    abstract public void errorReceived(String msg);

}
