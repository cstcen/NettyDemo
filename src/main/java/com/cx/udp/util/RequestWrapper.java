package com.cx.udp.util;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by cx on 2018-3-7.
 */
public class RequestWrapper implements Serializable {

    private int requestType;
    private String context;
    private Map<String, Object> operMap;

    public RequestWrapper(int requestType) {
        this.requestType = requestType;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Map<String, Object> getOperMap() {
        return operMap;
    }

    public void setOperMap(Map<String, Object> operMap) {
        this.operMap = operMap;
    }
}
