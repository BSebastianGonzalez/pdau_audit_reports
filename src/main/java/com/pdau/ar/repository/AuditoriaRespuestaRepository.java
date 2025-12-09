package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaRespuestaRepository extends JpaRepository<AuditoriaRespuesta, Long> {

    List<AuditoriaRespuesta> findByAdminId(Long adminId);

    long countByAdminId(Long adminId);
}