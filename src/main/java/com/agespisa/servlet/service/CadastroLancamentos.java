package com.agespisa.servlet.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.agespisa.servlet.model.Lancamento;
import com.agespisa.servlet.repository.Lancamentos;

public class CadastroLancamentos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentos;
	
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura.");
		}
		this.lancamentos.adicionar(lancamento);
	}
}
