package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaComentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaComentarioRepository extends JpaRepository<AuditoriaComentario, Long> {
    long count();
    long countByFechaCreacionBetween(LocalDateTime start, LocalDateTime end);
    long countByAdminId(Long adminId);
    long countByAdminIdAndFechaCreacionBetween(Long adminId, LocalDateTime start, LocalDateTime end);
    List<AuditoriaComentario> findByAdminId(Long adminId);
}
