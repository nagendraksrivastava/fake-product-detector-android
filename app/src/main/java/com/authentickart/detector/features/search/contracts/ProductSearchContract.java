package com.authentickart.detector.features.search.contracts;

import com.authentickart.detector.base.BasePresenterContract;
import com.authentickart.detector.base.BaseView;
import com.authentickart.detector.network.request.search.SearchRequest;
import com.authentickart.detector.network.response.search.SearchResponse;

/**
 * Created by arif on 7/8/17.
 */

public interface ProductSearchContract {

    interface View extends BaseView{
        void showSearchProductSuccess(SearchResponse searchResponse);
        void showSearchProductFailure(String message);
    }

    interface Presenter extends BasePresenterContract{
        void onSearchProductSuccess(SearchResponse searchResponse);
        void onSearchProductFailure(String message);
    }

    interface ApiContract{
        void searchProduct(SearchRequest searchRequest);
    }

}
