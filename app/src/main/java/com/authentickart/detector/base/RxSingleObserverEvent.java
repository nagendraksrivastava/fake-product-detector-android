package com.authentickart.detector.base;

/**
 * Created by arif on 30/7/17.
 */

public interface RxSingleObserverEvent<T> extends RxErrorEvent {
    void onSuccess(T value);
}
