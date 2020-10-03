package com.authentickart.detector.features.search.interactors;

import com.authentickart.detector.base.BaseInteractor;
import com.authentickart.detector.base.RxSingleObserverEvent;
import com.authentickart.detector.features.search.contracts.ProductSearchContract;
import com.authentickart.detector.network.api.AuthKartApiObservables;
import com.authentickart.detector.network.request.search.SearchRequest;
import com.authentickart.detector.network.response.search.SearchResponse;
import com.authentickart.detector.utils.Constants;

/**
 * Created by arif on 7/8/17.
 */

public class ProductSearchInteractor extends BaseInteractor implements ProductSearchContract.ApiContract,
        RxSingleObserverEvent<SearchResponse> {

    @Override
    public void searchProduct(SearchRequest searchRequest) {
        if (getPresenter() != null) {
            AuthKartApiObservables.getInstance().searchProduct(searchRequest)
                    .subscribe(getSingleSubscriber(this));
        }
    }

    @Override
    public void onSuccess(SearchResponse searchResponse) {
        if(getPresenter() != null && searchResponse != null){
            if(searchResponse.getStatus().getCode() == Constants.HttpCodes.OK){
                getPresenter().onSearchProductSuccess(searchResponse);
            }else {
                getPresenter().onSearchProductFailure(searchResponse.getStatus().getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable error) {
        if(getPresenter() != null){
            getPresenter().onSearchProductFailure(error.getMessage() != null ? error.getMessage() : "");
        }
    }

    private ProductSearchContract.Presenter getPresenter() {
        return (ProductSearchContract.Presenter) getPresenterContract();
    }

}
