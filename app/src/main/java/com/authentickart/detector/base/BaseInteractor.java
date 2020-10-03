package com.authentickart.detector.base;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by arif on 30/7/17.
 */

public class BaseInteractor {
    private static final String TAG = BaseInteractor.class.getSimpleName();
    private final CompositeDisposable subscriptionQueue = new CompositeDisposable();
    private BasePresenterContract presenterContract;

    /**
     * Call this when View is being created
     * <p/>
     * This binds the view to the listener
     *
     * @param updateListener
     */
    public void subscribePresenterView(BasePresenterContract updateListener) {
        final BasePresenterContract previousListener = presenterContract;
        if (previousListener != null) {
            throw new IllegalStateException("A view was already bound to this listener, please unbind it before binding any other view = " + previousListener);
        }
        presenterContract = updateListener;
    }

    /**
     * Check if View was previously subscribed.
     *
     * @param updateListener
     * @return
     */
    public boolean wasPresenterViewSubscribed(BasePresenterContract updateListener) {
        if (updateListener != null) {
            if (presenterContract == null)
                return false;
            if (updateListener == presenterContract)
                return true;
        }
        return false;
    }

    /**
     * Call this when View is being finished or destroyed
     *
     * @param updateListener
     */
    public void unsubscribePresenterView(BasePresenterContract updateListener) {
        final BasePresenterContract previousListener = presenterContract;
        if (previousListener == updateListener) {
            this.presenterContract = null;
        } else {
            throw new IllegalStateException("No such listener was bound previously.");
        }
        clearSubscriptionQueue();
    }

    public BasePresenterContract getPresenterContract() {
        return presenterContract;
    }

    void queueSubscriptionForDisposal(Disposable subscription) {
        subscriptionQueue.add(subscription);
    }

    //Queue multiple subscriptions for removal
    public void queueSubscriptionsForDisposal(Disposable... subscriptions) {
        for (Disposable subscription : subscriptions) {
            subscriptionQueue.add(subscription);
        }
    }

    void clearSubscriptionQueue() {
        subscriptionQueue.clear();
    }

    protected <E> Observer<E> getSubscriber(final RxObserverEvent subscriberEvents) {
        return new Observer<E>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                queueSubscriptionForDisposal(d);
            }

            @Override
            public void onError(Throwable e) {
                subscriberEvents.onError(e);
            }

            @Override
            public void onComplete() {
                subscriberEvents.onCompleted();
            }

            @Override
            public void onNext(E next) {
                subscriberEvents.onNext(next);
            }
        };
    }

    //SingleSubscriber for better performance!!!
    protected <E> SingleObserver<E> getSingleSubscriber(final RxSingleObserverEvent singleObserverEvents) {
        return new SingleObserver<E>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                queueSubscriptionForDisposal(d);
            }

            @Override
            public void onSuccess(E value) {
                singleObserverEvents.onSuccess(value);
            }

            @Override
            public void onError(Throwable error) {
                singleObserverEvents.onError(error);
            }
        };
    }
}
