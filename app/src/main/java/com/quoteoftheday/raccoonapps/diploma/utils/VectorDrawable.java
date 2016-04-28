package com.quoteoftheday.raccoonapps.diploma.utils;

import android.app.Application;

import com.bettervectordrawable.Convention;
import com.bettervectordrawable.VectorDrawableCompat;
import com.quoteoftheday.raccoonapps.diploma.R;

public class VectorDrawable extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        int[] ids = VectorDrawableCompat.findVectorResourceIdsByConvention(getResources(), R.drawable.class, Convention.RESOURCE_NAME_HAS_VECTOR_SUFFIX);
        VectorDrawableCompat.enableResourceInterceptionFor(getResources(), ids);
    }
}