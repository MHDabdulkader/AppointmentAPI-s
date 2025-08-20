package com.example.AppointmentManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String responseMessage;
    private T responseData;
    public ApiResponse(String responseMessage, T responseData){
        this.responseData = responseData;
        this.responseMessage = responseMessage;

    }
}
