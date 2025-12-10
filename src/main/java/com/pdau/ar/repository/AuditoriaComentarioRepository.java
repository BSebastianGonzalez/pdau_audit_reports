package com.pdau.ar.repository;

import com.pdau.ar.model.AuditoriaComentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaComentarioRepository extends JpaRepository<AuditoriaComentario, Long> {
    List<AuditoriaComentario> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
    List<AuditoriaComentario> findByAdminId(Long adminId);
}
