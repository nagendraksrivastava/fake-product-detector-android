package com.authentickart.detector.network.response.search;

import com.authentickart.detector.network.response.general.Status;

/**
 * Created by arif on 7/8/17.
 */

public class SearchResponse {

    private int product_code;
    private Status status;

    public int getCode() {
        return product_code;
    }

    public void setCode(int code) {
        this.product_code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
