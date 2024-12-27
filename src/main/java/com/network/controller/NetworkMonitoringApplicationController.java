package com.network.controller;

import com.network.controller.dto.AddUpdateNetworkDetailsRequest;
import com.network.controller.dto.AddUpdateNetworkDetailsResponse;
import com.network.controller.dto.DeleteNetworkDetailsResponse;
import com.network.controller.dto.GetListOfNetworkDetailsResponse;
import com.network.controller.dto.GetNetworkDetailsResponse;
import com.network.service.NetworkMonitoringService;
import com.network.utility.ControllerUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = EndpointConstants.SERVICE_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NetworkMonitoringApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitoringApplicationController.class);

    private final NetworkMonitoringService networkMonitoringService;
    private final ControllerUtility controllerUtility;

    @Autowired
    public NetworkMonitoringApplicationController(final NetworkMonitoringService networkMonitoringService,
            ControllerUtility controllerUtility) {
        this.networkMonitoringService = networkMonitoringService;
        this.controllerUtility = controllerUtility;
    }

    /**
     * Get network details by ID.
     *
     * @param id The ID of the network.
     * @return ResponseEntity containing the GetNetworkDetailsResponse or an error
     *         message.
     */
    @GetMapping(value = EndpointConstants.V1_GET_NETWORK_DETAILS_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNetworkDetailsById(@PathVariable @NotNull String id,
            HttpServletRequest httpServletRequest) {

        logger.info("Received request to fetch network details with ID: " + id);

        try {
            // Validate ID format if IDd is a String
            long parsedId = Long.parseLong(id);
            if (parsedId <= 0) {
                return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                        EndpointConstants.INVALID_ID + id);
            }

            // Fetch the response using the service layer
            GetNetworkDetailsResponse getNetworkDetailsResponse = networkMonitoringService
                    .getNetworkDetailsById(parsedId);
            if (getNetworkDetailsResponse.getId().equals(0L)) {
                return controllerUtility.buildErrorResponse(HttpStatus.NOT_FOUND,
                        EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_ID + id);
            }

            logger.info(EndpointConstants.NETWORK_DETAILS_FOUND_FOR_ID + id);
            return ResponseEntity.ok(getNetworkDetailsResponse);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_ID + id, e.getMessage());
            return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                    EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_ID + id);
        }
    }

    /**
     * Get details for all the networks.
     *
     * @param request The HTTP request.
     * @return ResponseEntity containing the GetListOfNetworkDetailsResponse or an
     *         error message.
     */
    @GetMapping(value = EndpointConstants.V1_GET_ALL_NETWORK_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNetworkDetails(HttpServletRequest request) {

        logger.info("Received request to fetch details for all the networks...");

        try {
            // Fetch the response using the service layer
            GetListOfNetworkDetailsResponse getListOfNetworkDetailsResponse = networkMonitoringService
                    .getAllNetworkDetails();
            if (getListOfNetworkDetailsResponse.getListOfNetworkDetailsResponse().isEmpty()) {
                return controllerUtility.buildErrorResponse(HttpStatus.NOT_FOUND,
                        EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_GET_ALL);
            }

            logger.info(EndpointConstants.NETWORK_DETAILS_FOUND_FOR_GET_ALL);
            return ResponseEntity.ok(getListOfNetworkDetailsResponse);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_GET_ALL, e.getMessage());
            return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                    EndpointConstants.NETWORK_DETAILS_NOT_FOUND_FOR_GET_ALL);
        }
    }

    @PostMapping(value = EndpointConstants.V1_ADD_NETWORK_DETAILS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNetworkDetails(
            @RequestBody @NotNull AddUpdateNetworkDetailsRequest addUpdateNetworkDetailsRequest,
            HttpServletRequest request) {

        logger.info("Received request to add new network details.");

        try {
            // Add a new record using the service layer
            AddUpdateNetworkDetailsResponse addUpdateNetworkDetailsResponse = networkMonitoringService
                    .addNetworkDetails(addUpdateNetworkDetailsRequest);
            if (addUpdateNetworkDetailsResponse.getId().equals(0L)) {
                return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                        EndpointConstants.NETWORK_DETAILS_NOT_ADDED);
            }

            logger.info(EndpointConstants.NETWORK_DETAILS_ADDED + addUpdateNetworkDetailsResponse.getId());
            return ResponseEntity.ok(addUpdateNetworkDetailsResponse);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_ADDED, e.getMessage());
            return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                    EndpointConstants.NETWORK_DETAILS_NOT_ADDED);
        }
    }

    @PutMapping(value = EndpointConstants.V1_UPDATE_NETWORK_DETAILS_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNetworkDetailsById(@PathVariable @NotNull Long id,
            @RequestBody @NotNull AddUpdateNetworkDetailsRequest addUpdateInternetUsageRequest,
            HttpServletRequest request) {

        logger.info("Received request to update a network detail with ID: " + id);

        try {
            // Update an existing record using the service layer
            AddUpdateNetworkDetailsResponse addUpdateNetworkDetailsResponse = networkMonitoringService
                    .updateNetworkDetails(id, addUpdateInternetUsageRequest);
            if (addUpdateNetworkDetailsResponse.getId().equals(0L)) {
                return controllerUtility.buildErrorResponse(HttpStatus.NOT_FOUND,
                        EndpointConstants.NETWORK_DETAILS_NOT_UPDATED_BY_ID + id);
            }

            logger.info(EndpointConstants.NETWORK_DETAILS_UPDATED_BY_ID + id);
            return ResponseEntity.ok(addUpdateNetworkDetailsResponse);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_UPDATED_BY_ID + id, e.getMessage());
            return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                    EndpointConstants.NETWORK_DETAILS_NOT_UPDATED_BY_ID + id);
        }
    }

    @DeleteMapping(value = EndpointConstants.V1_DELETE_NETWORK_DETAILS_BY_ID, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteNetworkDetailsById(@PathVariable @NotNull Long id, HttpServletRequest request) {

        logger.info("Received request to delete a network detail with ID: " + id);

        try {
            DeleteNetworkDetailsResponse deleteNetworkDetailsResponse = networkMonitoringService
                    .deleteNetworkDetailsById(id);
            if (!deleteNetworkDetailsResponse.getSuccess()) {
                logger.error(EndpointConstants.NETWORK_DETAILS_NOT_DELETED_BY_ID + id);
                return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                        EndpointConstants.NETWORK_DETAILS_NOT_DELETED_BY_ID + id);
            }

            logger.info(EndpointConstants.NETWORK_DETAILS_DELETED_BY_ID + id);
            return ResponseEntity.ok(deleteNetworkDetailsResponse);
        } catch (Exception e) {
            logger.error(EndpointConstants.NETWORK_DETAILS_NOT_DELETED_BY_ID + id, e.getMessage());
            return controllerUtility.buildErrorResponse(HttpStatus.BAD_REQUEST,
                    EndpointConstants.NETWORK_DETAILS_NOT_DELETED_BY_ID + id);
        }
    }

}