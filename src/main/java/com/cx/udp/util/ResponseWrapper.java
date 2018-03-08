package com.cx.udp.util;

import java.io.Serializable;

/**
 * Created by cx on 2018-3-7.
 */
public class ResponseWrapper implements Serializable {
    private int responseType;
    private String context;

    public ResponseWrapper(int responseType) {
        this.responseType = responseType;
    }

    public int getResponseType() {
        return responseType;
    }

    public void setResponseType(int responseType) {
        this.responseType = responseType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
