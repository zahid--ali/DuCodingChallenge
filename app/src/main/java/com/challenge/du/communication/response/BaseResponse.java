package com.challenge.du.communication.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by k.zahid on 12/28/17.
 */

public class BaseResponse {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
