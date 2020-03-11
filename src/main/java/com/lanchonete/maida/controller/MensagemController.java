package com.lanchonete.maida.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.MensagemPedido;
import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IMensagemPedidoService;
import com.lanchonete.maida.service.IPedidoService;

@RestController
@RequestMapping("/v1/pedidos/{pedidoId}/mensagens")
public class MensagemController {
	
	@Autowired
	private IPedidoService pedidoDao;
	
	@Autowired
	private IMensagemPedidoService mensagemDao;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<Object> salvarMensagem(@PathVariable int pedidoId, @RequestBody MensagemPedido mensagem) {
		Pedido pedido = pedidoDao.buscarPorId(pedidoId);
		mensagem.setPedido(pedido);
		MensagemPedido mensagemPedido = mensagemDao.salvar(mensagem);
		pedido.getMensagens().add(mensagemPedido);
		pedidoDao.alterar(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.of(mensagemPedido.getId()));
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<?> alterarMensagem(@PathVariable int pedidoId, @RequestBody MensagemPedido mensagem) {
		Pedido pedido = pedidoDao.buscarPorId(pedidoId);
		mensagem.setPedido(pedido);
		mensagemDao.salvar(mensagem);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> listarMensagem(@PathVariable int pedidoId) {
		Pedido pedido = pedidoDao.buscarPorId(pedidoId);
		List<MensagemPedido> mensagens = mensagemDao.listarPorPedido(pedido);
		return ResponseEntity.ok(Response.of(mensagens));
	}
	
	@GetMapping(value = "/{idMensagem}")
	public ResponseEntity<?> buscarPorId(@PathVariable int pedidoId, @PathVariable int idMensagem) {
		Pedido pedido = pedidoDao.buscarPorId(pedidoId);
		MensagemPedido mensagem = mensagemDao.buscarPorIdEPedido(idMensagem, pedido);
		return ResponseEntity.ok(Response.of(mensagem));
	}

	@DeleteMapping(value = "/{idMensagem}")
	@PreAuthorize("hasAnyRole('GESTOR')")
	public ResponseEntity<Object> deletarMensagem(@PathVariable int pedidoId, @PathVariable int idMensagem) {
		mensagemDao.deletar(idMensagem);
		return ResponseEntity.ok().build();
	}
}
