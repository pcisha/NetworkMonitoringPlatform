package com.network.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.network.data.model.NetworkDetails;

public class GetListOfNetworkDetailsResponse {

    List<NetworkDetails> listOfNetworkDetails = new ArrayList<>();

    int size = 0;

    public GetListOfNetworkDetailsResponse() {
        this.listOfNetworkDetails = new ArrayList<>();
        this.size = 0;
    }

    public GetListOfNetworkDetailsResponse(List<NetworkDetails> listOfNetworkDetails) {
        this.listOfNetworkDetails = listOfNetworkDetails;
        this.size = listOfNetworkDetails.size();
    }

    public List<NetworkDetails> getListOfNetworkDetailsResponse() {
        return listOfNetworkDetails;
    }

    public void setListOfNetworkDetailsResponse(List<NetworkDetails> listOfNetworkDetails) {
        this.listOfNetworkDetails = listOfNetworkDetails;
    }

    public int getSize() {
        return size;
    }

    public void setSize(List<NetworkDetails> listOfNetworkDetails) {
        this.size = listOfNetworkDetails.size();
    }

}
