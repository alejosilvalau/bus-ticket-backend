package com.frro.bus.ticket.features.journey.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByCityNameAndStateAndPostalCode(String cityName, String state, String postalCode);
    @Query("SELECT l FROM Location l WHERE " +
            "(:cityName IS NULL OR LOWER(l.cityName) LIKE LOWER(CONCAT('%', :cityName, '%'))) AND " +
            "(:state IS NULL OR LOWER(l.state) LIKE LOWER(CONCAT('%', :state, '%'))) AND " +
            "(:postalCode IS NULL OR LOWER(l.postalCode) LIKE LOWER(CONCAT('%', :postalCode, '%')))")
    List<Location> searchLocations(
            @Param("cityName") String cityName,
            @Param("state") String state,
            @Param("postalCode") String postalCode);

    @Query("SELECT l FROM Location l WHERE " +
            "(:cityName IS NULL OR LOWER(l.cityName) LIKE LOWER(CONCAT('%', :cityName, '%'))) AND " +
            "(:state IS NULL OR LOWER(l.state) LIKE LOWER(CONCAT('%', :state, '%'))) AND " +
            "(:postalCode IS NULL OR LOWER(l.postalCode) LIKE LOWER(CONCAT('%', :postalCode, '%')))")
    Page<Location> searchLocations(
            @Param("cityName") String cityName,
            @Param("state") String state,
            @Param("postalCode") String postalCode,
            Pageable pageable);
}
