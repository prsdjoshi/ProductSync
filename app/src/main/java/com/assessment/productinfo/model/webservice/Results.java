package com.assessment.productinfo.model.webservice;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Results implements Serializable {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("success") public String status;
    @SerializedName("message") public String msg;

}
