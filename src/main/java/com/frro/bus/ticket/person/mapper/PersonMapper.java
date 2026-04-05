package com.frro.bus.ticket.person.mapper;

import com.frro.bus.ticket.person.dto.PersonResponse;
import com.frro.bus.ticket.person.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

  PersonResponse toPersonResponse(Person person);

  Person toPerson(PersonResponse personResponse);

}
