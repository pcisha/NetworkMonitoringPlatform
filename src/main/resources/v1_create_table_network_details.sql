DROP TABLE IF EXISTS network_details;

CREATE TABLE network_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    region_country_area VARCHAR(255) NOT NULL,
    year_date DATE NOT NULL,
    percentage_of_individuals_using_internet DECIMAL(5, 2) NOT NULL,
    source VARCHAR(255) NOT NULL,
    dns_server VARCHAR(50) NOT NULL,
    network_status VARCHAR(50) NOT NULL
);