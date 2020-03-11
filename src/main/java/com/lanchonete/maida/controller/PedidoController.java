package com.lanchonete.maida.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.FilaPedido;
import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IPedidoService;

@RestController
@RequestMapping("/v1")
public class PedidoController {

	@Autowired
	private IPedidoService dao;

	@PostMapping(value = "/clientes/{idCliente}/pedidos")
	@PreAuthorize("hasAnyRole('CLIENTE')")
	public ResponseEntity<Response<Pedido>> salvar(@PathVariable int idCliente, @RequestBody Pedido pedido) {
		Response<Pedido> response = Response.of(dao.salvar(pedido));
		return new ResponseEntity<Response<Pedido>>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('GESTOR')")
	@GetMapping(value = "/pedidos/{id}")
	public Response<Pedido> buscarPorId(@PathVariable int id) {
		return Response.of(dao.buscarPorId(id));
	}

	@GetMapping(value = "/clientes/{idCliente}/pedidos/{id}")
	public Response<Pedido> buscarPorIdECliente(@PathVariable int id, @PathVariable int idCliente) {
		return Response.of(dao.buscarPorIdECliente(id, idCliente));
	}

	/*
	 * @PutMapping
	 * 
	 * @PreAuthorize("hasAnyRole('GESTOR')") public Response<Pedido>
	 * alterar(@RequestBody Pedido pedido) { return Response.of(dao.salvar(pedido));
	 * }
	 */

	@PreAuthorize("hasAnyRole('GESTOR')")
	@PatchMapping(value = "/pedidos/{idPedido}/{status}")
	public ResponseEntity<Response<Object>> alterarStatusGestor(@PathVariable int idPedido,
			@PathVariable StatusPedido status) {
		return ResponseEntity.ok(Response.of(dao.alterarStatus(idPedido, status)));
	}

	@PatchMapping(value = "/clientes/{idCliente}/pedidos/{idPedido}/{status}")
	public ResponseEntity<Response<Object>> alterarStatus(@PathVariable int idCliente, @PathVariable int idPedido,
			@PathVariable StatusPedido status) {
		return ResponseEntity.ok(Response.of(dao.alterarStatusPedidoCliente(idPedido, status)));
	}

	@GetMapping(value = "/clientes/{idCliente}/pedidos/status")
	public ResponseEntity<Response<Object>> buscarPorClienteEEstatus(@PathVariable int idCliente,
			@RequestParam List<StatusPedido> status) {
		List<Pedido> list = dao.buscarPorClienteEStatus(idCliente, status);
		return ResponseEntity.ok(Response.of(list));
	}

	@GetMapping(value = "/pedidos/status")
	@PreAuthorize("hasAnyRole('GESTOR')")
	public Response<List<Pedido>> buscarPorStatus(@RequestParam List<StatusPedido> status) {
		return Response.of(dao.buscarPorStatus(status));
	}

	/* SISTEMA DE FILA DE PEDIDOS */
	@GetMapping(value = "/clientes/{idCliente}/pedidos/fila/{id}")
	public Response<FilaPedido> filaPedidos(@PathVariable int idCliente, @PathVariable int id) {
		List<StatusPedido> statusList = Arrays.asList(StatusPedido.SOLICITADO, StatusPedido.RECEBIDO,
				StatusPedido.EM_PREPARO);
		List<Pedido> pedidos = dao.buscarPedidosFila(statusList, id);
		FilaPedido filaInfo = new FilaPedido();
		filaInfo.setPedidos(pedidos);
		return Response.of(filaInfo);
	}

	/* MENSAGENS */

}
