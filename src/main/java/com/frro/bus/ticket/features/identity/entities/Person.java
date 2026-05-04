package com.frro.bus.ticket.features.identity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.frro.bus.ticket.common.utils.EntityWithId;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "is_user", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Person implements EntityWithId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private boolean isActive = true;
}
