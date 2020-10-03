package com.authentickart.detector.network.response.subcategories;

import com.authentickart.detector.network.response.general.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public class GetSubCategoryResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("subcategory")
    @Expose
    private List<Subcategory> subcategory = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }
}
