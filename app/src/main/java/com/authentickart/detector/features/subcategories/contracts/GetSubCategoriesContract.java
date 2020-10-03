package com.authentickart.detector.features.subcategories.contracts;

import com.authentickart.detector.base.BasePresenterContract;
import com.authentickart.detector.base.BaseView;
import com.authentickart.detector.network.response.subcategories.Subcategory;

import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public interface GetSubCategoriesContract {
    interface View extends BaseView {
        void showGetSubCategoriesSuccess(List<Subcategory> subcategories);

        void showGetSubCategoriesFailure(String message);
    }

    interface Presenter extends BasePresenterContract {
        void onGetSubCategoriesSuccess(List<Subcategory> subcategories);

        void onGetSubCategoriesFailure(String message);
    }

    interface ApiContract {
        void getSubCategories(String categoryId);
    }
}
