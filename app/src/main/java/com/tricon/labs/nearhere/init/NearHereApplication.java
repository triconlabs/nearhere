package com.tricon.labs.nearhere.init;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.tricon.labs.nearhere.R;

/**
 * Created by gautam on 7/17/2015.
 */
public class NearHereApplication extends Application {

    public static Context appContext;
    public static RequestQueue requestQueue;
    public static Location currentLocation;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(appContext);

        // Create default options which will be used for every
        // displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.color.black_transparency)
                .showImageForEmptyUri(R.color.black_transparency)
                .showImageOnFail(R.color.black_transparency)
                .build();

        // Create global configuration and initialize ImageLoader with this
        // configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(appContext)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(10 * 1024 * 1024)
                .diskCacheSize(25 * 1024 * 1024)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
