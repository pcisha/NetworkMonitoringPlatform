package com.network.service;

import com.network.controller.NetworkMonitoringApplicationController;
import com.network.controller.dto.NetworkStatus;
import com.network.data.model.NetworkDetails;
import com.network.data.repository.NetworkDetailsRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvService {

    private static final String SUCCESSFULLY_PROCESSED = "Successfully processed ";

    private static final Logger logger = LoggerFactory.getLogger(NetworkMonitoringApplicationController.class);

    private final NetworkDetailsRepository networkDetailsRepository;

    public CsvService(final NetworkDetailsRepository networkDetailsRepository) {
        this.networkDetailsRepository = networkDetailsRepository;
    }

    /**
     * Parses a large CSV file and inserts data into the database in batches.
     *
     * @param filePath  Path to the CSV file to be processed.
     * @param batchSize Number of records to insert per batch.
     */
    public void parseNetworkDetailsCsv(String filePath, int batchSize) {
        final String FROM = " from ";
        final String ERROR = " - Error: ";
        final String ERROR_READING_CSV_FILE = "Error reading CSV file: ";
        final String SKIPPING_INVALID_ROW = "Skipping invalid row: ";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<NetworkDetails> listOfNetworkDetails = new ArrayList<>();
            String[] line;
            int rowCount = 0;

            // Skip header row (first line)
            reader.readNext();

            // Read and process each row
            while ((line = reader.readNext()) != null) {
                try {
                    // Parse each CSV row into an NetworkDetails object
                    NetworkDetails networkDetails = new NetworkDetails(Long.parseLong(line[0]), // ID
                            line[1], // Region/Country/Area
                            Date.valueOf(line[2]), // Year/Date (yyyy-MM-dd)
                            Double.parseDouble(line[3]), // Percentage of individuals using the Internet
                            line[4], // Source
                            line[5], // DNS Server
                            NetworkStatus.valueOf(line[6]) // Network Status
                    );

                    listOfNetworkDetails.add(networkDetails);
                    rowCount++;

                    // Save data in batches
                    if (rowCount % batchSize == 0) {
                        networkDetailsRepository.saveAll(listOfNetworkDetails);
                        listOfNetworkDetails.clear(); // Clear the list for the next batch
                    }
                } catch (Exception e) {
                    // Log and skip invalid rows
                    logger.error(SKIPPING_INVALID_ROW + Arrays.toString(line) + ERROR + e.getMessage());
                }
            }

            // Save any remaining rows that didn't complete a batch
            if (!listOfNetworkDetails.isEmpty()) {
                networkDetailsRepository.saveAll(listOfNetworkDetails);
            }

            logger.info(SUCCESSFULLY_PROCESSED + rowCount + FROM + filePath);
        } catch (IOException | CsvException e) {
            logger.error(ERROR_READING_CSV_FILE + e.getMessage());
        }
    }

    /**
     * Exports data from the database to a CSV file.
     * Example: /src/main/resources/network_details_from_database.csv
     * 
     * @param filePath Path to the CSV file to be created.
     * @throws IOException
     */
    public void exportToCsv(String filePath) throws IOException {
        final String ERROR_WRITING_CSV_FILE = "Error writing CSV file: ";
        final String DATABASE = " records from the database to ";

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            final String[] header = { "id", "Region/Country/Area", "Year/Date",
                    "Percentage of individuals using the Internet", "Source", "DNS", "Network Status" };
            List<NetworkDetails> listOfNetworkDetails = (List<NetworkDetails>) networkDetailsRepository.findAll();

            writer.writeNext(header);

            for (NetworkDetails networkDetails : listOfNetworkDetails) {
                writer.writeNext(new String[] {
                        networkDetails.getId().toString(),
                        networkDetails.getRegionCountryArea(),
                        networkDetails.getYearDate().toString(),
                        networkDetails.getPercentageOfIndividualsUsingInternet().toString(),
                        networkDetails.getSource(),
                        networkDetails.getDnsServer(),
                        networkDetails.getNetworkStatus().toString()
                });
            }

            logger.info(SUCCESSFULLY_PROCESSED + listOfNetworkDetails.size() + DATABASE + filePath);
        } catch (IOException e) {
            logger.error(ERROR_WRITING_CSV_FILE + e.getMessage());
        }
    }

}