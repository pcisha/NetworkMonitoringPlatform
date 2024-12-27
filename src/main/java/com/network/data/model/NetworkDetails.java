package com.network.data.model;

import com.network.controller.dto.NetworkStatus;

import javax.persistence.*;
import java.sql.Date;
import java.util.Optional;

@Entity
public class NetworkDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "region_country_area", nullable = false)
    private String regionCountryArea;

    @Column(name = "year_date", nullable = false)
    private Date yearDate;

    @Column(name = "percentage_of_individuals_using_internet", nullable = false)
    private Double percentageOfIndividualsUsingInternet;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "dns_server", nullable = false)
    private String dnsServer;

    @Enumerated(EnumType.STRING)
    @Column(name = "network_status", nullable = false)
    private NetworkStatus networkStatus;

    public NetworkDetails() {
    }

    public NetworkDetails(String regionCountryArea, Date yearDate, Double percentageOfIndividualsUsingInternet,
            String source, String dnsServer, NetworkStatus networkStatus) {
        this.regionCountryArea = regionCountryArea;
        this.yearDate = yearDate;
        this.percentageOfIndividualsUsingInternet = percentageOfIndividualsUsingInternet;
        this.source = source;
        this.dnsServer = dnsServer;
        this.networkStatus = networkStatus;
    }

    public NetworkDetails(Long id, String regionCountryArea, Date yearDate, Double percentageOfIndividualsUsingInternet,
            String source, String dnsServer, NetworkStatus networkStatus) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
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

    public Optional<NetworkDetails> map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }

}
