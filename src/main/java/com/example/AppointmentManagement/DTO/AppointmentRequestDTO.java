package com.example.AppointmentManagement.DTO;

import com.example.AppointmentManagement.model.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentRequestDTO {
    @JsonProperty("PatientId")
    private Long patientId;
    @JsonProperty("AppointmentDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2025-08-19")
    private LocalDate appointmentDate;

    @JsonProperty("AppointmentTime")
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(example = "14:30:00")
    private LocalTime appointmentTime;

    @JsonProperty("Reason")
    @Schema(example = "Routing check-up")
    private String reason;

    public static Appointment toEntity(AppointmentRequestDTO dto){
        return Appointment.builder()
                .patientId(dto.getPatientId())
                .appointmentDate(dto.getAppointmentDate())
                .appointmentTime(dto.getAppointmentTime())
                .reason(dto.getReason())
                .build();
    }


}
