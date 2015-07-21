package com.tricon.labs.nearhere.init;

import android.app.Application;
import android.content.Context;
import android.location.Location;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.tricon.labs.nearhere.utils.LruBitmapCache;

/**
 * Created by gautam on 7/17/2015.
 */
public class NearHereApplication extends Application {

    public static Context appContext;
    public static RequestQueue requestQueue;
    public static ImageLoader imageLoader;
    public static Location currentLocation;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(appContext);
        imageLoader = new ImageLoader(requestQueue, new LruBitmapCache(LruBitmapCache.getCacheSize(appContext)));
    }
}
