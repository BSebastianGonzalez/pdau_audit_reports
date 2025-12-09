package com.pdau.ar.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaApelacionAuditEvent {

    private Long respuestaApelacionId;
    private Long apelacionId;
    private Long adminId;
    private String detalle;
    private Date fechaRespuesta;
    private String resultado;
}
