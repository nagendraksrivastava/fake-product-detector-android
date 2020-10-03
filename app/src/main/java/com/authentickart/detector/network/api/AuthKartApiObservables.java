package com.authentickart.detector.network.api;

import com.authentickart.detector.base.BaseApiObservables;
import com.authentickart.detector.network.request.search.SearchRequest;
import com.authentickart.detector.network.request.signin.SignInRequest;
import com.authentickart.detector.network.request.signup.SignUpRequest;
import com.authentickart.detector.network.response.brands.GetBrandsResponse;
import com.authentickart.detector.network.response.categories.GetCategoriesResponse;
import com.authentickart.detector.network.response.search.SearchResponse;
import com.authentickart.detector.network.response.signin.SignInResponse;
import com.authentickart.detector.network.response.signup.SignUpResponse;
import com.authentickart.detector.network.response.subcategories.GetSubCategoryResponse;

import io.reactivex.Single;

/**
 * Created by arif on 23/7/17.
 */

public class AuthKartApiObservables extends BaseApiObservables {

    private AuthKartApi mAuthKartApi;

    private AuthKartApiObservables() {
        mAuthKartApi = ApiProvider.getInstance().getAuthKartApi();
    }

    private static final class AuthKartApiObservablesHolder{
        private static final AuthKartApiObservables AUTH_KART_API_OBSERVABLES = new AuthKartApiObservables();
    }

    public static AuthKartApiObservables getInstance(){
        return AuthKartApiObservablesHolder.AUTH_KART_API_OBSERVABLES;
    }

    public Single<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        return mAuthKartApi.signUp(signUpRequest)
                .compose(this.<SignUpResponse>applyCommonSchedulersSingle());
    }

    public Single<SignInResponse> signIn(SignInRequest signInRequest) {
        return mAuthKartApi.signIn(signInRequest)
                .compose(this.<SignInResponse>applyCommonSchedulersSingle());
    }

    public Single<GetCategoriesResponse> getCategories(){
        return mAuthKartApi.getCategories()
                .compose(this.<GetCategoriesResponse>applyCommonSchedulersSingle());
    }

    public Single<GetSubCategoryResponse> getSubCategories(String categoryId){
        return mAuthKartApi.getSubCategories(categoryId)
                .compose(this.<GetSubCategoryResponse>applyCommonSchedulersSingle());
    }

    public Single<GetBrandsResponse> getBrand(String subCategoryId){
        return mAuthKartApi.getBrands(subCategoryId)
                .compose(this.<GetBrandsResponse>applyCommonSchedulersSingle());
    }

    public Single<SearchResponse> searchProduct(SearchRequest searchRequest){
        return mAuthKartApi.searchProduct(searchRequest)
                .compose(this.<SearchResponse>applyCommonSchedulersSingle());
    }

}
