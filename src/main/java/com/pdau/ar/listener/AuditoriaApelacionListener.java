package com.pdau.ar.listener;

import com.pdau.ar.config.RabbitConfig;
import com.pdau.ar.event.ApelacionAuditEvent;
import com.pdau.ar.event.RespuestaApelacionAuditEvent;
import com.pdau.ar.model.AuditoriaApelacion;
import com.pdau.ar.model.AuditoriaRespuestaApelacion;
import com.pdau.ar.repository.AuditoriaApelacionRepository;
import com.pdau.ar.repository.AuditoriaRespuestaApelacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditoriaApelacionListener {

    private final AuditoriaApelacionRepository apelacionRepo;
    private final AuditoriaRespuestaApelacionRepository respuestaRepo;

    @RabbitListener(queues = RabbitConfig.APELACION_QUEUE)
    public void onApelacionCreada(ApelacionAuditEvent event) {

        AuditoriaApelacion a = new AuditoriaApelacion();
        a.setApelacionId(event.getApelacionId());
        a.setDenunciaId(event.getDenunciaId());
        a.setFechaApelacion(event.getFechaApelacion());
        a.setDetalle(event.getDetalle());

        apelacionRepo.save(a);
    }

    @RabbitListener(queues = RabbitConfig.RESPUESTA_APELACION_QUEUE)
    public void onRespuestaApelacion(RespuestaApelacionAuditEvent event) {

        AuditoriaRespuestaApelacion r = new AuditoriaRespuestaApelacion();
        r.setRespuestaApelacionId(event.getRespuestaApelacionId());
        r.setApelacionId(event.getApelacionId());
        r.setAdminId(event.getAdminId());
        r.setFechaRespuesta(event.getFechaRespuesta());
        r.setDetalle(event.getDetalle());
        r.setResultado(event.getResultado());

        respuestaRepo.save(r);
    }
}

