package com.authentickart.detector.features.brands.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.authentickart.detector.R;
import com.authentickart.detector.custom.AuthKartTextView;
import com.authentickart.detector.features.brands.contracts.OnBrandSelectedListener;
import com.authentickart.detector.network.response.brands.Brand;

import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.CategoriesViewHolder> {

    private List<Brand> mBrands;
    private OnBrandSelectedListener mOnBrandSelectedListener;

    public BrandsAdapter(List<Brand> brands, OnBrandSelectedListener onBrandSelectedListener) {
        mBrands = brands;
        mOnBrandSelectedListener = onBrandSelectedListener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_subcategories_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        Brand brand = mBrands.get(position);
        if (brand != null) {
            holder.mTvCategories.setText(brand.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mBrands.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView mCvBrands;
        private AuthKartTextView mTvCategories;

        public CategoriesViewHolder(View base) {
            super(base);
            mCvBrands = (CardView) base.findViewById(R.id.cv_subcategory);
            mTvCategories = (AuthKartTextView) base.findViewById(R.id.tv_subcategory_name);
            mCvBrands.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cv_subcategory:
                    if (mOnBrandSelectedListener != null) {
                        mOnBrandSelectedListener.onBrandSelected(mBrands.get(getAdapterPosition()));
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
