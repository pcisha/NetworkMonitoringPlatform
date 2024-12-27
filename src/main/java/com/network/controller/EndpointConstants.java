package com.network.controller;

public class EndpointConstants {

    // Resource-based URLs. Supports versioning.
    public static final String SERVICE_URL = "/network-monitoring-service/api";
    public static final String V1_ADD_NETWORK_DETAILS = "/v1/network-details"; // POST
    public static final String V1_UPDATE_NETWORK_DETAILS_BY_ID = "/v1/network-details/{id}"; // PUT
    public static final String V1_GET_NETWORK_DETAILS_BY_ID = "/v1/network-details/{id}"; // GET BY ID
    public static final String V1_GET_ALL_NETWORK_DETAILS = "/v1/network-details"; // GET ALL
    public static final String V1_GET_NETWORK_DETAILS_BY_REGION = "/v1/network-details/{region}"; // GET BY REGION
    public static final String V1_GET_NETWORK_DETAILS_BY_SOURCE = "/v1/network-details/{source}"; // GET BY SOURCE
    public static final String V1_DELETE_NETWORK_DETAILS_BY_ID = "/v1/network-details/{id}"; // DELETE BY ID
    public static final String V1_DELETE_ALL_NETWORK_DETAILS = "/v1/network-details"; // DELETE ALL

    // Success messages
    public static final String NETWORK_DETAILS_FOUND_FOR_ID = "Network details found for ID: ";
    public static final String NETWORK_DETAILS_FOUND_FOR_REGION = "Network details found for region: ";
    public static final String NETWORK_DETAILS_FOUND_FOR_SOURCE = "Network details found for source: ";
    public static final String NETWORK_DETAILS_FOUND_FOR_GET_ALL = "Network details available.";
    public static final String NETWORK_DETAILS_ADDED = "Network details added successfully, with ID: ";
    public static final String NETWORK_DETAILS_UPDATED_BY_ID = "Network details updated successfully for ID: ";
    public static final String NETWORK_DETAILS_DELETED_BY_ID = "Network details deleted successfully for ID: ";
    public static final String NETWORK_DETAILS_DELETED_ALL = "All network details deleted successfully.";

    // Error messages
    public static final String INVALID_ID = "Invalid ID. ID must be greater than zero.";
    public static final String NETWORK_DETAILS_NOT_FOUND_FOR_ID = "Network details not found for ID: ";
    public static final String NETWORK_DETAILS_NOT_FOUND_FOR_REGION = "Network details not found for region: ";
    public static final String NETWORK_DETAILS_NOT_FOUND_FOR_SOURCE = "Network details not found for source: ";
    public static final String NETWORK_DETAILS_NOT_FOUND_FOR_GET_ALL = "Network details unavailable.";
    public static final String NETWORK_DETAILS_NOT_ADDED = "Network details cannot be added.";
    public static final String NETWORK_DETAILS_NOT_UPDATED_BY_ID = "Network details cannot be updated for ID: ";
    public static final String NETWORK_DETAILS_NOT_DELETED_BY_ID = "Network details cannot be deleted for ID: ";
    public static final String NETWORK_DETAILS_CANNOT_DELETED_ALL = "All network details cannot be deleted.";

    // HTTP Status codes
    public static final String OK = "200";
    public static final String CREATED = "201";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";
    public static final String NOT_FOUND = "404";
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String SERVICE_UNAVAILABLE = "503";
}
