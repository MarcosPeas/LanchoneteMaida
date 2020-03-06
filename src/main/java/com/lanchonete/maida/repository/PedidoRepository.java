package com.lanchonete.maida.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByStatusOrderByHorarioPedidoDesc(Pedido.StatusPedido status);

	List<Pedido> findByStatusInOrderByHorarioPedidoDesc(List<Pedido.StatusPedido> status);

	List<Pedido> findByStatusAndClienteOrderByHorarioPedidoDesc(Pedido.StatusPedido status, Usuario cliente);

	List<Pedido> findByStatusInAndClienteOrderByHorarioPedidoDesc(List<Pedido.StatusPedido> status, Usuario cliente);
}
