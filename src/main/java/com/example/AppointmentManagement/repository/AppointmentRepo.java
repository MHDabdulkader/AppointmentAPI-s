package com.example.AppointmentManagement.repository;

import com.example.AppointmentManagement.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    @Query("Select a from Appointment a " +
            "where a.patientId= :patientId " +
            "AND a.appointmentDate= :appointmentDate " +
            "AND a.appointmentTime= :appointmentTime")
    Optional<Appointment> findByPatientAndDateTime(@Param("patientId") Long patientId,
                                                   @Param("appointmentDate") LocalDate appointmentDate,
                                                   @Param("appointmentTime") LocalTime appointmentTime);
}
