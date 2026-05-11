package com.frro.bus.ticket.features.fleet.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.fleet.entities.SeatType;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    @Query("SELECT st FROM SeatType st WHERE " +
            "(:name IS NULL OR LOWER(st.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:startUpcharge IS NULL OR st.upcharge >= :startUpcharge) AND " +
            "(:endUpcharge IS NULL OR st.upcharge <= :endUpcharge)")
    List<SeatType> searchSeatTypes(
            @Param("name") String name,
            @Param("startUpcharge") BigDecimal startUpcharge,
            @Param("endUpcharge") BigDecimal endUpcharge);
}
