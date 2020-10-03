package com.authentickart.detector.features.brands.contracts;

import com.authentickart.detector.base.BasePresenterContract;
import com.authentickart.detector.base.BaseView;
import com.authentickart.detector.network.response.brands.Brand;

import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public interface GetBrandsContract {
    interface View extends BaseView{
        void showGetBrandsSuccess(List<Brand> brands);
        void showGetBrandsFailure(String message);
    }
    interface Presenter extends BasePresenterContract{
        void onGetBrandsSuccess(List<Brand> brands);
        void onGetBrandsFailure(String message);
    }
    interface ApiContract{
        void getBrands(String subcategoryId);
    }
}
