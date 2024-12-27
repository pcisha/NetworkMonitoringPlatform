package com.network.utility;

import com.network.controller.error.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Configuration
public class ControllerUtility {

    private static final Logger logger = LoggerFactory.getLogger(ControllerUtility.class);

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String UNABLE_TO_SERIALIZE_RESPONSE = "Unable to serialize response.";
    private static final String FAILED_TO_CONVERT_OBJECT_TO_JSON = "Failed to convert object to JSON: ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    /**
     * Builds an error response with a specific HTTP status and message.
     *
     * @param httpStatus   The HTTP status to return.
     * @param errorMessage The error message to include in the response.
     * @return ResponseEntity containing an error object.
     */
    public ResponseEntity<Error> buildErrorResponse(HttpStatus httpStatus, String errorMessage) {
        Error error = new Error(httpStatus, errorMessage);
        return new ResponseEntity<>(error, httpStatus);
    }

    /**
     * Converts an object to its JSON representation.
     * Serialize the response body into a JSON string.
     * 
     * @param object The object to serialize.
     * @return JSON string representation of the object.
     */
    public String convertToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(YYYY_MM_DD));
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(FAILED_TO_CONVERT_OBJECT_TO_JSON + " " + object, e.getMessage());
            return UNABLE_TO_SERIALIZE_RESPONSE;
        }
    }

    /**
     * Extracts the Bearer token from the Authorization header.
     * 
     * @param request The HttpServletRequest object containing the request details.
     * @return The Bearer token if found, otherwise null.
     * @see <a href="https://tools.ietf.org/html/rfc6750#section-2.1">The OAuth 2.0
     *      Authorization Framework: Bearer Token Usage</a>
     */
    public String extractBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            return authorizationHeader.substring(7); // Remove "Bearer " prefix
        }
        return null; // No token found
    }
}
