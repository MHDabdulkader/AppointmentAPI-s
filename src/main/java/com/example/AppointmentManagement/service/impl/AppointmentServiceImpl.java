package com.example.AppointmentManagement.service.impl;

import com.example.AppointmentManagement.DTO.AppointmentRequestDTO;
import com.example.AppointmentManagement.DTO.AppointmentResponseDTO;
import com.example.AppointmentManagement.model.Appointment;
import com.example.AppointmentManagement.repository.AppointmentRepo;
import com.example.AppointmentManagement.service.AppointmentServices;
import com.example.AppointmentManagement.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentServices {
    private final AppointmentRepo appointmentRepo;
//    private final Appointment appointment;
    @Override
    public ResponseEntity<?> create(AppointmentRequestDTO dto) {
        Optional<?> existing = appointmentRepo.findByPatientAndDateTime(dto.getPatientId(), dto.getAppointmentDate(), dto.getAppointmentTime());

        if(existing.isPresent()){
            ApiResponse<AppointmentResponseDTO> response = new ApiResponse<>("Patient already has an appointment at this time", null);
            return ResponseEntity.status(400).body(response);
        }

        Appointment appointment = AppointmentRequestDTO.toEntity(dto);
        Appointment saved = appointmentRepo.save(appointment);
        AppointmentResponseDTO responseDTO = AppointmentResponseDTO.toDto(saved);
        ApiResponse<AppointmentResponseDTO> response = new ApiResponse<>("Appointment created successfully", responseDTO);

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<?> getSingleAppointment(Long appointmentId) {
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        if(appointment.isEmpty()){
            ApiResponse<AppointmentResponseDTO> response = new ApiResponse<>("Appointment did not find", null);
            return ResponseEntity.status(404).body(response);
        }
        AppointmentResponseDTO responseDTO = AppointmentResponseDTO.toDto(appointment.get());
        ApiResponse<AppointmentResponseDTO> response = new ApiResponse<>("Appointment find successfully", responseDTO);
        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<?> getAllAppointment() {
        List<AppointmentResponseDTO> responseDTOS = appointmentRepo.findAll().stream()
                .map(AppointmentResponseDTO::toDto)
                .toList();

        ApiResponse<List<AppointmentResponseDTO>> response = new ApiResponse<>("All appointment list", responseDTOS);
        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<?> update(Long appointmentId, AppointmentRequestDTO dto) {

        Appointment UpdateAppointment = AppointmentRequestDTO.toEntity(dto);
        Optional<Appointment> existing = appointmentRepo.findById(appointmentId);

        if(existing.isEmpty()){
            ApiResponse<AppointmentResponseDTO> response = new ApiResponse<>("Appointment is not find to change", null);
            return ResponseEntity.status(404).body(response);
        }

        Appointment previous = existing.get();
        System.out.println("=============== previous ==========" + previous);
        System.out.println("================= updated ===========" + UpdateAppointment);
        if((UpdateAppointment.getAppointmentDate().equals(previous.getAppointmentDate()) )
                && (UpdateAppointment.getAppointmentTime().equals(previous.getAppointmentTime()))
                && UpdateAppointment.getReason().equals(previous.getReason())){
            AppointmentResponseDTO responseDTO = AppointmentResponseDTO.toDto(previous);
            ApiResponse<AppointmentResponseDTO> response = new ApiResponse<>("Appointment nothing to updated", responseDTO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        previous.setAppointmentDate(UpdateAppointment.getAppointmentDate());
        previous.setAppointmentTime(UpdateAppointment.getAppointmentTime());
        previous.setReason(UpdateAppointment.getReason());
        previous.setPatientId(UpdateAppointment.getPatientId());

        Appointment saved = appointmentRepo.save(previous);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Appointment Updated Successfully", AppointmentResponseDTO.toDto(saved)));
    }

    @Override
    public ResponseEntity<?> delete(Long appointmentId) {
        Optional<Appointment> existing = appointmentRepo.findById(appointmentId);
        if(existing.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Appointment Not founded: Delete Appointment", null));
        }
        Appointment appointment = existing.get();
        appointmentRepo.delete(appointment);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> patch(AppointmentRequestDTO dto) {
        return null;
    }
}
