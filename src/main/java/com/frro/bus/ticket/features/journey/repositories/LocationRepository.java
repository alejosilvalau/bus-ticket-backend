package com.frro.bus.ticket.features.journey.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.journey.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT l FROM Location l WHERE " +
            "(:cityName IS NULL OR LOWER(l.cityName) LIKE LOWER(CONCAT('%', :cityName, '%'))) AND " +
            "(:state IS NULL OR LOWER(l.state) LIKE LOWER(CONCAT('%', :state, '%'))) AND " +
            "(:postalCode IS NULL OR l.postalCode = :postalCode)")
    List<Location> searchLocations(
            @Param("cityName") String cityName,
            @Param("state") String state,
            @Param("postalCode") String postalCode);
}
