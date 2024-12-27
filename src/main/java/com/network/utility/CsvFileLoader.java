package com.network.utility;

import com.network.controller.NetworkMonitoringApplicationController;
import com.network.service.CsvService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// CommandLineRunner bean to handle the startup tasks. This will execute after
// the Spring application context is initialized.
@Component
public class CsvFileLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitoringApplicationController.class);

    private final CsvService csvService;

    @Autowired
    public CsvFileLoader(CsvService csvService) {
        this.csvService = csvService;
    }

    /**
     * Loads the CSV file and processes it on application startup.
     */
    @Override
    public void run(String... args) {
        int BATCH_SIZE = 500; // Adjust batch size based on performance requirements
        final String NETWORK_DETAILS_CSV_FILEPATH = "src/main/resources/network_details.csv";

        logger.info("Starting CSV File parsing...");
        String filePath = NETWORK_DETAILS_CSV_FILEPATH;
        csvService.parseNetworkDetailsCsv(filePath, BATCH_SIZE);
        logger.info("CSV File processing completed.");
    }

}
