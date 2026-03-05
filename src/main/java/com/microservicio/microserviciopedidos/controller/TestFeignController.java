package com.microservicio.microserviciopedidos.controller;

import com.microservicio.microserviciopedidos.Feign_Cliente.ClienteClient;
import com.microservicio.microserviciopedidos.Feign_Cliente.ProductoClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestFeignController {

    private final ClienteClient clienteClient;
    private final ProductoClient productoClient;

    public TestFeignController(
            ClienteClient clienteClient,
            ProductoClient productoClient
    ) {
        this.clienteClient = clienteClient;
        this.productoClient = productoClient;
    }

    @GetMapping("/clientes")
    public Object testClientes() {
        return clienteClient.obtenerClientes();
    }

    @GetMapping("/productos")
    public Object testProductos() {
        return productoClient.obtenerProductos();
    }
}