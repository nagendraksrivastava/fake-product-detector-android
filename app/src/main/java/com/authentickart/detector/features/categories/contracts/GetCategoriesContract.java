package com.authentickart.detector.features.categories.contracts;

import com.authentickart.detector.base.BasePresenterContract;
import com.authentickart.detector.base.BaseView;
import com.authentickart.detector.network.response.categories.Category;

import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public interface GetCategoriesContract {
    interface View extends BaseView{
        void showGetCategoriesSuccess(List<Category> categories);
        void showGetCategoriesFailure(String message);
    }
    interface Presenter extends BasePresenterContract{
        void onGetCategoriesSuccess(List<Category> categories);
        void onGetCategoriesFailure(String message);
    }
    interface ApiContract{
        void getCategories();
    }
}
