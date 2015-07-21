package com.tricon.labs.nearhere.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.tricon.labs.nearhere.utils.GsonUtils;

import java.lang.reflect.Type;

/**
 * Created by gautam on 7/20/2015.
 */
public class GsonRequest<T> extends Request<T> {

    private Response.Listener<T> listener;
    private Type type;

    public GsonRequest(int methodType, String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Type type) {
        super(methodType, url, errorListener);
        this.listener = listener;
        this.type = type;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success((T)GsonUtils.fromJson(json, type), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
