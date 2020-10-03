package com.authentickart.detector.features.categories.presenters;

import com.authentickart.detector.base.BasePresenter;
import com.authentickart.detector.features.categories.contracts.GetCategoriesContract;
import com.authentickart.detector.features.categories.interactors.GetCategoriesInteractor;
import com.authentickart.detector.network.response.categories.Category;

import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public class GetCategoriesPresenter extends BasePresenter<GetCategoriesContract.View> implements GetCategoriesContract.Presenter {

    private GetCategoriesInteractor mGetCategoriesInteractor;

    public GetCategoriesPresenter(){
        mGetCategoriesInteractor = new GetCategoriesInteractor();
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(mGetCategoriesInteractor, this);
    }

    public void getCategories(){
        if(getViewContract() != null){
            getViewContract().showProgress();
            mGetCategoriesInteractor.getCategories();
        }
    }

    @Override
    public void onGetCategoriesSuccess(List<Category> categories) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetCategoriesSuccess(categories);
        }
    }

    @Override
    public void onGetCategoriesFailure(String message) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetCategoriesFailure(message);
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
            getViewContract().showGetCategoriesFailure(error);
        }
    }
}
