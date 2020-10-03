package com.authentickart.detector.features.subcategories.interactors;

import com.authentickart.detector.base.BaseInteractor;
import com.authentickart.detector.base.RxSingleObserverEvent;
import com.authentickart.detector.features.subcategories.contracts.GetSubCategoriesContract;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.network.response.subcategories.GetSubCategoryResponse;
import com.authentickart.detector.utils.Constants;

/**
 * Created by arif on 7/8/17.
 */

public class GetSubCategoriesInteractor extends BaseInteractor implements GetSubCategoriesContract.ApiContract,
        RxSingleObserverEvent<GetSubCategoryResponse> {

    @Override
    public void getSubCategories(String categoryId) {
        if (getPresenter() != null) {
            AuthKartApiObservables.getInstance()
                    .getSubCategories(categoryId)
                    .subscribe(getSingleSubscriber(this));
        }
    }

    @Override
    public void onSuccess(GetSubCategoryResponse getSubCategoryResponse) {
        if (getPresenter() != null && getSubCategoryResponse != null) {
            if (getSubCategoryResponse.getStatus().getCode() == Constants.HttpCodes.OK) {
                getPresenter().onGetSubCategoriesSuccess(getSubCategoryResponse.getSubcategory());
            } else {
                getPresenter().onGetSubCategoriesFailure(getSubCategoryResponse.getStatus().getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable error) {
        if (getPresenter() != null) {
            getPresenter().onGetSubCategoriesFailure(error.getMessage() != null ? error.getMessage() : "");
        }
    }

    private GetSubCategoriesContract.Presenter getPresenter() {
        return (GetSubCategoriesContract.Presenter) getPresenterContract();
    }
}
