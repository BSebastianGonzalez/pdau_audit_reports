package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaRespuestaApelacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuditoriaRespuestaApelacionRepository extends JpaRepository<AuditoriaRespuestaApelacion, Long> {
    long count();
    long countByFechaRespuestaBetween(Date start, Date end);
    long countByAdminId(Long adminId);
    long countByAdminIdAndFechaRespuestaBetween(Long adminId, Date start, Date end);
    List<AuditoriaRespuestaApelacion> findByAdminId(Long adminId);
}
