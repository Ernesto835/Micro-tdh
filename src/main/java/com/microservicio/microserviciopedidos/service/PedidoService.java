package com.microservicio.microserviciopedidos.service;

import com.microservicio.microserviciopedidos.dto_apis.DetallePedidoRequestDTO;
import com.microservicio.microserviciopedidos.dto_apis.PedidoRequestDTO;
import com.microservicio.microserviciopedidos.entidad.DetallePedido;
import com.microservicio.microserviciopedidos.entidad.Pedido;
import com.microservicio.microserviciopedidos.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // 🔥 CREAR PEDIDO (AHORA DEVUELVE ENTIDAD)
    @Transactional
    public Pedido crearPedido(PedidoRequestDTO request) {

        Pedido pedido = new Pedido();
        pedido.setClienteId(request.getClienteId());
        pedido.setEstado(
                request.getEstado() != null ? request.getEstado() : "CREADO"
        );
        pedido.setFechaPedido(LocalDateTime.now());

        List<DetallePedido> detalles = new ArrayList<>();

        if (request.getDetalles() != null) {

            for (DetallePedidoRequestDTO detalleDTO : request.getDetalles()) {

                DetallePedido detalle = new DetallePedido();

                detalle.setProductoId(detalleDTO.getProductoId());
                detalle.setProveedorId(detalleDTO.getProveedorId());
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());

                detalle.setPedido(pedido);

                detalles.add(detalle);
            }
        }

        pedido.setDetalles(detalles);

        // 🔥 recalcula total del pedido
        pedido.calcularTotal();

        return pedidoRepository.save(pedido);
    }

    // 🔹 LISTAR TODOS
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // 🔹 OBTENER POR ID
    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    // 🔹 ELIMINAR
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    // 🔹 GUARDAR (para update)
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}