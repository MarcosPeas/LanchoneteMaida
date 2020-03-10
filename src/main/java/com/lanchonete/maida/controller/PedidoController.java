package com.lanchonete.maida.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.FilaPedido;
import com.lanchonete.maida.model.MensagemPedido;
import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IMensagemPedidoService;
import com.lanchonete.maida.service.IPedidoService;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService dao;

	@Autowired
	private IMensagemPedidoService mensagemDao;

	@PostMapping
	@PreAuthorize("hasAnyRole('CLIENTE')")
	public ResponseEntity<Response<Pedido>> salvar(@RequestBody Pedido pedido) {
		Response<Pedido> response = Response.of(dao.salvar(pedido));
		return new ResponseEntity<Response<Pedido>>(response, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public Response<Pedido> buscarPorId(@PathVariable int id) {
		return Response.of(dao.buscarPorId(id));
	}

	@GetMapping(value = "/{id}/cliente/{idCliente}")
	public Response<Pedido> buscarPorIdECliente(@PathVariable int id, @PathVariable int idCliente) {
		return Response.of(dao.buscarPorIdECliente(id, idCliente));
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public Response<Pedido> alterar(@RequestBody Pedido pedido) {
		return Response.of(dao.salvar(pedido));
	}

	@PatchMapping
	public ResponseEntity<Response<Object>> alterarStatus(@RequestBody Pedido pedido) {
		return ResponseEntity.ok(Response.of(dao.alterarStatus(pedido)));
	}

	@GetMapping(value = "/{idCliente}/{status}")
	public ResponseEntity<Response<Object>> buscarPorClienteEEstatus(@PathVariable int idCliente,
			@RequestBody StatusPedido status) {
		List<Pedido> list = dao.buscarPorClienteEStatus(idCliente, status);
		return ResponseEntity.ok(Response.of(list));
	}

	@GetMapping(value = "/{idCliente}/list/{status}")
	public ResponseEntity<Response<Object>> buscarPorClienteEEstatus(@PathVariable int idCliente,
			@RequestParam List<StatusPedido> status) {
		List<Pedido> list = dao.buscarPorClienteEStatus(idCliente, status);
		return ResponseEntity.ok(Response.of(list));
	}

	@GetMapping(value = "/status/{status}")
	public Response<List<Pedido>> buscarPorStatus(@RequestParam StatusPedido status) {
		return Response.of(dao.buscarPorStatus(status));
	}

	@GetMapping(value = "/status/list")
	@PreAuthorize("hasAnyRole('CLIENTE')")
	public Response<List<Pedido>> buscarPorStatus(@RequestParam List<StatusPedido> status) {
		return Response.of(dao.buscarPorStatus(status));
	}

	@DeleteMapping(value = "/{id}")
	public BodyBuilder deletar(@PathVariable int id) {
		dao.deletar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT);
	}

	/* SISTEMA DE FILA DE PEDIDOS */
	@GetMapping(value = "/fila/{id}")
	@PreAuthorize("hasAnyRole('CLIENTE')")
	public Response<FilaPedido> filaPedidos(@PathVariable int id) {
		List<StatusPedido> statusList = Arrays.asList(StatusPedido.SOLICITADO, StatusPedido.RECEBIDO,
				StatusPedido.EM_PREPARO);
		List<Pedido> pedidos = dao.buscarPedidosFila(statusList, id);
		FilaPedido filaInfo = new FilaPedido();
		filaInfo.setPedidos(pedidos);
		return Response.of(filaInfo);
	}

	/* MENSAGENS */

	@PostMapping(value = "/{pedidoId}/mensagens")
	public ResponseEntity<Object> salvarMensagem(@PathVariable int pedidoId, @RequestBody MensagemPedido mensagem) {
		MensagemPedido pedido = mensagemDao.salvar(mensagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.of(pedido.getId()));
	}

	@PutMapping(value = "/{pedidoId}/mensagens")
	public ResponseEntity<?> alterarMensagem(@PathVariable int pedidoId, @RequestBody MensagemPedido mensagem) {
		mensagemDao.salvar(mensagem);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{pedidoId}/mensagens")
	public ResponseEntity<Object> listarMensagem(@PathVariable int pedidoId) {
		Pedido pedido = dao.buscarPorId(pedidoId);
		List<MensagemPedido> mensagens = mensagemDao.listarPorPedido(pedido);
		return ResponseEntity.ok(mensagens);
	}

	@DeleteMapping(value = "/{pedidoId}/mensagens/{idMensagem}")
	public ResponseEntity<Object> deletarMensagem(@PathVariable int pedidoId, @PathVariable int idMensagem) {
		mensagemDao.deletar(idMensagem);
		return ResponseEntity.ok().build();
	}

}
