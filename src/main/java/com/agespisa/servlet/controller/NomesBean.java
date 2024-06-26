package com.agespisa.servlet.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;

@ManagedBean
@ViewScoped
public class NomesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private List<String> nomes = new ArrayList<>();
	
	private HtmlInputText inputNome;
	private HtmlCommandButton botaoAdicionar;
	
//	public void adicionar() {
//		this.nomes.add(nome);
//		
//	// desativa campo e botão quando mais que 3 nomes
//	// forem adicionados
//		
//		if(this.nomes.size() > 3) {
//			this.inputNome.setDisabled(true);
//			this.botaoAdicionar.setDisabled(true);
//			this.botaoAdicionar.setValue("Mutos nomes adicionados...");
//			}
//			
//	}
//	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getNomes() {
		return nomes;
	}

	public HtmlInputText getInputNome() {
		return inputNome;
	}

	public void setInputNome(HtmlInputText inputNome) {
		this.inputNome = inputNome;
	}

	public HtmlCommandButton getBotaoAdicionar() {
		return botaoAdicionar;
	}

	public void setBotaoAdicionar(HtmlCommandButton botaoAdicionar) {
		this.botaoAdicionar = botaoAdicionar;
	}
	
	public String adicionar() {
		this.nomes.add(nome);
		
		if(this.nomes.size() > 3) {
			return "Ola?faces-redirect=true";
		}
		
		return null;
	}
	
}
