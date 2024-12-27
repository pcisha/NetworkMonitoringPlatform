package com.network.service;

import com.network.controller.EndpointConstants;
import com.network.controller.NetworkMonitoringApplicationController;
import com.network.controller.dto.AddUpdateNetworkDetailsRequest;
import com.network.controller.dto.AddUpdateNetworkDetailsResponse;
import com.network.controller.dto.DeleteNetworkDetailsResponse;
import com.network.controller.dto.GetListOfNetworkDetailsResponse;
import com.network.controller.dto.GetNetworkDetailsResponse;
import com.network.data.model.NetworkDetails;
import com.network.data.repository.NetworkDetailsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetworkMonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitoringApplicationController.class);

    private final NetworkDetailsRepository networkDetailsRepository;
    // private final ModelMapper modelMapper;

    @Autowired
    public NetworkMonitoringService(final CsvService csvService,
            final NetworkDetailsRepository networkDetailsRepository) {
        this.networkDetailsRepository = networkDetailsRepository;
        // this.modelMapper = new ModelMapper(); // Initialize ModelMapper
    }

    // Retrieves network details by ID.
    public GetNetworkDetailsResponse getNetworkDetailsById(Long id) {
        try {
            return networkDetailsRepository.findById(id)
                    .map(details -> new GetNetworkDetailsResponse(details.getId(), details))
                    .orElseGet(GetNetworkDetailsResponse::new);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_ID + id, e.getMessage());
            return new GetNetworkDetailsResponse();
        }
    }

    // Retrieves all details of all the networks.
    public GetListOfNetworkDetailsResponse getAllNetworkDetails() {
        try {
            GetListOfNetworkDetailsResponse getListOfNetworkDetailsResponse = new GetListOfNetworkDetailsResponse();
            getListOfNetworkDetailsResponse.getListOfNetworkDetailsResponse()
                    .addAll(networkDetailsRepository.findAll());
            getListOfNetworkDetailsResponse.setSize(getListOfNetworkDetailsResponse.getListOfNetworkDetailsResponse());
            return getListOfNetworkDetailsResponse;
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_GET_ALL, e.getMessage());
            return new GetListOfNetworkDetailsResponse();
        }
    }

    // Add a network detail.
    public AddUpdateNetworkDetailsResponse addNetworkDetails(
            AddUpdateNetworkDetailsRequest addUpdateNetworkDetailsRequest) {
        try {
            NetworkDetails networkDetails = getNetworkDetailsFromRequest(addUpdateNetworkDetailsRequest);
            NetworkDetails savedNetworkDetails = networkDetailsRepository.save(networkDetails);
            return createAddUpdateNetworkDetailsResponse(savedNetworkDetails);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_ADDED, e.getMessage());
            return new AddUpdateNetworkDetailsResponse();
        }
    }

    // Update a network detail.
    public AddUpdateNetworkDetailsResponse updateNetworkDetails(Long id,
            AddUpdateNetworkDetailsRequest addUpdateNetworkDetailsRequest) {
        try {
            if (networkDetailsRepository.existsById(id)) {
                NetworkDetails savedNetworkDetails = networkDetailsRepository
                        .save(updateSavedNetworkDetailsFromRequest(id, addUpdateNetworkDetailsRequest));
                return createAddUpdateNetworkDetailsResponse(savedNetworkDetails);
            } else {
                return addNetworkDetails(addUpdateNetworkDetailsRequest); // Create a new record if ID not found
            }
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_UPDATED_BY_ID + id, e.getMessage());
            return new AddUpdateNetworkDetailsResponse();
        }
    }

    // Delete a network detail by ID.
    public DeleteNetworkDetailsResponse deleteNetworkDetailsById(Long id) {
        DeleteNetworkDetailsResponse deleteNetworkDetailsResponse = new DeleteNetworkDetailsResponse();
        if (networkDetailsRepository.existsById(id)) {
            deleteNetworkDetailsResponse.setId(id);
            try {
                networkDetailsRepository.deleteById(id);
                deleteNetworkDetailsResponse.setSuccess(true);
            } catch (Exception e) {
                logger.error(EndpointConstants.NETWORK_DETAILS_NOT_DELETED_BY_ID + id, e.getMessage());
                deleteNetworkDetailsResponse.setSuccess(false);
            }
        } else {
            logger.warn(EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_ID + id);
            deleteNetworkDetailsResponse.setSuccess(false);
        }
        return deleteNetworkDetailsResponse;
    }

    // Mappers
    private NetworkDetails getNetworkDetailsFromRequest(AddUpdateNetworkDetailsRequest addUpdateNetworkDetailsRequest) {
        return new NetworkDetails(addUpdateNetworkDetailsRequest.getRegionCountryArea(),
                addUpdateNetworkDetailsRequest.getYearDate(),
                addUpdateNetworkDetailsRequest.getPercentageOfIndividualsUsingInternet(),
                addUpdateNetworkDetailsRequest.getSource(),
                addUpdateNetworkDetailsRequest.getDnsServer(),
                addUpdateNetworkDetailsRequest.getNetworkStatus());
    }

    private AddUpdateNetworkDetailsResponse createAddUpdateNetworkDetailsResponse(NetworkDetails savedNetworkDetails) {
        return new AddUpdateNetworkDetailsResponse(savedNetworkDetails.getId(), savedNetworkDetails);
    }

    private NetworkDetails updateSavedNetworkDetailsFromRequest(Long id,
            AddUpdateNetworkDetailsRequest addUpdateNetworkDetailsRequest) {
        NetworkDetails savedNetworkDetails = networkDetailsRepository.findById(id).get();
        savedNetworkDetails.setRegionCountryArea(addUpdateNetworkDetailsRequest.getRegionCountryArea());
        savedNetworkDetails.setYearDate(addUpdateNetworkDetailsRequest.getYearDate());
        savedNetworkDetails.setPercentageOfIndividualsUsingInternet(
                addUpdateNetworkDetailsRequest.getPercentageOfIndividualsUsingInternet());
        savedNetworkDetails.setSource(addUpdateNetworkDetailsRequest.getSource());
        savedNetworkDetails.setDnsServer(addUpdateNetworkDetailsRequest.getDnsServer());
        savedNetworkDetails.setNetworkStatus(addUpdateNetworkDetailsRequest.getNetworkStatus());
        return savedNetworkDetails;
    }

}
