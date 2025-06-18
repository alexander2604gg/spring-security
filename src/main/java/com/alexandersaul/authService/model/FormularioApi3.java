package com.alexandersaul.authService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "formulario_api3")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormularioApi3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer edad;

    private Integer genero; // 0 = Masculino, 1 = Femenino

    @Column(name = "anios_educacion")
    private Integer aniosEducacion;

    private Double global;

    private Integer ef;

    @Column(name = "glucosa_minima")
    private Integer glucosaMinima;

    @Column(name = "hipertension_sistolica")
    private Integer hipertensionSistolica;

    private Integer fazekas;

    @Column(name = "svd_simple_score")
    private Integer svdSimpleScore;

    private Integer ps;

    @Column(name = "colesterol_total")
    private Integer colesterolTotal;

    private Integer fumador; // 0 = No, 1 = Sí

    @Column(name = "numero_lacunes")
    private Integer numeroLacunes;

    @Column(name = "conteo_cmb")
    private Integer conteoCmb;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserSec user;

    private Boolean hasDementia;
}
