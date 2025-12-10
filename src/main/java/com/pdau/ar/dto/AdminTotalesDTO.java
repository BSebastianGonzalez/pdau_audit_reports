package com.pdau.ar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminTotalesDTO {
    public long respuestas;
    public long archivamientos;
    public long comentarios;
    public long respuestasApelacion;
}
