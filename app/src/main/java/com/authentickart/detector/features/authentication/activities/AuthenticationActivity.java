package com.authentickart.detector.features.authentication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.authentickart.detector.R;
import com.authentickart.detector.base.BaseToolbarActivity;
import com.authentickart.detector.custom.AuthKartButton;
import com.authentickart.detector.custom.AuthKartTextView;
import com.authentickart.detector.features.search.contracts.ProductSearchContract;
import com.authentickart.detector.features.search.presenters.SearchProductPresenter;
import com.authentickart.detector.network.request.search.SearchRequest;
import com.authentickart.detector.network.response.brands.Brand;
import com.authentickart.detector.network.response.search.SearchResponse;
import com.authentickart.detector.utils.Constants;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by arif on 7/8/17.
 */

public class AuthenticationActivity extends BaseToolbarActivity implements View.OnClickListener, ProductSearchContract.View {

    private Brand mBrand;
    private long mSubCategoryId;
    private AuthKartTextView mTvBrandName, mTvAuthenticity;
    private ImageView mImgAuthenticity;
    private LinearLayout mLLAuthenticityLayout;
    private AuthKartButton mBtnScanBarcode;
    private SearchProductPresenter mSearchProductPresenter;
    private ProgressBar mPbSearchProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_layout);
        initToolbar();
        initViews();
        setListeners();
        initPresenters();
        mBrand = getIntent().getExtras().getParcelable(Constants.BundleKeys.BRAND);
        if (mBrand != null) {
            mTvBrandName.setText(String.format("%s %s", getString(R.string.brand_selection), mBrand.getName()));
        }
        mSubCategoryId = getIntent().getExtras().getLong(Constants.BundleKeys.SUB_CATEGORY_ID);
    }

    @Override
    protected void onDestroy() {
        destroyPresenters();
        super.onDestroy();
    }

    private void initToolbar() {
        hideToolbarIcon();
        setToolbarTitle(getString(R.string.scan_product));
    }

    private void initViews() {
        mTvBrandName = (AuthKartTextView) findViewById(R.id.tv_brand_name);
        mLLAuthenticityLayout = (LinearLayout) findViewById(R.id.ll_authenticity);
        mImgAuthenticity = (ImageView) findViewById(R.id.img_authenticity);
        mTvAuthenticity = (AuthKartTextView) findViewById(R.id.tv_authenticity);
        mBtnScanBarcode = (AuthKartButton) findViewById(R.id.btn_scan_code);
        mPbSearchProduct = (ProgressBar) findViewById(R.id.pb_product_search);
    }

    private void setListeners() {
        mBtnScanBarcode.setOnClickListener(this);
    }

    private void initPresenters() {
        mSearchProductPresenter = new SearchProductPresenter();
        mSearchProductPresenter.subscribeView(this);
    }

    private void destroyPresenters() {
        if (isPresenterSubscribed(mSearchProductPresenter, this)) {
            mSearchProductPresenter.unsubscribeView(this);
        }
    }

    private void showProductFoundUI() {
        mLLAuthenticityLayout.setVisibility(View.VISIBLE);
        mTvAuthenticity.setText(getString(R.string.product_authentic));
        mImgAuthenticity.setImageResource(R.drawable.ic_tick_mark);
    }

    private void showProductNotFoundUI() {
        mLLAuthenticityLayout.setVisibility(View.VISIBLE);
        mTvAuthenticity.setText(getString(R.string.product_authenticity_unknown));
        mImgAuthenticity.setImageResource(R.drawable.ic_cross_mark);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan_code:
                IntentIntegrator intentIntegrator = new IntentIntegrator(this);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt(getString(R.string.scan_product_prompt));
                intentIntegrator.initiateScan();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                showShortToast("Cancelled");
            } else {
                showShortToast("Scanned " + result.getContents());
                if (isPresenterSubscribed(mSearchProductPresenter, this)) {
                    SearchRequest searchRequest = new SearchRequest();
                    searchRequest.setBarcode(result.getContents());
                    //TODO add serial number when it can be scanned
                    searchRequest.setSerialNo("");
                    searchRequest.setBrandId(String.valueOf(mBrand.getId()));
                    searchRequest.setSubcategoryId(String.valueOf(mSubCategoryId));
                    mSearchProductPresenter.searchProduct(searchRequest);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showSearchProductSuccess(SearchResponse searchResponse) {
        if (searchResponse.getCode() == Constants.IntegerConstants.PRODUCT_FOUND) {
            showProductFoundUI();
        } else {
            showProductNotFoundUI();
        }
    }

    @Override
    public void showSearchProductFailure(String message) {
        showShortToast(message);
    }

    @Override
    public void showProgress() {
        mBtnScanBarcode.setEnabled(false);
        mPbSearchProduct.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelProgress() {
        mBtnScanBarcode.setEnabled(true);
        mPbSearchProduct.setVisibility(View.GONE);
    }

    @Override
    public void networkError(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void onError(@NonNull String message) {
        showShortToast(message);
    }
}
