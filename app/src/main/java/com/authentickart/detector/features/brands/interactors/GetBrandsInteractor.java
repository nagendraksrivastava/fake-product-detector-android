package com.authentickart.detector.features.brands.interactors;

import com.authentickart.detector.base.BaseInteractor;
import com.authentickart.detector.base.RxSingleObserverEvent;
import com.authentickart.detector.features.brands.contracts.GetBrandsContract;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.network.response.brands.GetBrandsResponse;
import com.authentickart.detector.utils.Constants;

/**
 * Created by arif on 7/8/17.
 */

public class GetBrandsInteractor extends BaseInteractor implements GetBrandsContract.ApiContract, RxSingleObserverEvent<GetBrandsResponse> {

    @Override
    public void getBrands(String subcategoryId) {
        if (getPresenter() != null) {
            AuthKartApiObservables.getInstance()
                    .getBrand(subcategoryId)
                    .subscribe(getSingleSubscriber(this));
        }
    }

    @Override
    public void onSuccess(GetBrandsResponse getBrandsResponse) {
        if (getPresenter() != null && getBrandsResponse != null) {
            if (getBrandsResponse.getStatus().getCode() == Constants.HttpCodes.OK) {
                getPresenter().onGetBrandsSuccess(getBrandsResponse.getBrand());
            } else {
                getPresenter().onGetBrandsFailure(getBrandsResponse.getStatus().getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable error) {
        if(getPresenter() != null){
            getPresenter().onGetBrandsFailure(error.getMessage() != null ? error.getMessage() : "");
        }
    }

    private GetBrandsContract.Presenter getPresenter() {
        return (GetBrandsContract.Presenter) getPresenterContract();
    }
}
