package com.authentickart.detector.features.categories.interactors;

import com.authentickart.detector.base.BaseInteractor;
import com.authentickart.detector.base.RxSingleObserverEvent;
import com.authentickart.detector.features.categories.contracts.GetCategoriesContract;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.network.response.categories.GetCategoriesResponse;
import com.authentickart.detector.utils.Constants;

/**
 * Created by arif on 6/8/17.
 */

public class GetCategoriesInteractor extends BaseInteractor implements GetCategoriesContract.ApiContract,
        RxSingleObserverEvent<GetCategoriesResponse> {

    @Override
    public void getCategories() {
        if (getPresenter() != null) {
            AuthKartApiObservables
                    .getInstance()
                    .getCategories()
                    .subscribe(getSingleSubscriber(this));
        }
    }

    @Override
    public void onSuccess(GetCategoriesResponse getCategoriesResponse) {
        if (getPresenter() != null) {
            if (getCategoriesResponse.getStatus() != null) {
                if (getCategoriesResponse.getStatus().getCode() == Constants.HttpCodes.OK)
                    getPresenter().onGetCategoriesSuccess(getCategoriesResponse.getCategory());
                else
                    getPresenter().onGetCategoriesFailure(getCategoriesResponse.getStatus().getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable error) {
        if (getPresenter() != null) {
            getPresenter().onGetCategoriesFailure(error != null ? error.getMessage() : "");
        }
    }

    private GetCategoriesContract.Presenter getPresenter() {
        return (GetCategoriesContract.Presenter) getPresenterContract();
    }
}
