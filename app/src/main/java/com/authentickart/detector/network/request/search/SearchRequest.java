package com.authentickart.detector.network.request.search;

/**
 * Created by arif on 7/8/17.
 */

public class SearchRequest {

    private String subcategory_id;
    private String brand_id;
    private String barcode;
    private String serial_no;

    public String getSubcategoryId() {
        return subcategory_id;
    }

    public void setSubcategoryId(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getBrandId() {
        return brand_id;
    }

    public void setBrandId(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSerialNo() {
        return serial_no;
    }

    public void setSerialNo(String serial_no) {
        this.serial_no = serial_no;
    }
}
