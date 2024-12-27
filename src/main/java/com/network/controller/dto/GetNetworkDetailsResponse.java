package com.network.controller.dto;

import com.network.data.model.NetworkDetails;

public class GetNetworkDetailsResponse {

    private Long id;

    private NetworkDetails networkDetails;

    public GetNetworkDetailsResponse() {

    }

    public GetNetworkDetailsResponse(Long id, NetworkDetails networkDetails) {
        this.id = id;
        this.networkDetails = networkDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NetworkDetails getNetworkDetailsResponse() {
        return networkDetails;
    }

    public void setNetworkDetailsResponse(NetworkDetails networkDetails) {
        this.networkDetails = networkDetails;
    }

}
