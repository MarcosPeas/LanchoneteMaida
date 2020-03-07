package com.lanchonete.maida.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.response.Response;
import com.lanchonete.maida.service.IPedidoService;
import com.lanchonete.maida.util.Relatorio;
import com.lanchonete.maida.util.Relatorio.RelatorioTipo;

@RestController("/v1/relatorio")
public class RelatorioController {

	@Autowired
	private IPedidoService dao;

	@GetMapping(value = "/diario")
	public Response<Relatorio> relatorioDiario() {
		LocalDateTime data = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		List<StatusPedido> statusList = Arrays.asList(StatusPedido.CANCELADO, StatusPedido.FINALIZADO);
		List<Pedido> pedidos = dao.buscarPorDataMaiorOuIgual(data, statusList);
		Relatorio relatorio = new Relatorio(pedidos, "", data, LocalDateTime.now(), RelatorioTipo.DIARIO);
		return Response.of(relatorio.descrever());
	}

	@GetMapping(value = "/mensal")
	public Response<Relatorio> relatorioMensal() {
		LocalDateTime data = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
		List<StatusPedido> statusList = Arrays.asList(StatusPedido.CANCELADO, StatusPedido.FINALIZADO);
		List<Pedido> pedidos = dao.buscarPorDataMaiorOuIgual(data, statusList);
		Relatorio relatorio = new Relatorio(pedidos, "", data, LocalDateTime.now(), RelatorioTipo.MENSAL);
		return Response.of(relatorio.descrever());
	}

	@GetMapping(value = "/anual")
	public Response<Relatorio> relatorioAnual() {
		LocalDateTime data = LocalDateTime.now().withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0)
				.withNano(0);
		List<StatusPedido> statusList = Arrays.asList(StatusPedido.CANCELADO, StatusPedido.FINALIZADO);
		List<Pedido> pedidos = dao.buscarPorDataMaiorOuIgual(data, statusList);
		Relatorio relatorio = new Relatorio(pedidos, "", data, LocalDateTime.now(), RelatorioTipo.ANUAL);
		return Response.of(relatorio.descrever());
	}

	@GetMapping(value = "/entredatas")
	public Response<Relatorio> relatorioEntreDatas(@RequestParam long inicio, @RequestParam long fim) {
		
		LocalDateTime dateInicio =
			    Instant.ofEpochMilli(inicio).atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateFim =
			    Instant.ofEpochMilli(fim).atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		List<StatusPedido> statusList = Arrays.asList(StatusPedido.CANCELADO, StatusPedido.FINALIZADO);
		List<Pedido> pedidos = dao.buscarEntreDatas(dateInicio, dateFim, statusList);
		Relatorio relatorio = new Relatorio(pedidos, "", dateInicio, dateFim, RelatorioTipo.ENTRE_DATAS);
		return Response.of(relatorio.descrever());
	}

}
