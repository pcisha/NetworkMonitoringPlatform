package com.network.data.repository;

import com.network.data.model.NetworkDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface NetworkDetailsRepository extends CrudRepository<NetworkDetails, Long> {

    Optional<NetworkDetails> findById(@NonNull Long id);

    List<NetworkDetails> findAll();

    List<NetworkDetails> findByRegionCountryArea(String regionCountryArea);

    List<NetworkDetails> findByYearDate(Date yearDate);

    List<NetworkDetails> findByPercentageOfIndividualsUsingInternet(Double percentageOfIndividualsUsingInternet);

    List<NetworkDetails> findBySource(String source);

    List<NetworkDetails> findByDnsServer(String dnsServer);

    List<NetworkDetails> findByNetworkStatus(String networkStatus);

    boolean existsById(Long id);

    void deleteById(@NonNull Long id);

    void deleteAll();

    void deleteByRegionCountryArea(String regionCountryArea);

    void deleteByYearDate(Date yearDate);

    void deleteByPercentageOfIndividualsUsingInternet(Double percentageOfIndividualsUsingInternet);

    void deleteBySource(String source);

    void deleteByDnsServer(String dnsServer);

    void deleteByNetworkStatus(String networkStatus);

    void deleteByRegionCountryAreaAndYearDate(String regionCountryArea, Date yearDate);

}
