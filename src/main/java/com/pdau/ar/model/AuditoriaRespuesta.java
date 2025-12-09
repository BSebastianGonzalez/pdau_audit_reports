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
public class AuditoriaRespuesta {

    @Id
    private Long respuestaId;

    private Long denunciaId;
    private Long adminId;

    @Column(columnDefinition = "TEXT")
    private String detalle;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
}
