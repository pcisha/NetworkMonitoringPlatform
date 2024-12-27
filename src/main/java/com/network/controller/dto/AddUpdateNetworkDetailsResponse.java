package com.network.controller.dto;

import com.network.data.model.NetworkDetails;

public class AddUpdateNetworkDetailsResponse {

    private Long id;

    private NetworkDetails networkDetails;

    public AddUpdateNetworkDetailsResponse() {
    }

    public AddUpdateNetworkDetailsResponse(Long id, NetworkDetails networkDetails) {
        this.id = id;
        this.networkDetails = networkDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NetworkDetails getNetworkStatus() {
        return networkDetails;
    }

    public void setNetworkStatus(NetworkDetails networkDetails) {
        this.networkDetails = networkDetails;
    }

}
