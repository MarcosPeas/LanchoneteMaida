package com.lanchonete.maida.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	//List<Pedido> findByStatusOrderByHorarioPedidoDesc(Pedido.StatusPedido status);

	List<Pedido> findByStatusInOrderByHorarioPedidoDesc(List<Pedido.StatusPedido> status);

	List<Pedido> findByStatusInAndHorarioPedidoLessThanEqualOrderByHorarioPedidoDesc(List<Pedido.StatusPedido> status,
			LocalDateTime horarioPedido);

	//List<Pedido> findByStatusAndClienteOrderByHorarioPedidoDesc(Pedido.StatusPedido status, Usuario cliente);

	List<Pedido> findByStatusInAndClienteOrderByHorarioPedidoDesc(List<Pedido.StatusPedido> status, Usuario cliente);

	// buscar pedidos com data de entrega maiores ou iguais a data passada e entre
	// os status passados
	List<Pedido> findByHorarioEntregueGreaterThanEqualAndStatusInOrderByHorarioEntregueDesc(
			LocalDateTime horarioEntregue, List<Pedido.StatusPedido> status);

	// buscar pedidos entre duas datas e entre os status passados
	List<Pedido> findByHorarioEntregueBetweenAndStatusInOrderByHorarioEntregueDesc(LocalDateTime inicio,
			LocalDateTime fim, List<Pedido.StatusPedido> status);

	Optional<Pedido> findByIdAndCliente(int id, Usuario cliente);
}
