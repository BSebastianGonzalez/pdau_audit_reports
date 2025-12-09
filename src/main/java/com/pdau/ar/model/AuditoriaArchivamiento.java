package com.pdau.ar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaArchivamiento {

    @Id
    private Long archivamientoId;

    private Long denunciaId;
    private Long adminId;

    @Column(columnDefinition = "TEXT")
    private String justificacion;

    private LocalDateTime fechaArchivado;
}