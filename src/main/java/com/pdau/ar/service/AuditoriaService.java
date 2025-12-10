package com.pdau.ar.service;

import com.pdau.ar.dto.AdminTotalesDTO;
import com.pdau.ar.dto.TotalesDTO;
import com.pdau.ar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public TotalesDTO obtenerTotalesPorRango(Date startDate, Date endDate) {
        LocalDateTime startLdt = toLocal(startDate);
        LocalDateTime endLdt = toLocal(endDate);

        long r = respuestaRepo.countByFechaRespuestaBetween(startDate, endDate);
        long ap = apelacionRepo.countByFechaApelacionBetween(startDate, endDate);
        long ra = respuestaApelacionRepo.countByFechaRespuestaBetween(startDate, endDate);

        long a = archivamientoRepo.countByFechaArchivadoBetween(startLdt, endLdt);
        long c = comentarioRepo.countByFechaCreacionBetween(startLdt, endLdt);

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
    public AdminTotalesDTO obtenerTotalesPorAdminYRango(Long adminId, Date startDate, Date endDate) {
        LocalDateTime startLdt = toLocal(startDate);
        LocalDateTime endLdt = toLocal(endDate);

        long r = respuestaRepo.countByAdminIdAndFechaRespuestaBetween(adminId, startDate, endDate);
        long ra = respuestaApelacionRepo.countByAdminIdAndFechaRespuestaBetween(adminId, startDate, endDate);

        long a = archivamientoRepo.countByAdminIdAndFechaArchivadoBetween(adminId, startLdt, endLdt);
        long c = comentarioRepo.countByAdminIdAndFechaCreacionBetween(adminId, startLdt, endLdt);

        return new AdminTotalesDTO(r, a, c, ra);
    }

    private LocalDateTime toLocal(Date d) {
        return d.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
