package com.example.AppointmentManagement.service;

import com.example.AppointmentManagement.DTO.AppointmentRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//@Service
public interface AppointmentServices {
    ResponseEntity<?> create(AppointmentRequestDTO dto);
    ResponseEntity<?> getSingleAppointment(Long appointmentId);
    ResponseEntity<?> getAllAppointment();
    ResponseEntity<?> update(Long appointmentId, AppointmentRequestDTO dto); // full update
    ResponseEntity<?> delete(Long appointmentId);
    ResponseEntity<?> patch(AppointmentRequestDTO dto); // partial update
}
