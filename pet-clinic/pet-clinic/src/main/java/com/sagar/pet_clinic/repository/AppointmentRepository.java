package com.sagar.pet_clinic.repository;

import com.sagar.pet_clinic.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
