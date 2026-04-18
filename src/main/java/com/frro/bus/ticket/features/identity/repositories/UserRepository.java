package com.frro.bus.ticket.features.identity.repositories;

import org.springframework.stereotype.Repository;

import com.frro.bus.ticket.features.identity.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findById(int id);
}
