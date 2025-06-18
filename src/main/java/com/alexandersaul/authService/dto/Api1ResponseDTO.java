package com.alexandersaul.authService.dto;

import lombok.Data;

@Data
public class Api1ResponseDTO {
    private double probabilidad_demencia;
    private String riesgo;
    private int prediccion;
    private String status;
}