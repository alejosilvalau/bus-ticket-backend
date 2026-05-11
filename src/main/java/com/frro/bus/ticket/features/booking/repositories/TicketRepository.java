package com.frro.bus.ticket.features.booking.repositories;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.booking.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Override
    @EntityGraph(attributePaths = { "user", "trip", "seat" })
    List<Ticket> findAll();

    @Override
    @EntityGraph(attributePaths = { "user", "trip", "seat" })
    Optional<Ticket> findById(Integer id);

    @Query("SELECT t FROM Ticket t WHERE " +
            "(:startFinalPrice IS NULL OR t.finalPrice >= :startFinalPrice) AND " +
            "(:endFinalPrice IS NULL OR t.finalPrice <= :endFinalPrice) AND " +
            "(:startBookingTime IS NULL OR t.bookingTime >= :startBookingTime) AND " +
            "(:endBookingTime IS NULL OR t.bookingTime <= :endBookingTime) AND " +
            "(:isCancelled IS NULL OR t.isCancelled = :isCancelled) AND " +
            "(:token IS NULL OR LOWER(t.token) LIKE LOWER(CONCAT('%', :token, '%'))) AND " +
            "(:idUser IS NULL OR t.user.id = :idUser) AND " +
            "(:idTrip IS NULL OR t.trip.id = :idTrip) AND " +
            "(:idSeat IS NULL OR t.seat.id = :idSeat)")
    @EntityGraph(attributePaths = { "user", "trip", "seat" })
    List<Ticket> searchTickets(
            @Param("startFinalPrice") BigDecimal startFinalPrice,
            @Param("endFinalPrice") BigDecimal endFinalPrice,
            @Param("startBookingTime") ZonedDateTime startBookingTime,
            @Param("endBookingTime") ZonedDateTime endBookingTime,
            @Param("isCancelled") Boolean isCancelled,
            @Param("token") String token,
            @Param("idUser") Integer idUser,
            @Param("idTrip") Integer idTrip,
            @Param("idSeat") Integer idSeat);
}
