package com.lanchonete.maida.model;

import java.time.LocalTime;
import java.util.List;

import com.lanchonete.maida.model.Pedido.StatusPedido;

import lombok.Getter;
import lombok.Setter;

public class FilaPedido {

	@Getter
	private List<Pedido> pedidos;

	@Getter
	@Setter
	private LocalTime previsaoPreparo;

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
		calcularPrevisaoEntrega();
	}

	private void calcularPrevisaoEntrega() {
		if (pedidos == null || pedidos.isEmpty()) {
			return;
		}
		Integer min = pedidos.stream().mapToInt(p -> {
			if (p.getStatus() == StatusPedido.SOLICITADO) {
				return 14;
			} else if (p.getStatus() == StatusPedido.RECEBIDO) {
				return 12;
			} else if (p.getStatus() == StatusPedido.EM_PREPARO) {
				return 6;
			}
			return 0;
		}).sum();
		previsaoPreparo = LocalTime.now().plusMinutes(min);
	}

}
