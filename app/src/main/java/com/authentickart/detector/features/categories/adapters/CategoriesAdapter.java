package com.authentickart.detector.features.categories.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.authentickart.detector.R;
import com.authentickart.detector.custom.AuthKartTextView;
import com.authentickart.detector.features.categories.contracts.OnCategoryClickListener;
import com.authentickart.detector.network.response.categories.Category;

import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> mCategories;
    private OnCategoryClickListener mOnCategoryClickListener;

    public CategoriesAdapter(List<Category> categories, OnCategoryClickListener onCategoryClickListener) {
        mCategories = categories;
        mOnCategoryClickListener = onCategoryClickListener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_categories_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        Category category = mCategories.get(position);
        if (category != null) {
            holder.mTvCategories.setText(category.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView mCvCategories;
        private AuthKartTextView mTvCategories;

        public CategoriesViewHolder(View base) {
            super(base);
            mCvCategories = (CardView) base.findViewById(R.id.cv_category);
            mTvCategories = (AuthKartTextView) base.findViewById(R.id.tv_category_name);
            mCvCategories.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cv_category:
                    if (mOnCategoryClickListener != null) {
                        mOnCategoryClickListener.onCategoryClicked(mCategories.get(getAdapterPosition()).getId());
                    }
                    break;
            }
        }
    }
}
