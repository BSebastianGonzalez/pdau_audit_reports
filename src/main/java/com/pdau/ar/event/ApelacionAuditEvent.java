package com.pdau.ar.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApelacionAuditEvent {

    private Long apelacionId;
    private Long denunciaId;
    private String detalle;
    private Date fechaApelacion;
}
