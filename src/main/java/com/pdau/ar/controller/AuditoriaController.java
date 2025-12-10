package com.pdau.ar.controller;


import com.pdau.ar.dto.AdminTotalesDTO;
import com.pdau.ar.dto.TotalesDTO;
import com.pdau.ar.model.*;
import com.pdau.ar.repository.*;
import com.pdau.ar.service.AuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService service;
    private final AuditoriaRespuestaRepository respuestaRepo;
    private final AuditoriaArchivamientoRepository archivamientoRepo;
    private final AuditoriaComentarioRepository comentarioRepo;
    private final AuditoriaApelacionRepository apelacionRepo;
    private final AuditoriaRespuestaApelacionRepository respuestaApelacionRepo;

    // 1. Totales globales por tipo
    @GetMapping("/totales")
    public ResponseEntity<TotalesDTO> getTotalesGlobales() {
        return ResponseEntity.ok(service.obtenerTotalesGlobales());
    }

    // 2. Totales por rango de fechas.
    // Query params: start,end en ISO. IMPORTANTE: Para campos Date (java.util.Date) se espera ISO instant (con Z o offset).
    // Para LocalDateTime campos (sin offset) se espera formato 2025-12-01T00:00:00
    @GetMapping("/totales/rango")
    public ResponseEntity<TotalesDTO> getTotalesPorRango(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        // Para campos java.util.Date
        Date startDateForDateFields = AuditoriaService.parseIsoToDate(start);
        Date endDateForDateFields = AuditoriaService.parseIsoToDate(end);

        // Para campos LocalDateTime
        LocalDateTime startLdt = AuditoriaService.parseIsoToLocalDateTime(start);
        LocalDateTime endLdt = AuditoriaService.parseIsoToLocalDateTime(end);

        TotalesDTO dto = service.obtenerTotalesPorRango(startDateForDateFields, endDateForDateFields, startLdt, endLdt);
        return ResponseEntity.ok(dto);
    }

    // 3. Totales por admin (excluye apelaciones)
    @GetMapping("/admin/{adminId}/totales")
    public ResponseEntity<AdminTotalesDTO> getTotalesPorAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(service.obtenerTotalesPorAdmin(adminId));
    }

    // 4. Totales por admin y rango
    @GetMapping("/admin/{adminId}/totales/rango")
    public ResponseEntity<AdminTotalesDTO> getTotalesPorAdminYRango(
            @PathVariable Long adminId,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        Date startDateForDateFields = AuditoriaService.parseIsoToDate(start);
        Date endDateForDateFields = AuditoriaService.parseIsoToDate(end);
        LocalDateTime startLdt = AuditoriaService.parseIsoToLocalDateTime(start);
        LocalDateTime endLdt = AuditoriaService.parseIsoToLocalDateTime(end);

        AdminTotalesDTO dto = service.obtenerTotalesPorAdminYRango(adminId, startDateForDateFields, endDateForDateFields, startLdt, endLdt);
        return ResponseEntity.ok(dto);
    }

    // 5. Endpoints para retornar acciones (no contar) por admin.
    // Respuestas por admin
    @GetMapping("/admin/{adminId}/respuestas")
    public ResponseEntity<List<AuditoriaRespuesta>> getRespuestasPorAdmin(@PathVariable Long adminId) {
        List<AuditoriaRespuesta> list = respuestaRepo.findByAdminId(adminId);
        return ResponseEntity.ok(list);
    }

    // Archivamientos por admin
    @GetMapping("/admin/{adminId}/archivamientos")
    public ResponseEntity<List<AuditoriaArchivamiento>> getArchivamientosPorAdmin(@PathVariable Long adminId) {
        List<AuditoriaArchivamiento> list = archivamientoRepo.findByAdminId(adminId);
        return ResponseEntity.ok(list);
    }

    // Comentarios por admin
    @GetMapping("/admin/{adminId}/comentarios")
    public ResponseEntity<List<AuditoriaComentario>> getComentariosPorAdmin(@PathVariable Long adminId) {
        List<AuditoriaComentario> list = comentarioRepo.findByAdminId(adminId);
        return ResponseEntity.ok(list);
    }

    // Respuestas a apelaciones por admin
    @GetMapping("/admin/{adminId}/respuestas-apelacion")
    public ResponseEntity<List<AuditoriaRespuestaApelacion>> getRespuestasApelacionPorAdmin(@PathVariable Long adminId) {
        List<AuditoriaRespuestaApelacion> list = respuestaApelacionRepo.findByAdminId(adminId);
        return ResponseEntity.ok(list);
    }

    // Si quieres tambien recuperar apelaciones (aunque indicas que no tienen admin) puedes
    @GetMapping("/apelaciones")
    public ResponseEntity<List<AuditoriaApelacion>> getApelaciones() {
        return ResponseEntity.ok(apelacionRepo.findAll());
    }
}
