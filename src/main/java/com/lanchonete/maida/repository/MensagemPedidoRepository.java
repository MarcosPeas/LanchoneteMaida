package com.lanchonete.maida.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lanchonete.maida.model.MensagemPedido;
import com.lanchonete.maida.model.Pedido;

public interface MensagemPedidoRepository extends JpaRepository<MensagemPedido, Integer> {
	List<MensagemPedido> findByPedidoOrderByHorarioDesc(Pedido pedido);

	Optional<MensagemPedido> findByIdAndPedidoOrderByHorarioDesc(int id, Pedido pedido);
}
