package com.challenge.du.communication.response;

import com.challenge.du.models.BranchModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by k.zahid on 12/28/17.
 */

public class NearbyBranchesResponse extends BaseResponse {

    @SerializedName("payLoad")
    @Expose
    private List<BranchModel> branchesList = null;

    public List<BranchModel> getBranchesList() {
        return branchesList;
    }

    public void setBranchesList(List<BranchModel> branchesList) {
        this.branchesList = branchesList;
    }
}
