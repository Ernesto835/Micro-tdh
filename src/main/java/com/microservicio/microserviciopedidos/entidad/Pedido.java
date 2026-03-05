package com.microservicio.microserviciopedidos.entidad;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 ID del cliente (referencia al microservicio cliente)
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    // 🔹 Estado del pedido (CREADO, PAGADO, ENVIADO, etc.)
    @Column(length = 50, nullable = false)
    private String estado;

    // 🔹 Fecha y hora del pedido
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    // 🔹 Total del pedido (suma de subtotales)
    @Column(nullable = false)
    private Double total;

    // 🔹 Un pedido tiene muchos detalles
    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DetallePedido> detalles = new java.util.ArrayList<>();

    // 🔹 Constructor vacío (JPA)
    public Pedido() {
    }

    // 🔹 Método de negocio: calcular total
    public void calcularTotal() {
        if (detalles == null || detalles.isEmpty()) {
            this.total = 0.0;
            return;
        }
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    // 🔹 Getters y Setters

    public Long getId() {
        return id;
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

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}