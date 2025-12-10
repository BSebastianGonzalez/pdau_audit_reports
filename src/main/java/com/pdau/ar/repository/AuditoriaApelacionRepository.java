package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaApelacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuditoriaApelacionRepository extends JpaRepository<AuditoriaApelacion, Long> {
    List<AuditoriaApelacion> findByFechaApelacionBetween(Date inicio, Date fin);
}
