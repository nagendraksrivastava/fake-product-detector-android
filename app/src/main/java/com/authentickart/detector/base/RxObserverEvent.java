package com.authentickart.detector.base;

/**
 * Created by arif on 30/7/17.
 */

public interface RxObserverEvent<T> extends RxErrorEvent {
    void onNext(T data);

    void onCompleted();
}
