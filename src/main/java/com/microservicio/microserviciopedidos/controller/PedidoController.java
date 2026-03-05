package com.microservicio.microserviciopedidos.controller;

import com.microservicio.microserviciopedidos.dto_apis.PedidoRequestDTO;
import com.microservicio.microserviciopedidos.dto_apis.PedidoResponseDTO;
import com.microservicio.microserviciopedidos.entidad.Pedido;
import com.microservicio.microserviciopedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 🔥 =========================
    // 🔹 VISTA HTML (TEMPLATE)
    // 🔥 =========================
    @GetMapping
    public String mostrarPaginaPedidos() {
        return "pedidos"; // templates/pedidos.html
    }

    // 🔥 =========================
    // 🔹 API REST
    // 🔥 =========================

    // LISTAR
    @GetMapping("/api")
    @ResponseBody
    public List<PedidoResponseDTO> listarPedidos() {

        return pedidoService.listarPedidos()
                .stream()
                .map(this::convertirAPedidoResponseDTO)
                .collect(Collectors.toList());
    }

    // OBTENER POR ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<PedidoResponseDTO> obtenerPedido(@PathVariable Long id) {

        Pedido pedido = pedidoService.obtenerPorId(id);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertirAPedidoResponseDTO(pedido));
    }

    // CREAR
    @PostMapping("/api")
    @ResponseBody
    public PedidoResponseDTO crearPedido(@RequestBody PedidoRequestDTO request) {

        Pedido pedido = pedidoService.crearPedido(request);
        return convertirAPedidoResponseDTO(pedido);
    }

    // ACTUALIZAR
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<PedidoResponseDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestBody PedidoRequestDTO request) {

        Pedido pedido = pedidoService.obtenerPorId(id);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        pedido.setEstado(request.getEstado());
        Pedido actualizado = pedidoService.guardarPedido(pedido);

        return ResponseEntity.ok(convertirAPedidoResponseDTO(actualizado));
    }

    // ELIMINAR
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {

        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    // 🔥 CONVERSOR
    private PedidoResponseDTO convertirAPedidoResponseDTO(Pedido pedido) {

        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setPedidoId(pedido.getId());
        dto.setClienteId(pedido.getClienteId());
        dto.setEstado(pedido.getEstado());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setTotal(pedido.getTotal());

        return dto;
    }
}