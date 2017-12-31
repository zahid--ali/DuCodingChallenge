package com.challenge.du.communication.response;

import com.challenge.du.models.SectionModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by k.zahid on 12/28/17.
 */

public class SectionResponse extends BaseResponse {

    @SerializedName("payLoad")
    @Expose
    private List<SectionModel> sections = null;


    public List<SectionModel> getSections() {
        return sections;
    }

    public void setSections(List<SectionModel> sections) {
        this.sections = sections;
    }
}
