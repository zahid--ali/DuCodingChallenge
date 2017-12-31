package com.challenge.du.communication.response;

import com.challenge.du.models.ShopModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by k.zahid on 12/28/17.
 */

public class NearbyBranchesResponse extends BaseResponse {

    @SerializedName("payLoad")
    @Expose
    private List<ShopModel> branchesList = null;

    public List<ShopModel> getBranchesList() {
        return branchesList;
    }

    public void setBranchesList(List<ShopModel> branchesList) {
        this.branchesList = branchesList;
    }
}
