package com.network.controller.dto;

import java.sql.Date;

public class AddUpdateNetworkDetailsRequest {

    private Long id;

    private String regionCountryArea;

    private Date yearDate;

    private Double percentageOfIndividualsUsingInternet;

    private String source;

    private String dnsServer;

    private NetworkStatus networkStatus;

    public AddUpdateNetworkDetailsRequest() {
    }

    public AddUpdateNetworkDetailsRequest(String regionCountryArea, Date yearDate,
            Double percentageOfIndividualsUsingInternet,
            String source, String dnsServer, NetworkStatus networkStatus) {
        this.regionCountryArea = regionCountryArea;
        this.yearDate = yearDate;
        this.percentageOfIndividualsUsingInternet = percentageOfIndividualsUsingInternet;
        this.source = source;
        this.dnsServer = dnsServer;
        this.networkStatus = networkStatus;
    }

    public Long getId() {
        return id;
    }

    public String getRegionCountryArea() {
        return regionCountryArea;
    }

    public void setRegionCountryArea(String regionCountryArea) {
        this.regionCountryArea = regionCountryArea;
    }

    public Date getYearDate() {
        return yearDate;
    }

    public void setYearDate(Date yearDate) {
        this.yearDate = yearDate;
    }

    public Double getPercentageOfIndividualsUsingInternet() {
        return percentageOfIndividualsUsingInternet;
    }

    public void setPercentageOfIndividualsUsingInternet(Double percentageOfIndividualsUsingInternet) {
        this.percentageOfIndividualsUsingInternet = percentageOfIndividualsUsingInternet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDnsServer() {
        return dnsServer;
    }

    public void setDnsServer(String dnsServer) {
        this.dnsServer = dnsServer;
    }

    public NetworkStatus getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(NetworkStatus networkStatus) {
        this.networkStatus = networkStatus;
    }

}
