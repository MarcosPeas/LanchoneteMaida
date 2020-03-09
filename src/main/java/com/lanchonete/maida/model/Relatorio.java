package com.lanchonete.maida.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lanchonete.maida.model.Pedido.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {

	private List<Pedido> pedidos;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private RelatorioTipo tipo;

	public Relatorio descrever() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		double total = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(relatorioTipo(tipo)).append("\n").append("Referência: de ").append(dataInicio.format(formatter))
				.append(" a ").append(dataFim.format(formatter)).append("\n\n");

		Map<StatusPedido, List<Pedido>> map = pedidos.stream().collect(Collectors.groupingBy(p -> {
			return p.getStatus();
		}));

		List<Pedido> listPFinali = map.get(StatusPedido.FINALIZADO);
		List<Pedido> listPCancel = map.get(StatusPedido.CANCELADO);

		if (listPFinali != null) {
			sb.append("Pedidos finalizados: ").append(listPFinali.size()).append("\n\n");
			total = listPFinali.stream().mapToDouble(p -> {
				return p.getValor().doubleValue();
			}).sum();
		}

		if (listPCancel != null) {
			sb.append("Pedidos cancelados: ").append(listPCancel.size()).append("\n\n");
		}

		sb.append("Valor total de vendas(R$): ").append(total);

		descricao = sb.toString();
		return this;
	}

	private String relatorioTipo(Relatorio.RelatorioTipo tipo) {
		switch (tipo) {
		case DIARIO:
			return "Relatório diário";
		case MENSAL:
			return "Relatório mensal";
		case ANUAL:
			return "Relatório anual";
		default:
			return "Relatório entre datas personalizadas";
		}
	}

	public enum RelatorioTipo {
		DIARIO, MENSAL, ANUAL, ENTRE_DATAS
	}

}
