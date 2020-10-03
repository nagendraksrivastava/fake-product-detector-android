package com.authentickart.detector.features.subcategories.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.authentickart.detector.R;
import com.authentickart.detector.custom.AuthKartTextView;
import com.authentickart.detector.features.subcategories.contracts.OnSubcategoryClickListener;
import com.authentickart.detector.network.response.subcategories.Subcategory;

import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.CategoriesViewHolder> {

    private List<Subcategory> mSubCategories;
    private OnSubcategoryClickListener mOnSubcategoryClickListener;

    public SubCategoriesAdapter(List<Subcategory> categories, OnSubcategoryClickListener onSubcategoryClickListener) {
        mSubCategories = categories;
        mOnSubcategoryClickListener = onSubcategoryClickListener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_subcategories_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        Subcategory category = mSubCategories.get(position);
        if (category != null) {
            holder.mTvCategories.setText(category.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mSubCategories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView mCVSubCategory;
        private AuthKartTextView mTvCategories;

        public CategoriesViewHolder(View base) {
            super(base);
            mCVSubCategory = (CardView) base.findViewById(R.id.cv_subcategory);
            mCVSubCategory.setOnClickListener(this);
            mTvCategories = (AuthKartTextView) base.findViewById(R.id.tv_subcategory_name);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cv_subcategory:
                    if (mOnSubcategoryClickListener != null) {
                        mOnSubcategoryClickListener.onSubCategoryClicked(mSubCategories.get(getAdapterPosition()).getId());
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
