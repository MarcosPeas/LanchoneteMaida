package com.lanchonete.maida.dao;

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

	public void salvar(Pedido pedido) {
		rep.save(pedido);
	}
	
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
		rep.deleteById(id);
	}
}
