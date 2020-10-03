package com.authentickart.detector.features.subcategories.presenters;

import com.authentickart.detector.base.BasePresenter;
import com.authentickart.detector.features.subcategories.contracts.GetSubCategoriesContract;
import com.authentickart.detector.features.subcategories.interactors.GetSubCategoriesInteractor;
import com.authentickart.detector.network.response.subcategories.Subcategory;

import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public class GetSubCategoriesPresenter extends BasePresenter<GetSubCategoriesContract.View> implements GetSubCategoriesContract.Presenter {

    private GetSubCategoriesInteractor mGetSubCategoriesInteractor;

    public GetSubCategoriesPresenter(){
        mGetSubCategoriesInteractor = new GetSubCategoriesInteractor();
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(mGetSubCategoriesInteractor, this);
    }

    public void getSubCategories(String categoryId){
        if(getViewContract() != null){
            getViewContract().showProgress();
            mGetSubCategoriesInteractor.getSubCategories(categoryId);
        }
    }

    @Override
    public void onGetSubCategoriesSuccess(List<Subcategory> subcategories) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetSubCategoriesSuccess(subcategories);
        }
    }

    @Override
    public void onGetSubCategoriesFailure(String message) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showGetSubCategoriesFailure(message);
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
            getViewContract().showGetSubCategoriesFailure(error);
        }
    }

}
