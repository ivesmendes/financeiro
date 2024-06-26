package com.agespisa.servlet.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.agespisa.servlet.model.Lancamento;
import com.agespisa.servlet.repository.Lancamentos;
import com.agespisa.servlet.service.CadastroLancamentos;
import com.agespisa.servlet.service.NegocioException;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentosRepository;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	private Lancamento lancamentoSelecionado;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@PostConstruct
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}
	
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			System.out.println("VALOR: " + lancamentoSelecionado.getDescricao());
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			
			context.addMessage(null, new FacesMessage(
					"Lançamento excluído com sucesso!"));
			
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	
	
	
}
