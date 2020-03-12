package com.lanchonete.maida.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.lanchonete.maida.model.Pedido.StatusPedido;
import com.lanchonete.maida.service.IPedidoService;

@SpringBootTest
public class PedidoDaoTest {

	IPedidoService pedidoService;

	@Before
	public void before() {
		pedidoService = Mockito.mock(IPedidoService.class);
	}

	@Test
	public void testaMudancaStatus() {
		Mockito.when(pedidoService.podeAlterarStatus(StatusPedido.SOLICITADO, StatusPedido.CANCELADO)).thenReturn(false);
		
		Assert.assertTrue(pedidoService.podeAlterarStatus(StatusPedido.SOLICITADO, StatusPedido.CANCELADO));
	}

}
