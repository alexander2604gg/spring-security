package com.alexandersaul.authService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Api1RequestDTO {
    private int age;
    private int gender;
    private int educationyears;

    @JsonProperty("Global")
    private double global;

    @JsonProperty("EF")
    private double ef;

    @JsonProperty("PS")
    private double ps;

    @JsonProperty("glucose_min")
    private double glucoseMin;

    @JsonProperty("cholesterol_total")
    private double cholesterolTotal;

    @JsonProperty("hypertension_sys")
    private double hypertensionSys;

    private int smoking;

    @JsonProperty("Fazekas")
    private int fazekas;

    @JsonProperty("lacunes_num")
    private int lacunesNum;

    @JsonProperty("SVD_Simple_Score")
    private int svdSimpleScore;

    @JsonProperty("CMB_count")
    private int cmbCount;
}
