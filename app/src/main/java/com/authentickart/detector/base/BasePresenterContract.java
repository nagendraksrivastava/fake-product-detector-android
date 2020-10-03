package com.authentickart.detector.base;

/**
 * Created by arif on 30/7/17.
 */

public interface BasePresenterContract {
    void onError(Throwable error);

    void onNetworkError(String error);
}
