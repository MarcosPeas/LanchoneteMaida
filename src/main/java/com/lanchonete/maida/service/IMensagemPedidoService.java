package com.lanchonete.maida.service;

import java.util.List;

import com.lanchonete.maida.model.MensagemPedido;
import com.lanchonete.maida.model.Pedido;

public interface IMensagemPedidoService {

	MensagemPedido salvar(MensagemPedido mensagem);
	
	MensagemPedido alterar(MensagemPedido mensagem);

	MensagemPedido buscarPorId(int id);
	
	MensagemPedido buscarPorIdEPedido(int id, Pedido pedido);

	List<MensagemPedido> listarPorPedido(Pedido pedido);

	void deletar(int id);
}
