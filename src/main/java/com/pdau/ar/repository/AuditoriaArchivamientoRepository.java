package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaArchivamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaArchivamientoRepository extends JpaRepository<AuditoriaArchivamiento, Long> {
    long count();
    long countByFechaArchivadoBetween(LocalDateTime start, LocalDateTime end);
    long countByAdminId(Long adminId);
    long countByAdminIdAndFechaArchivadoBetween(Long adminId, LocalDateTime start, LocalDateTime end);
    List<AuditoriaArchivamiento> findByAdminId(Long adminId);
}
