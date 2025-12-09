package com.pdau.ar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaApelacion {

    @Id
    private Long apelacionId;

    private Long denunciaId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApelacion;

    @Column(columnDefinition = "TEXT")
    private String detalle;
}