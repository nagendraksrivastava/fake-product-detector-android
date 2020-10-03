package com.authentickart.detector.features.search.presenters;

import com.authentickart.detector.base.BasePresenter;
import com.authentickart.detector.features.search.contracts.ProductSearchContract;
import com.authentickart.detector.features.search.interactors.ProductSearchInteractor;
import com.authentickart.detector.network.request.search.SearchRequest;
import com.authentickart.detector.network.response.search.SearchResponse;

/**
 * Created by arif on 7/8/17.
 */

public class SearchProductPresenter extends BasePresenter<ProductSearchContract.View> implements ProductSearchContract.Presenter {

    private ProductSearchInteractor mProductSearchInteractor;

    public SearchProductPresenter(){
        mProductSearchInteractor = new ProductSearchInteractor();
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(mProductSearchInteractor, this);
    }

    public void searchProduct(SearchRequest searchRequest){
        if(getViewContract() != null){
            getViewContract().showProgress();
            mProductSearchInteractor.searchProduct(searchRequest);
        }
    }

    @Override
    public void onSearchProductSuccess(SearchResponse searchResponse) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showSearchProductSuccess(searchResponse);
        }
    }

    @Override
    public void onSearchProductFailure(String message) {
        if(getViewContract() != null){
            getViewContract().cancelProgress();
            getViewContract().showSearchProductFailure(message);
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
            getViewContract().showSearchProductFailure(error);
        }
    }

}
