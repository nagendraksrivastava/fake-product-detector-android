package com.authentickart.detector.features.brands.presenters;

import com.authentickart.detector.base.BasePresenter;
import com.authentickart.detector.features.brands.contracts.GetBrandsContract;
import com.authentickart.detector.features.brands.interactors.GetBrandsInteractor;
import com.authentickart.detector.network.response.brands.Brand;

import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public class GetBrandsPresenter extends BasePresenter<GetBrandsContract.View> implements GetBrandsContract.Presenter {

    private GetBrandsInteractor mGetBrandsInteractor;

    public GetBrandsPresenter(){
        mGetBrandsInteractor = new GetBrandsInteractor();
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(mGetBrandsInteractor, this);
    }

    public void getBrands(String subcategoryId){
        if(getViewContract() != null){
            getViewContract().showProgress();
            mGetBrandsInteractor.getBrands(subcategoryId);
        }
    }

    @Override
    public void onGetBrandsSuccess(List<Brand> brands) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetBrandsSuccess(brands);
        }
    }

    @Override
    public void onGetBrandsFailure(String message) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetBrandsFailure(message);
        }
    }

    @Override
    public void onError(Throwable error) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
        }
    }

    @Override
    public void onNetworkError(String error) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetBrandsFailure(error);
        }
    }
}
