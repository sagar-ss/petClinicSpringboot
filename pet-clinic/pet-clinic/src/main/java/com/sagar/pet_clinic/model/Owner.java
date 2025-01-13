package com.sagar.pet_clinic.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID
    private Integer id;

    @Column(name = "first_name", nullable = false) // Maps to first_name column
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone" , nullable = false)
    private String telephone;

    // One-to-many relationship (One Owner has Many Pets)
    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Pet> pets;


}
