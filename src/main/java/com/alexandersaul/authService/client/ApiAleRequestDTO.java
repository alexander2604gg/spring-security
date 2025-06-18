package com.alexandersaul.authService.client;

import lombok.Data;

@Data
public class ApiAleRequestDTO {
    private int age;
    private String gender;
    private int educationyears;
    private double EF;
    private double PS;
    private double Global;
    private int diabetes;
    private int smoking;
    private int hypertension;
    private int hypercholesterolemia;
    private int lacunes_num;
    private String fazekas_cat;
    private int lac_count;
    private int CMB_count;
    private int SVD_Simple_Score;
    private int SVD_Amended_Score;
}
