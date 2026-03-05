package com.microservicio.microserviciopedidos.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Relación con Pedido (Asegúrate de que Pedido use @Table(name = "pedidos"))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_PEDIDO"))
    private Pedido pedido;

    // 🔹 Producto (ID del microservicio productos)
    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    // 🔹 Proveedor (ID del microservicio proveedores)
    @Column(name = "proveedor_id")
    private Long proveedorId;

    // 🔹 Cantidad pedida
    @Column(nullable = false)
    private Integer cantidad;

    // 🔹 Precio unitario
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    // 🔹 Subtotal
    @Column(nullable = false)
    private Double subtotal;

    // 🔹 Constructor vacío obligatorio para JPA
    public DetallePedido() {
    }

    // 🔹 Constructor de negocio
    public DetallePedido(Pedido pedido, Long productoId, Long proveedorId, Integer cantidad, Double precioUnitario) {
        this.pedido = pedido;
        this.productoId = productoId;
        this.proveedorId = proveedorId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularSubtotal();
    }

    // 🔥 Lógica de negocio para calcular el subtotal
    public void calcularSubtotal() {
        if (this.cantidad != null && this.precioUnitario != null) {
            this.subtotal = this.cantidad * this.precioUnitario;
        } else {
            this.subtotal = 0.0;
        }
    }

    // 🔹 Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal(); // Recalcula al cambiar cantidad
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularSubtotal(); // Recalcula al cambiar precio
    }

    public Double getSubtotal() {
        return subtotal;
    }

    // Se agrega Setter para evitar problemas de persistencia con algunas versiones de Hibernate
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}