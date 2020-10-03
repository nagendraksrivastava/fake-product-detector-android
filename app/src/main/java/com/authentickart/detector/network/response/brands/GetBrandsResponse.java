package com.authentickart.detector.network.response.brands;

import com.authentickart.detector.network.response.general.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arif on 7/8/17.
 */

public class GetBrandsResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("brand")
    @Expose
    private List<Brand> brand = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

}
