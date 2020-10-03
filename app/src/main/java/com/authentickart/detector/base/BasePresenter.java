package com.authentickart.detector.base;

import android.util.ArrayMap;

import com.authentickart.detector.utils.AppUtils;
import com.authentickart.detector.utils.Logger;

import java.util.Map;

/**
 * Created by arif on 30/7/17.
 */

public abstract class BasePresenter<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();
    private V viewContract;
    private Map<BaseInteractor, BasePresenterContract> mInteractorMap = new ArrayMap<>();

    /**
     * Call this when View is being created
     * <p/>
     * This binds the view to the listener
     *
     * @param updateListener
     */
    public void subscribeView(V updateListener) {
        subscribeInteractor();
        final V previousListener = viewContract;
        if (previousListener != null) {
            throw new IllegalStateException("A view was already bound to this listener, please unbind it before binding any other view = " + previousListener);
        }
        viewContract = updateListener;
        subscribeAllPresenterViews();
    }

    /**
     * Check if View was previously subscribed.
     *
     * @param updateListener
     * @return
     */
    public boolean wasSubscribed(V updateListener) {
        if (updateListener != null) {
            if (viewContract == null)
                return false;
            if (updateListener == viewContract)
                return true;
        }
        return false;
    }

    /**
     * Call this when View is being finished or destroyed
     *
     * @param updateListener
     */
    public void unsubscribeView(V updateListener) {
        final V previousListener = viewContract;
        if (previousListener == updateListener) {
            this.viewContract = null;
        } else {
            throw new IllegalStateException("No such listener was bound previously.");
        }
        unsubscribeAllPresenterViews();
    }

    protected void subscribeInteractor(BaseInteractor interactor, BasePresenterContract contract) {
        mInteractorMap.put(interactor, contract);
    }

    public abstract void subscribeInteractor();

    protected V getViewContract() {
        return viewContract;
    }

    private void subscribeAllPresenterViews() {
        if (!AppUtils.isEmpty(mInteractorMap)) {
            for (Map.Entry<BaseInteractor, BasePresenterContract> entry : mInteractorMap.entrySet()) {
                Logger.e(TAG, String.format("Subscribing %s to %s", entry.getKey().getClass().getSimpleName(), entry.getValue().getClass().getSimpleName()));
                entry.getKey().subscribePresenterView(entry.getValue());
            }
        }
    }

    private void unsubscribeAllPresenterViews() {
        if (!AppUtils.isEmpty(mInteractorMap)) {
            for (Map.Entry<BaseInteractor, BasePresenterContract> entry : mInteractorMap.entrySet()) {
                Logger.e(TAG, String.format("unsubscribing %s from %s", entry.getKey().getClass().getSimpleName(), entry.getValue().getClass().getSimpleName()));
                entry.getKey().unsubscribePresenterView(entry.getValue());
            }
        }
    }
}

