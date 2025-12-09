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
public class AuditoriaRespuestaApelacion {

    @Id
    private Long respuestaApelacionId;

    private Long apelacionId;
    private Long adminId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;

    @Column(columnDefinition = "TEXT")
    private String detalle;

    private String resultado;
}
