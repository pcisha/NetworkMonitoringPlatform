package com.network.controller.dto;

public class DeleteNetworkDetailsResponse {

    private Long id;

    private boolean success;

    public DeleteNetworkDetailsResponse() {
    }

    public DeleteNetworkDetailsResponse(Long id, boolean success) {
        this.id = id;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
