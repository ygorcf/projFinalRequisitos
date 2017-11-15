package com.example.ygor.iluminati.tasks;

import java.util.List;

/**
 * Created by Ygor on 14/11/2017.
 */

public abstract class BaseResponse<T> {

    private boolean success;
    private int status;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
