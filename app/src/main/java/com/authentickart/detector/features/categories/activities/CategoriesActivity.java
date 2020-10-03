package com.authentickart.detector.features.categories.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.authentickart.detector.R;
import com.authentickart.detector.base.BaseToolbarActivity;
import com.authentickart.detector.custom.SpacesItemDecoration;
import com.authentickart.detector.features.categories.adapters.CategoriesAdapter;
import com.authentickart.detector.features.categories.contracts.GetCategoriesContract;
import com.authentickart.detector.features.categories.contracts.OnCategoryClickListener;
import com.authentickart.detector.features.categories.presenters.GetCategoriesPresenter;
import com.authentickart.detector.network.response.categories.Category;
import com.authentickart.detector.utils.AppNavigator;
import com.authentickart.detector.utils.AppUtils;
import com.authentickart.detector.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public class CategoriesActivity extends BaseToolbarActivity implements GetCategoriesContract.View, OnCategoryClickListener {

    private RecyclerView mRvCategories;
    private ProgressBar mPbCategories;
    private CategoriesAdapter mCategoriesAdapter;
    private List<Category> mCategories;
    private GetCategoriesPresenter mGetCategoriesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_layout);
        initToolbar();
        initViews();
        initPresenters();
        if (isPresenterSubscribed(mGetCategoriesPresenter, this))
            mGetCategoriesPresenter.getCategories();
    }

    @Override
    protected void onDestroy() {
        destroyPresenters();
        super.onDestroy();
    }

    private void initToolbar() {
        hideToolbarIcon();
        setToolbarTitle(getString(R.string.categories));
    }

    private void initViews() {
        mRvCategories = (RecyclerView) findViewById(R.id.rv_categories);
        mPbCategories = (ProgressBar) findViewById(R.id.pb_categories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvCategories.setLayoutManager(linearLayoutManager);
        mRvCategories.setHasFixedSize(true);
        mCategories = new ArrayList<>();
        mCategoriesAdapter = new CategoriesAdapter(mCategories, this);
        mRvCategories.addItemDecoration(new SpacesItemDecoration((int) getResources().getDimension(R.dimen.dimen_16dp)));
        mRvCategories.setAdapter(mCategoriesAdapter);
    }

    private void initPresenters() {
        mGetCategoriesPresenter = new GetCategoriesPresenter();
        mGetCategoriesPresenter.subscribeView(this);
    }

    private void destroyPresenters() {
        if (isPresenterSubscribed(mGetCategoriesPresenter, this)) {
            mGetCategoriesPresenter.unsubscribeView(this);
        }
    }

    @Override
    public void showGetCategoriesSuccess(List<Category> categories) {
        if (!AppUtils.isEmpty(categories)) {
            mCategories.clear();
            mCategories.addAll(categories);
            mCategoriesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showGetCategoriesFailure(String message) {
        showShortToast(message);
    }

    @Override
    public void showProgress() {
        mPbCategories.setVisibility(View.VISIBLE);
        mRvCategories.setVisibility(View.GONE);
    }

    @Override
    public void cancelProgress() {
        mPbCategories.setVisibility(View.GONE);
        mRvCategories.setVisibility(View.VISIBLE);
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
    public void onCategoryClicked(long categoryId) {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.BundleKeys.CATEGORY_ID, categoryId);
        AppNavigator.getInstance().startSubCategoriesActivity(this, bundle);
    }
}
