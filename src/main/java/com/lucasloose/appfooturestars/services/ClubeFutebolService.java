package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ClubeFutebolService {

	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	public List<ClubeFutebol> buscarLista() {
		List<ClubeFutebol> clubeFutebol = clubeFutebolRepository.findAll();
		if (clubeFutebol == null) {
			throw new ObjectNotFoundException("Clubes de Futebol não encontrados!"
					+ ", Tipo: " + ClubeFutebol.class.getName());
		}
		return clubeFutebol;
	}
	
	public ClubeFutebol buscar(Integer id) {
		ClubeFutebol clubeFutebol = clubeFutebolRepository.findOne(id);
		if (clubeFutebol == null) {
			throw new ObjectNotFoundException("Clube de Futebol não encontrado! ID: " + id
					+ ", Tipo: " + ClubeFutebol.class.getName());
		}
		return clubeFutebol;
	}
	
	public ClubeFutebol insert(ClubeFutebol obj) {
		obj.setId(null);
//		obj.setInstante(new Date());
//		obj.setCliente(clubeFutebolRepository.findOne(obj.getId()));
//		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
//		obj.getPagamento().setPedido(obj);
//		if (obj.getPagamento() instanceof PagamentoComBoleto) {
//			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
//			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
//		}
		obj = clubeFutebolRepository.save(obj);
//		pagamentoRepository.save(obj.getPagamento());
//		for (ItemPedido ip : obj.getItens()) {
//			ip.setDesconto(0.0);
//			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
//			ip.setPreco(ip.getProduto().getPreco());
//			ip.setPedido(obj);
//		}
//		itemPedidoRepository.save(obj.getItens());
//		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
}
