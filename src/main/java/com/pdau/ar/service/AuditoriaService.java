package com.pdau.ar.service;

import com.pdau.ar.dto.AdminTotalesDTO;
import com.pdau.ar.dto.TotalesDTO;
import com.pdau.ar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRespuestaRepository respuestaRepo;
    private final AuditoriaArchivamientoRepository archivamientoRepo;
    private final AuditoriaComentarioRepository comentarioRepo;
    private final AuditoriaApelacionRepository apelacionRepo;
    private final AuditoriaRespuestaApelacionRepository respuestaApelacionRepo;

    public TotalesDTO obtenerTotalesGlobales() {
        long r = respuestaRepo.count();
        long a = archivamientoRepo.count();
        long c = comentarioRepo.count();
        long ap = apelacionRepo.count();
        long ra = respuestaApelacionRepo.count();
        return new TotalesDTO(r, a, c, ap, ra);
    }

    // 2. Totales por rango (start/end son Date o LocalDateTime según campo)
    public TotalesDTO obtenerTotalesPorRango(Date startDateForDateFields, Date endDateForDateFields,
                                             LocalDateTime startLdtForLocalDateTime, LocalDateTime endLdtForLocalDateTime) {
        long r = respuestaRepo.countByFechaRespuestaBetween(startDateForDateFields, endDateForDateFields);
        long a = archivamientoRepo.countByFechaArchivadoBetween(startLdtForLocalDateTime, endLdtForLocalDateTime);
        long c = comentarioRepo.countByFechaCreacionBetween(startLdtForLocalDateTime, endLdtForLocalDateTime);
        long ap = apelacionRepo.countByFechaApelacionBetween(startDateForDateFields, endDateForDateFields);
        long ra = respuestaApelacionRepo.countByFechaRespuestaBetween(startDateForDateFields, endDateForDateFields);
        return new TotalesDTO(r, a, c, ap, ra);
    }

    // 3. Totales por admin (excluye apelaciones)
    public AdminTotalesDTO obtenerTotalesPorAdmin(Long adminId) {
        long r = respuestaRepo.countByAdminId(adminId);
        long a = archivamientoRepo.countByAdminId(adminId);
        long c = comentarioRepo.countByAdminId(adminId);
        long ra = respuestaApelacionRepo.countByAdminId(adminId);
        return new AdminTotalesDTO(r, a, c, ra);
    }

    // 4. Totales por admin y rango
    public AdminTotalesDTO obtenerTotalesPorAdminYRango(Long adminId,
                                                        Date startDateForDateFields, Date endDateForDateFields,
                                                        LocalDateTime startLdtForLocalDateTime, LocalDateTime endLdtForLocalDateTime) {
        long r = respuestaRepo.countByAdminIdAndFechaRespuestaBetween(adminId, startDateForDateFields, endDateForDateFields);
        long a = archivamientoRepo.countByAdminIdAndFechaArchivadoBetween(adminId, startLdtForLocalDateTime, endLdtForLocalDateTime);
        long c = comentarioRepo.countByAdminIdAndFechaCreacionBetween(adminId, startLdtForLocalDateTime, endLdtForLocalDateTime);
        long ra = respuestaApelacionRepo.countByAdminIdAndFechaRespuestaBetween(adminId, startDateForDateFields, endDateForDateFields);
        return new AdminTotalesDTO(r, a, c, ra);
    }

    public static Date parseIsoToDate(String iso) {
        if (iso == null) return null;
        Instant instant = Instant.parse(iso);
        return Date.from(instant);
    }

    public static LocalDateTime parseIsoToLocalDateTime(String iso) {
        if (iso == null) return null;
        return LocalDateTime.parse(iso);
    }
}
