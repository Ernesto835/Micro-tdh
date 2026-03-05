package com.microservicio.microserviciopedidos.dto_apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String cedula;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
}