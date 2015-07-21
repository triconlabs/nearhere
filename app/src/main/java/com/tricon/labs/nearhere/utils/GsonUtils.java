package com.tricon.labs.nearhere.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by gautam3 on 14-03-2015.
 */
public abstract class GsonUtils {

    private static final Gson GSON = createGson();

    private static Gson createGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return GSON.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static String toJson(Object object, Type type) {
        return GSON.toJson(object, type);
    }

}
