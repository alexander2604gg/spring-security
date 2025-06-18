package com.alexandersaul.authService.dto;


import lombok.Data;

import java.util.List;

@Data
public class ApiAleResponseDTO {
    private String risk;
    private double probability;
    private String confidence;
    private List<String> features_used;
}