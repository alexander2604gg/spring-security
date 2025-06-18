package com.alexandersaul.authService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "formulario_api1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormularioApi1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer edad;

    private String genero;

    @Column(name = "anios_educacion")
    private Integer aniosEducacion;

    private Double ps;
    private Double ef;
    private Double global;

    private Boolean diabetes;
    private Boolean hipertension;
    private Boolean fumador;
    private Boolean hipercolesterolemia;

    @Column(name = "numero_lacunes")
    private Integer numeroLacunes;

    @Column(name = "conteo_lac")
    private Integer conteoLac;

    @Column(name = "conteo_cmb")
    private Integer conteoCmb;

    @Column(name = "puntaje_svd_simple")
    private Integer puntajeSvdSimple;

    @Column(name = "puntaje_svd_modificado")
    private Integer puntajeSvdModificado;

    @Column(name = "categoria_fazekas")
    private String categoriaFazekas;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id", nullable = false)
    private UserSec user;

    private Boolean hasDementia;
}