package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuditoriaRespuestaRepository extends JpaRepository<AuditoriaRespuesta, Long> {

    List<AuditoriaRespuesta> findByFechaRespuestaBetween(Date inicio, Date fin);
    List<AuditoriaRespuesta> findByAdminId(Long adminId);
}