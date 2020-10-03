package com.authentickart.detector.network.response.categories;

import com.authentickart.detector.network.response.general.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arif on 6/8/17.
 */

public class GetCategoriesResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
