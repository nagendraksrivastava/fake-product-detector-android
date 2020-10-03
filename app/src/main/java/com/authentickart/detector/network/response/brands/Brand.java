package com.authentickart.detector.network.response.brands;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arif on 7/8/17.
 */

public class Brand implements Parcelable {

    @SerializedName("slogan")
    @Expose
    private String slogan;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("discription")
    @Expose
    private String discription;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("id")
    @Expose
    private long id;

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.slogan);
        dest.writeString(this.name);
        dest.writeString(this.discription);
        dest.writeString(this.address);
        dest.writeString(this.imgUrl);
        dest.writeLong(this.id);
    }

    public Brand() {
    }

    protected Brand(Parcel in) {
        this.slogan = in.readString();
        this.name = in.readString();
        this.discription = in.readString();
        this.address = in.readString();
        this.imgUrl = in.readString();
        this.id = in.readLong();
    }

    public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel source) {
            return new Brand(source);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };
}
