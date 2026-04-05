package com.frro.bus.ticket.user.repository;

import com.frro.bus.ticket.person.repository.PersonRepository;
import com.frro.bus.ticket.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PersonRepository {

  Optional<User> findByEmail(String email);

  List<User> findAllByIsAdminTrue();

  List<User> findAllByIsAdminFalse();

  Long countByIsAdminTrue();
}
