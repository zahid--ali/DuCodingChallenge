package com.challenge.du.communication.response;

import com.challenge.du.models.ShopModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by k.zahid on 12/28/17.
 */

public class ShopResponse extends BaseResponse {

    @SerializedName("payLoad")
    @Expose
    private List<ShopModel> shops = null;

    public List<ShopModel> getShops() {
        return shops;
    }

    public void setShops(List<ShopModel> shops) {
        this.shops = shops;
    }
}
