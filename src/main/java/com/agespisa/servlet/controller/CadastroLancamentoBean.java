package com.agespisa.servlet.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.agespisa.servlet.model.Lancamento;
import com.agespisa.servlet.model.Pessoa;
import com.agespisa.servlet.model.TipoLancamento;
import com.agespisa.servlet.repository.Lancamentos;
import com.agespisa.servlet.repository.Pessoas;
import com.agespisa.servlet.service.CadastroLancamentos;
import com.agespisa.servlet.service.NegocioException;

@Named
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Lancamentos lancamentos;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {
			this.todasPessoas = this.pessoas.todas();
			
			if(this.lancamento == null) {
				this.lancamento = new Lancamento();
			}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo:" + event.getOldValue());
		System.out.println("Novo valor:" + event.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if(this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}
	
	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}
}


