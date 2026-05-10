package com.frro.bus.ticket.features.identity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.identity.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Query("SELECT d FROM Driver d WHERE " +
            "(:firstName IS NULL OR LOWER(d.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:isActive IS NULL OR d.isActive = :isActive) AND " +
            "(:licenseNumber IS NULL OR d.licenseNumber = :licenseNumber) AND " +
            "(:phoneNumber IS NULL OR d.phoneNumber = :phoneNumber)")
    @EntityGraph(attributePaths = { "trips" })
    List<Driver> searchDrivers(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("isActive") Boolean isActive,
            @Param("licenseNumber") String licenseNumber,
            @Param("phoneNumber") String phoneNumber);
}
