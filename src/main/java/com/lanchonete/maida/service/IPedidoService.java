package com.lanchonete.maida.service;

import java.util.List;

import com.lanchonete.maida.model.Pedido;

public interface IPedidoService {

	List<Pedido> buscarPorStatus(Pedido.StatusPedido status);
	
	List<Pedido> buscarPorStatus(List<Pedido.StatusPedido> status);

	List<Pedido> buscarPorClienteEStatus(int clientId, Pedido.StatusPedido status);

	List<Pedido> buscarPorClienteEStatus(int clientId, List<Pedido.StatusPedido> status);
}
