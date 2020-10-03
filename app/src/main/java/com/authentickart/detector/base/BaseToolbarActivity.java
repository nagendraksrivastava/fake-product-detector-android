package com.authentickart.detector.base;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.authentickart.detector.R;

/**
 * Created by arif on 23/7/17.
 */

public class BaseToolbarActivity extends BaseActivity {

    protected Toolbar mToolbar;
    private TextView mToolbarTitle, mToolbarSubTitle;
    private ImageView mImgIcon;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setUpToolBar();
    }

    @Override
    public void setContentView(View view) {
        setTheme(R.style.AppTheme);
        super.setContentView(view);
        setUpToolBar();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setUpToolBar();
    }

    protected void setUpToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
//        mToolbarSubTitle = (TextView) mToolbar.findViewById(R.id.toolbar_subtitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (mToolbar.findViewById(R.id.logo) != null)
            mImgIcon = (ImageView) mToolbar.findViewById(R.id.logo);
    }

    public void hideToolbarIcon() {
        if (mImgIcon != null)
            mImgIcon.setVisibility(View.GONE);
    }

    public void showToolbarIcon() {
        if (mImgIcon != null)
            mImgIcon.setVisibility(View.VISIBLE);
    }

    public void showToolabr() {
        if (mToolbar != null)
            mToolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        if (mToolbar != null)
            mToolbar.setVisibility(View.GONE);
    }

    public void setHomeAsUpEnabled(boolean homeAsUpEnabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        }
    }

    protected void setCrossHomeAsUpIcon() {
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_window_close_grey600_24dp);
    }


    public void setToolbarTitle(String title) {
        if (mToolbar != null && title != null && title.length() > 0) {
            mToolbarTitle.setText(title);
            mToolbarTitle.setVisibility(View.VISIBLE);
        }
    }

//    public void setToolbarSubTitle(String subtitle) {
//        if (mToolbar != null && subtitle != null && subtitle.length() > 0) {
//            if (mToolbarSubTitle != null && mToolbarSubTitle.getVisibility() == View.GONE)
//                mToolbarSubTitle.setVisibility(View.VISIBLE);
//            mToolbarSubTitle.setText(subtitle);
//        }
//    }

    protected void setToobarTextColor(int color) {
        mToolbarTitle.setTextColor(color);
    }

    protected void hideToolbarTitle() {
        mToolbarTitle.setVisibility(View.GONE);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected void changeTitle(String title) {
        setToolbarTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
