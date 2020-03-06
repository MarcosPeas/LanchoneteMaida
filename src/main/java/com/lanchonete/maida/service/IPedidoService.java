package com.lanchonete.maida.service;

import java.util.List;

import com.lanchonete.maida.model.Pedido;

public interface IPedidoService {

	Pedido salvar(Pedido pedido);

	Pedido alterarStatus(int pedidoId, Pedido.StatusPedido status);

	List<Pedido> buscarPorStatus(Pedido.StatusPedido status);

	List<Pedido> buscarPorStatus(List<Pedido.StatusPedido> status);

	List<Pedido> buscarPorClienteEStatus(int clientId, Pedido.StatusPedido status);

	List<Pedido> buscarPorClienteEStatus(int clientId, List<Pedido.StatusPedido> status);

	void deletar(int id);
}
