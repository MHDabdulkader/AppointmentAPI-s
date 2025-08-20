package com.example.AppointmentManagement.controller;

import com.example.AppointmentManagement.DTO.AppointmentRequestDTO;
import com.example.AppointmentManagement.service.AppointmentServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/appointment")
@RequiredArgsConstructor

@Tag(name = "Appointment", description = "APIs for managing appointments")
public class AppointmentController {
    private final AppointmentServices services;

    @Operation(summary = "Create a new appointment",
        description = "Create a new appointment with given details",
            responses = {
                @ApiResponse(responseCode = "201", description = "Appointment Created Successfully"
                   // content = @Content(schema = @Schema(implementation = AppointmentRequestDTO.class))
                ),
                    @ApiResponse(responseCode = "400", description = "Invalid input")

            }
    )
    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDTO dto){
        return services.create(dto);
    }

    @Operation(summary = "Get appointment by ID",
            description = "Fetches a single appointment by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Appointment retrieved successfully",
                            content = @Content(schema = @Schema(implementation = AppointmentRequestDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
            })
    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAnAppointment(
            @Parameter(description = "ID of the appointment to retrieve", required = true)
            @PathVariable("appointmentId") Long appointmentId){
        return services.getSingleAppointment(appointmentId);
    }

    @Operation(summary = "Get all appointments",
            description = "Fetches a list of all appointments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of appointments retrieved",
                            content = @Content(schema = @Schema(implementation = AppointmentRequestDTO.class)))
            })
    @GetMapping("/")
    public ResponseEntity<?> getAllAppointment(){
        return services.getAllAppointment();
    }

    @Operation(summary = "Update an appointment",
            description = "Updates an existing appointment by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Appointment updated successfully",
                            content = @Content(schema = @Schema(implementation = AppointmentRequestDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Nothing to update or invalid input", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
            })
    @PutMapping("/{appointmentId}")
    public ResponseEntity<?> updateAppointment(
            @Parameter(description = "ID of the appointment to update", required = true)
            @PathVariable("appointmentId") Long appointmentId,
            @RequestBody AppointmentRequestDTO dto){
        return services.update(appointmentId, dto);
    }

    @Operation(summary = "Delete an appointment",
            description = "Deletes an appointment by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Appointment deleted successfully", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
            })
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(
            @Parameter(description = "ID of the appointment to delete", required = true)
            @PathVariable("appointmentId") Long appointmentId){
        return services.delete(appointmentId);
    }
}
