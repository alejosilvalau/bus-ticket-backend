package com.frro.bus.ticket.user.repository;

import com.frro.bus.ticket.user.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
