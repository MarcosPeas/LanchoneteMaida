package com.lanchonete.maida.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;

public interface IPedidoService {

	Pedido salvar(Pedido pedido);

	Pedido alterarStatus(Pedido pedido);

	Optional<Pedido> buscarPorId(int id);

	Optional<Pedido> buscarPorIdECliente(int id, int idCliente);

	List<Pedido> buscarPorStatus(Pedido.StatusPedido status);

	List<Pedido> buscarPorStatus(List<Pedido.StatusPedido> statusList);

	List<Pedido> buscarPorClienteEStatus(int clientId, Pedido.StatusPedido status);

	List<Pedido> buscarPorClienteEStatus(int clientId, List<Pedido.StatusPedido> statusList);

	List<Pedido> buscarPedidosFila(List<Pedido.StatusPedido> status, int idPedido);

	List<Pedido> buscarPorDataMaiorOuIgual(LocalDateTime data, List<StatusPedido> statusList);

	List<Pedido> buscarEntreDatas(LocalDateTime inicio, LocalDateTime fim, List<StatusPedido> statusList);

	void deletar(int id);
}
