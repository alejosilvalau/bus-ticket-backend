package com.frro.bus.ticket.user.repository;

import com.frro.bus.ticket.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  List<User> findAllByIsAdminTrue();

  List<User> findAllByIsAdminFalse();

  Long countByIsAdminTrue();
}
