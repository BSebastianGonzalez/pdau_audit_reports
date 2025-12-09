package com.pdau.ar.listener;

import com.pdau.ar.event.RespuestaAuditEvent;
import com.pdau.ar.model.AuditoriaRespuesta;
import com.pdau.ar.repository.AuditoriaRespuestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditoriaRespuestaListener {

    private final AuditoriaRespuestaRepository repository;

    @RabbitListener(queues = "auditoria.respuesta.queue")
    public void onRespuestaAudit(RespuestaAuditEvent event) {

        AuditoriaRespuesta a = new AuditoriaRespuesta();
        a.setRespuestaId(event.getRespuestaId());
        a.setDenunciaId(event.getDenunciaId());
        a.setAdminId(event.getAdminId());
        a.setDetalle(event.getDetalle());
        a.setFechaRespuesta(event.getFechaRespuesta());

        repository.save(a);
    }
}

