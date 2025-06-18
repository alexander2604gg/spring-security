package com.alexandersaul.authService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "formulario_api2")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormularioApi2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer edad;

    private String genero;

    @Column(name = "anios_educacion")
    private Integer aniosEducacion;

    @Column(name = "glucosa_minima")
    private Double glucosaMinima;

    @Column(name = "colesterol_total")
    private Double colesterolTotal;

    private Double ps;
    private Double ef;
    private Double global;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserSec user;

    private Boolean hasDementia;
}
