package com.challenge.du.communication.response;

import com.challenge.du.models.SectionModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by k.zahid on 12/28/17.
 */

public class HomeScreenResponse extends BaseResponse {

    @SerializedName("payLoad")
    @Expose
    private List<SectionModel> contentList = null;

    public List<SectionModel> getContentList() {
        return contentList;
    }

    public void setContentList(List<SectionModel> contentList) {
        this.contentList = contentList;
    }
}
