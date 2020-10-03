package com.authentickart.detector.features.subcategories.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.authentickart.detector.R;
import com.authentickart.detector.base.BaseToolbarActivity;
import com.authentickart.detector.custom.GridSpacingItemDecoration;
import com.authentickart.detector.custom.SpacesItemDecoration;
import com.authentickart.detector.features.categories.adapters.CategoriesAdapter;
import com.authentickart.detector.features.subcategories.adapters.SubCategoriesAdapter;
import com.authentickart.detector.features.subcategories.contracts.GetSubCategoriesContract;
import com.authentickart.detector.features.subcategories.contracts.OnSubcategoryClickListener;
import com.authentickart.detector.features.subcategories.presenters.GetSubCategoriesPresenter;
import com.authentickart.detector.network.response.subcategories.Subcategory;
import com.authentickart.detector.utils.AppNavigator;
import com.authentickart.detector.utils.AppUtils;
import com.authentickart.detector.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public class SubCategoriesActivity extends BaseToolbarActivity implements GetSubCategoriesContract.View,
        OnSubcategoryClickListener{

    public static final int SPAN_COUNT = 2;
    private RecyclerView mRvSubCategories;
    private ProgressBar mPbSubCategories;
    private SubCategoriesAdapter mSubCategoriesAdapter;
    private List<Subcategory> mSubcategories;
    private GetSubCategoriesPresenter mGetSubCategoriesPresenter;
    private long mCategoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories_layout);
        initToolbar();
        initViews();
        initPresenter();
        mCategoryId = getIntent().getExtras().getLong(Constants.BundleKeys.CATEGORY_ID);
        if (mCategoryId > 0) {
            if (isPresenterSubscribed(mGetSubCategoriesPresenter, this)) {
                mGetSubCategoriesPresenter.getSubCategories(String.valueOf(mCategoryId));
            }
        }
    }

    @Override
    protected void onDestroy() {
        destroyPresenter();
        super.onDestroy();
    }

    private void initToolbar() {
        hideToolbarIcon();
        setToolbarTitle(getString(R.string.subcategories));
    }

    private void initViews() {
        mRvSubCategories = (RecyclerView) findViewById(R.id.rv_subcategories);
        mPbSubCategories = (ProgressBar) findViewById(R.id.pb_subcategories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mRvSubCategories.setLayoutManager(gridLayoutManager);
        mRvSubCategories.setHasFixedSize(true);
        mSubcategories = new ArrayList<>();
        mSubCategoriesAdapter = new SubCategoriesAdapter(mSubcategories, this);
        mRvSubCategories.addItemDecoration(
                new GridSpacingItemDecoration(SPAN_COUNT, (int)getResources().getDimension(R.dimen.dimen_8dp), true));
        mRvSubCategories.setAdapter(mSubCategoriesAdapter);
    }

    private void initPresenter() {
        mGetSubCategoriesPresenter = new GetSubCategoriesPresenter();
        mGetSubCategoriesPresenter.subscribeView(this);
    }

    private void destroyPresenter() {
        if (isPresenterSubscribed(mGetSubCategoriesPresenter, this)) {
            mGetSubCategoriesPresenter.unsubscribeView(this);
        }
    }

    @Override
    public void showGetSubCategoriesSuccess(List<Subcategory> subcategories) {
        if (!AppUtils.isEmpty(subcategories)) {
            mSubcategories.clear();
            mSubcategories.addAll(subcategories);
            mSubCategoriesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showGetSubCategoriesFailure(String message) {
        showShortToast(message);
    }

    @Override
    public void showProgress() {
        mPbSubCategories.setVisibility(View.VISIBLE);
        mRvSubCategories.setVisibility(View.GONE);
    }

    @Override
    public void cancelProgress() {
        mPbSubCategories.setVisibility(View.GONE);
        mRvSubCategories.setVisibility(View.VISIBLE);
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
    public void onSubCategoryClicked(long subcategoryId) {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.BundleKeys.SUB_CATEGORY_ID, subcategoryId);
        AppNavigator.getInstance().startBrandsActivity(this, bundle);
    }
}
