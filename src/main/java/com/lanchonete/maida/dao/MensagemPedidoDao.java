package com.lanchonete.maida.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanchonete.maida.exceptions.ResourceNotFoundException;
import com.lanchonete.maida.model.MensagemPedido;
import com.lanchonete.maida.model.Pedido;
import com.lanchonete.maida.repository.MensagemPedidoRepository;
import com.lanchonete.maida.service.IMensagemPedidoService;

@Service
public class MensagemPedidoDao implements IMensagemPedidoService {

	@Autowired
	private MensagemPedidoRepository rep;

	@Override
	public MensagemPedido salvar(MensagemPedido mensagem) {
		return rep.save(mensagem);
	}

	@Override
	public MensagemPedido buscarPorId(int id) {
		Optional<MensagemPedido> optional = rep.findById(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("mensagem não encontrada");
		}
		return optional.get();
	}

	@Override
	public List<MensagemPedido> listarPorPedido(Pedido pedido) {
		return rep.findByPedidoOrderByHorarioDesc(pedido);
	}

	@Override
	public void deletar(int id) {
		rep.delete(buscarPorId(id));
	}

	@Override
	public MensagemPedido alterar(MensagemPedido mensagem) {
		buscarPorId(mensagem.getId());
		return rep.saveAndFlush(mensagem);
	}

}
