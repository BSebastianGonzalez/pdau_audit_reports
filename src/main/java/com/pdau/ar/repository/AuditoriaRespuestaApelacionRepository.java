package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaRespuestaApelacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuditoriaRespuestaApelacionRepository extends JpaRepository<AuditoriaRespuestaApelacion, Long> {
    List<AuditoriaRespuestaApelacion> findByFechaRespuestaBetween(Date inicio, Date fin);
    List<AuditoriaRespuestaApelacion> findByAdminId(Long adminId);
}
