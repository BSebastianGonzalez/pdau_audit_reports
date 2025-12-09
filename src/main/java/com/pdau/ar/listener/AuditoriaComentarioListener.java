package com.pdau.ar.listener;

import com.pdau.ar.config.RabbitConfig;
import com.pdau.ar.event.ComentarioAuditEvent;
import com.pdau.ar.model.AuditoriaComentario;
import com.pdau.ar.repository.AuditoriaComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditoriaComentarioListener {

    private final AuditoriaComentarioRepository comentarioRepo;

    @RabbitListener(queues = RabbitConfig.COMENTARIO_QUEUE)
    public void onComentario(ComentarioAuditEvent event) {

        AuditoriaComentario c = new AuditoriaComentario();
        c.setComentarioId(event.getComentarioId());
        c.setDenunciaId(event.getDenunciaId());
        c.setAdminId(event.getAdminId());
        c.setContenido(event.getContenido());
        c.setFechaCreacion(event.getFechaCreacion());

        comentarioRepo.save(c);
    }
}
