package com.authentickart.detector.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by arif on 23/7/17.
 */

public class Constants {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef
    public @interface IntegerConstants {
        int API_TIMEOUT = 60;
        int DB_VERSION = 1;
        int PRODUCT_NOT_FOUND = 0;
        int PRODUCT_FOUND = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef
    public @interface StringConstants {
        String DATABASE_NAME = "auth-kart-detector";
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef
    public @interface HttpCodes {
        int OK = 200;
        int CREATED = 201;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface EndPoints {
        String AUTH_KART_STAGING_URL = "http://13.58.182.84/";
        String AUTH_KART_PRODUCTION_URL = "";//TODO add real prod url
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface BundleKeys {
        String CATEGORY_ID = "category_id";
        String SUB_CATEGORY_ID = "subcategory_id";
        String BRAND = "brand";
    }
}
