package com.authentickart.detector.base;

import android.support.annotation.NonNull;

/**
 * Created by arif on 30/7/17.
 */

public interface BaseView {
    void showProgress();

    void cancelProgress();

    void networkError(@NonNull String message);

    void onError(@NonNull String message);
}
