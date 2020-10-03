package com.authentickart.detector.network.api;

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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by arif on 23/7/17.
 */

public interface AuthKartApi {

    @POST("super/v1/signup/")
    Single<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("super/v1/login/")
    Single<SignInResponse> signIn(@Body SignInRequest signInRequest);

    @GET("super/v1/category/")
    Single<GetCategoriesResponse> getCategories();

    @GET("super/v1/subcategory/{category_id}")
    Single<GetSubCategoryResponse> getSubCategories(@Path("category_id") String categoryId);

    @GET("super/v1/brand/{subcategory_id}")
    Single<GetBrandsResponse> getBrands(@Path("subcategory_id") String subCategoryId);

    @POST("super/v1/product/")
    Single<SearchResponse> searchProduct(@Body SearchRequest searchRequest);

}
