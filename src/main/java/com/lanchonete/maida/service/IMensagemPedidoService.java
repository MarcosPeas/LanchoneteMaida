package com.lanchonete.maida.service;

import java.util.List;
import java.util.Optional;

import com.lanchonete.maida.model.MensagemPedido;
import com.lanchonete.maida.model.Pedido;

public interface IMensagemPedidoService {

	MensagemPedido salvar(MensagemPedido mensagem);

	Optional<MensagemPedido> buscarPorId(int id);

	List<MensagemPedido> listarPorPedido(Pedido pedido);

	void deletar(int id);
}
