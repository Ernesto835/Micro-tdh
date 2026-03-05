package com.microservicio.microserviciopedidos.Feign_Cliente;

import com.microservicio.microserviciopedidos.dto_apis.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "clientes-service",
        url = "http://4.206.202.17:8081"
)
public interface ClienteClient {

    @GetMapping("/api/clientes")
    List<ClienteDTO> obtenerClientes();
}
