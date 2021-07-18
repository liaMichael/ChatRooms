package com.example.chatrooms.model.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Utils {

    /**
     * Finds a Bitmap by its resource name.
     * @param context a context
     * @param name string of the resource's name
     * @return the Bitmap.
     */
    public static Bitmap findBitmapByName(Context context, String name) {
        return BitmapFactory.decodeResource(
                context.getResources(),
                findDrawableResourceByName(context, name)
        );
    }

    /**
     * Finds a drawable resource id by its name.
     * @param context a context
     * @param name string of the resource's name
     * @return the drawable resource id.
     */
    public static int findDrawableResourceByName(Context context, String name) {
        return context.getResources().getIdentifier(
                name,
                "drawable",
                context.getPackageName()
        );
    }
}
