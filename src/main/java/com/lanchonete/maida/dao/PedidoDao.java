package com.lanchonete.maida.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.exceptions.ConstraintViolationImpl;
import com.lanchonete.maida.exceptions.ResourceNotFoundException;
import com.lanchonete.maida.model.ItemPedido;
import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.model.Produto;
import com.lanchonete.maida.model.Usuario;
import com.lanchonete.maida.repository.ItemPedidoRepository;
import com.lanchonete.maida.repository.PedidoRepository;
import com.lanchonete.maida.service.IPedidoService;
import com.lanchonete.maida.service.IProdutoService;

@Service
public class PedidoDao implements IPedidoService {

	@Autowired
	private PedidoRepository rep;

	@Autowired
	private ItemPedidoRepository iRep;

	@Autowired
	private IProdutoService produtoService;

	public Pedido buscarPorId(int id) {
		Optional<Pedido> optional = rep.findById(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("pedido não encontrado");
		}
		return optional.get();
	}

	/*@Override
	public List<Pedido> buscarPorStatus(StatusPedido status) {
		return rep.findByStatusOrderByHorarioPedidoDesc(status);
	}*/

	@Override
	public List<Pedido> buscarPorStatus(List<StatusPedido> status) {
		return rep.findByStatusInOrderByHorarioPedidoDesc(status);
	}

	/*@Override
	public List<Pedido> buscarPorClienteEStatus(int clientId, StatusPedido status) {
		return rep.findByStatusAndClienteOrderByHorarioPedidoDesc(status,
				new Usuario(clientId, null, null, null, null, null, null));
	}*/

	@Override
	public List<Pedido> buscarPorClienteEStatus(int clientId, List<StatusPedido> status) {
		return rep.findByStatusInAndClienteOrderByHorarioPedidoDesc(status,
				new Usuario(clientId, null, null, null, null, null, null));
	}

	public void deletar(int id) {
		rep.delete(buscarPorId(id));
	}

	@Override
	public Pedido salvar(Pedido pedido) {
		List<Integer> ids = pedido.getItensPedido().stream().map(new Function<ItemPedido, Integer>() {
			@Override
			public Integer apply(ItemPedido t) {
				return t.getProduto().getId();
			}
		}).collect(Collectors.toList());
		List<Produto> list = produtoService.buscarPorIds(ids);
		list.forEach(p -> {
			if (!p.isDisponivel()) {
				throw ConstraintViolationImpl.of("Produto indisponível", p.getTitulo() + " está indiponível", p)
						.getViolationException();
			}
		});
		iRep.saveAll(pedido.getItensPedido());
		return rep.save(pedido);
	}

	@Override
	public Pedido alterar(Pedido pedido) {
		iRep.saveAll(pedido.getItensPedido());
		return rep.save(pedido);
	}

	@Override
	public Pedido alterarStatus(Pedido pedido) {
		Pedido pedidoA = buscarPorId(pedido.getId());
		StatusPedido st = pedidoA.getStatus();
		if (podeAlterarStatus(pedido.getStatus(), st)) {
			return salvar(pedido);
		}
		String message = "Não é possível alterar os status do pedido";
		String temMessage = "messageTemplete";
		throw ConstraintViolationImpl.of(message, temMessage, pedido).getViolationException();
	}

	@Override
	public Pedido buscarPorIdECliente(int id, int idCliente) {
		 Optional<Pedido> optional = rep.findByIdAndCliente(id, new Usuario(idCliente, null, null, null, null, null, null));
		 if(optional.isEmpty()) {
			 throw new ResourceNotFoundException("pedido não encontrado");
		 }
		 return optional.get();
	}

	@Override
	public List<Pedido> buscarPedidosFila(List<StatusPedido> status, int idPedido) {
		Pedido pedido = buscarPorId(idPedido);
			return rep.findByStatusInAndHorarioPedidoLessThanEqualOrderByHorarioPedidoDesc(status,
					pedido.getHorarioPedido());
	}

	@Override
	public List<Pedido> buscarPorDataMaiorOuIgual(LocalDateTime data, List<StatusPedido> statusList) {
		return rep.findByHorarioEntregueGreaterThanEqualAndStatusInOrderByHorarioEntregueDesc(data, statusList);
	}

	@Override
	public List<Pedido> buscarEntreDatas(LocalDateTime inicio, LocalDateTime fim, List<StatusPedido> statusList) {
		return rep.findByHorarioEntregueBetweenAndStatusInOrderByHorarioEntregueDesc(inicio, fim, statusList);
	}

	private boolean podeAlterarStatus(StatusPedido novo, StatusPedido antigo) {
		// SOLICITADO, RECEBIDO, EM_PREPARO, RECUSADO, CANCELADO, EM_ENTREGA, FINALIZADO
		if (novo == StatusPedido.CANCELADO) {
			if (antigo == StatusPedido.SOLICITADO || antigo == StatusPedido.RECEBIDO
					|| antigo == StatusPedido.RECUSADO) {
				return true;
			}
		}
		if (novo == StatusPedido.SOLICITADO && antigo == StatusPedido.RECUSADO) {
			return true;
		}
		return false;
	}
}
