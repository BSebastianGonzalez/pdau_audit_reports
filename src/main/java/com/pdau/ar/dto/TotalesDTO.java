package com.pdau.ar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalesDTO {
    public long respuestas;
    public long archivamientos;
    public long comentarios;
    public long apelaciones;
    public long respuestasApelacion;
}
