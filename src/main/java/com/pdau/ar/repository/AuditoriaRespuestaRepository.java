package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuditoriaRespuestaRepository extends JpaRepository<AuditoriaRespuesta, Long> {
    long count();
    long countByFechaRespuestaBetween(Date start, Date end);
    long countByAdminId(Long adminId);
    long countByAdminIdAndFechaRespuestaBetween(Long adminId, Date start, Date end);
    List<AuditoriaRespuesta> findByAdminId(Long adminId);
}