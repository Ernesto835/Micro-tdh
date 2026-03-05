package com.microservicio.microserviciopedidos.dto_apis;

public class DetallePedidoRequestDTO {

    // 🔹 ID del producto
    private Long productoId;

    // 🔹 ID del proveedor (opcional si lo manejas)
    private Long proveedorId;

    // 🔹 Cantidad solicitada
    private Integer cantidad;

    // 🔹 Precio unitario del producto
    private Double precioUnitario;

    // 🔹 Constructor vacío
    public DetallePedidoRequestDTO() {
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
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}