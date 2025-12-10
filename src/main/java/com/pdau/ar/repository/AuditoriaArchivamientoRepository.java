package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaArchivamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaArchivamientoRepository extends JpaRepository<AuditoriaArchivamiento, Long> {
    List<AuditoriaArchivamiento> findByFechaArchivadoBetween(LocalDateTime inicio, LocalDateTime fin);
    List<AuditoriaArchivamiento> findByAdminId(Long adminId);
}
