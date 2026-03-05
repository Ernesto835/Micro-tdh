package com.microservicio.microserviciopedidos.dto_apis;

import java.util.List;

public class PedidoRequestDTO {

    // 🔹 Cliente al que pertenece el pedido
    private Long clienteId;

    // 🔹 Estado opcional (si no viene, se pondrá CREADO)
    private String estado;

    // 🔹 Lista de detalles del pedido
    private List<DetallePedidoRequestDTO> detalles;

    public PedidoRequestDTO() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetallePedidoRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoRequestDTO> detalles) {
        this.detalles = detalles;
    }
}