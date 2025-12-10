package com.pdau.ar.listener;

import com.pdau.ar.config.RabbitConfig;
import com.pdau.ar.event.ArchivamientoAuditEvent;
import com.pdau.ar.model.AuditoriaArchivamiento;
import com.pdau.ar.repository.AuditoriaArchivamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditoriaArchivamientoListener {

    private final AuditoriaArchivamientoRepository archivamientoRepo;

    @RabbitListener(queues = RabbitConfig.ARCHIVAMIENTO_QUEUE)
    public void onArchivamiento(ArchivamientoAuditEvent event) {

        AuditoriaArchivamiento a = new AuditoriaArchivamiento();
        a.setArchivamientoId(event.getArchivamientoId());
        a.setDenunciaId(event.getDenunciaId());
        a.setAdminId(event.getAdminId());
        a.setJustificacion(event.getJustificacion());
        a.setFechaArchivado(event.getFechaArchivado());

        archivamientoRepo.save(a);
    }
}
