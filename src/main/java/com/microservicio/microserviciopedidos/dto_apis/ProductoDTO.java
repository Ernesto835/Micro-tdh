package com.microservicio.microserviciopedidos.dto_apis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    @JsonProperty("id_producto")
    private Long idProducto;

    private String nombre;
    private Double precio;
    private Integer stock;
}