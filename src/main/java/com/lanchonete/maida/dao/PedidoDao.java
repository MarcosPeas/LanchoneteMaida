package com.lanchonete.maida.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.repository.PedidoRepository;
import com.lanchonete.maida.service.IPedidoService;

@Service
public class PedidoDao implements IPedidoService {

	@Autowired
	private PedidoRepository rep;

	public Optional<Pedido> buscarPorId(int id) {
		return rep.findById(id);
	}

	@Override
	public List<Pedido> buscarPorStatus(StatusPedido status) {
		return rep.findByStatusOrderByHorarioPedidoDesc(status);
	}

	@Override
	public List<Pedido> buscarPorStatus(List<StatusPedido> status) {
		return rep.findByStatusInOrderByHorarioPedidoDesc(status);
	}

	@Override
	public List<Pedido> buscarPorClienteEStatus(int clientId, StatusPedido status) {
		return rep.findByStatusAndClienteOrderByHorarioPedidoDesc(status,
				new Usuario(clientId, null, null, null, null, null, null));
	}

	@Override
	public List<Pedido> buscarPorClienteEStatus(int clientId, List<StatusPedido> status) {
		return rep.findByStatusInAndClienteOrderByHorarioPedidoDesc(status,
				new Usuario(clientId, null, null, null, null, null, null));
	}

	public void deletar(int id) {
		// verificar se o pedido pode ser deletado
		rep.deleteById(id);
	}

	@Override
	public Pedido salvar(Pedido pedido) {
		return rep.save(pedido);
	}

	@Override
	public Pedido alterarStatus(Pedido pedido) {
		// Verificar se o pedido pode ser alterado
		return salvar(pedido);
	}

	@Override
	public Optional<Pedido> buscarPorIdECliente(int id, int idCliente) {
		return rep.findByIdAndCliente(id, new Usuario(idCliente, null, null, null, null, null, null));
	}

	@Override
	public List<Pedido> buscarPedidosFila(List<StatusPedido> status, int idPedido) {
		Optional<Pedido> optional = buscarPorId(idPedido);
		if (optional.isPresent()) {
			return rep.findByStatusInAndHorarioPedidoLessThanEqualOrderByHorarioPedidoDesc(status,
					optional.get().getHorarioPedido());
		}
		return null;
	}

	@Override
	public List<Pedido> buscarPorDataMaiorOuIgual(LocalDateTime data, List<StatusPedido> statusList) {
		return rep.findByHorarioEntregueGreaterThanEqualAndStatusInOrderByHorarioEntregueDesc(data, statusList);
	}

	@Override
	public List<Pedido> buscarEntreDatas(LocalDateTime inicio, LocalDateTime fim, List<StatusPedido> statusList) {
		return rep.findByHorarioEntregueBetweenAndStatusInOrderByHorarioEntregueDesc(inicio, fim, statusList);
	}
}
