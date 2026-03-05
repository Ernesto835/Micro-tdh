package com.microservicio.microserviciopedidos.dto_apis;

public class DetallePedidoDTO {

    private Long productoId;
    private Long proveedorId;
    private Integer cantidad;
    private Double precioUnitario;

    // 🔹 Subtotal calculado
    public Double getSubtotal() {
        return cantidad * precioUnitario;
    }

    // Getters y Setters
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
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}