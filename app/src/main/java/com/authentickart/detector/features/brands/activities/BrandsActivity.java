package com.authentickart.detector.features.brands.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.authentickart.detector.R;
import com.authentickart.detector.base.BaseToolbarActivity;
import com.authentickart.detector.custom.GridSpacingItemDecoration;
import com.authentickart.detector.features.brands.adapters.BrandsAdapter;
import com.authentickart.detector.features.brands.contracts.GetBrandsContract;
import com.authentickart.detector.features.brands.contracts.OnBrandSelectedListener;
import com.authentickart.detector.features.brands.presenters.GetBrandsPresenter;
import com.authentickart.detector.features.subcategories.adapters.SubCategoriesAdapter;
import com.authentickart.detector.network.response.brands.Brand;
import com.authentickart.detector.utils.AppNavigator;
import com.authentickart.detector.utils.AppUtils;
import com.authentickart.detector.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public class BrandsActivity extends BaseToolbarActivity implements GetBrandsContract.View, OnBrandSelectedListener {

    public static final int SPAN_COUNT = 2;
    private RecyclerView mRvBrands;
    private ProgressBar mPbBrands;
    private BrandsAdapter mBrandsAdapter;
    private List<Brand> mBrands;
    private GetBrandsPresenter mGetBrandsPresenter;
    private long mSubCategoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands_layout);
        initToolbar();
        initViews();
        initPresenters();
        mSubCategoryId = getIntent().getExtras().getLong(Constants.BundleKeys.SUB_CATEGORY_ID);
        if (mSubCategoryId > 0) {
            if (isPresenterSubscribed(mGetBrandsPresenter, this)) {
                mGetBrandsPresenter.getBrands(String.valueOf(mSubCategoryId));
            }
        }
    }

    @Override
    protected void onDestroy() {
        destroyPresenters();
        super.onDestroy();
    }

    private void initToolbar(){
        hideToolbarIcon();
        setToolbarTitle(getString(R.string.brands));
    }

    private void initViews(){
        mRvBrands = (RecyclerView) findViewById(R.id.rv_brands);
        mPbBrands = (ProgressBar) findViewById(R.id.pb_brands);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mRvBrands.setLayoutManager(gridLayoutManager);
        mRvBrands.setHasFixedSize(true);
        mBrands = new ArrayList<>();
        mBrandsAdapter = new BrandsAdapter(mBrands, this);
        mRvBrands.addItemDecoration(
                new GridSpacingItemDecoration(SPAN_COUNT, (int)getResources().getDimension(R.dimen.dimen_8dp), true));
        mRvBrands.setAdapter(mBrandsAdapter);
    }

    private void initPresenters(){
        mGetBrandsPresenter = new GetBrandsPresenter();
        mGetBrandsPresenter.subscribeView(this);
    }

    private void destroyPresenters(){
        if(isPresenterSubscribed(mGetBrandsPresenter, this)){
            mGetBrandsPresenter.unsubscribeView(this);
        }
    }

    @Override
    public void showGetBrandsSuccess(List<Brand> brands) {
        if (!AppUtils.isEmpty(brands)) {
            mBrands.clear();
            mBrands.addAll(brands);
            mBrandsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showGetBrandsFailure(String message) {
        showShortToast(message);
    }

    @Override
    public void showProgress() {
        mPbBrands.setVisibility(View.VISIBLE);
        mRvBrands.setVisibility(View.GONE);
    }

    @Override
    public void cancelProgress() {
        mPbBrands.setVisibility(View.GONE);
        mRvBrands.setVisibility(View.VISIBLE);
    }

    @Override
    public void networkError(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void onError(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void onBrandSelected(Brand brand) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleKeys.BRAND, brand);
        bundle.putLong(Constants.BundleKeys.SUB_CATEGORY_ID, mSubCategoryId);
        AppNavigator.getInstance().startAuthenticationActivity(this, bundle);
    }
}
