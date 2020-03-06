package com.lanchonete.maida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IPedidoService;

@RestController
@RequestMapping(name = "/v1/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService dao;

	@PostMapping
	public ResponseEntity<Response<Pedido>> salvar(@RequestBody Pedido pedido) {
		Response<Pedido> response = Response.of(dao.salvar(pedido));
		return new ResponseEntity<Response<Pedido>>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public Response<Pedido> alterar(@RequestBody Pedido pedido) {
		return Response.of(dao.salvar(pedido));
	}

	@PatchMapping(value = "/{id}/{status}")
	public ResponseEntity<Response<Object>> alterarStatus(@PathVariable int id, @PathVariable String status) {
		try {
			StatusPedido nStatus = StatusPedido.valueOf(status);
			return ResponseEntity.ok(Response.of(dao.alterarStatus(id, nStatus)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Response.erro("Status inválido"));
		}
	}

	@GetMapping(value = "/{idCliente}/{status}")
	public ResponseEntity<Response<Object>> buscarPorClienteEEstatus(@PathVariable int idCliente,
			@RequestBody StatusPedido status) {
		List<Pedido> list = dao.buscarPorClienteEStatus(idCliente, status);
		return ResponseEntity.ok(Response.of(list));
	}

	@GetMapping(value = "/{idCliente}/list/")
	public ResponseEntity<Response<Object>> buscarPorClienteEEstatus(@PathVariable int idCliente,
			@RequestParam List<StatusPedido> status) {
		System.out.println("Chamado...");
		List<Pedido> list = dao.buscarPorClienteEStatus(idCliente, status);
		return ResponseEntity.ok(Response.of(list));
	}

	// void mostar() {
	/*
	 * dao.buscarPorClienteEStatus(clientId, status); ****
	 * dao.buscarPorClienteEStatus(clientId, status); dao.buscarPorStatus(status);
	 */
	// }

}
