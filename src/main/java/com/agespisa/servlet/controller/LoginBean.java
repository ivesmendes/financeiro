package com.agespisa.servlet.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.agespisa.servlet.model.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;
	
	private String nomeUsuario;
	private String senha;
	
	public void login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if("admin".equals(this.nomeUsuario)
				&& "123".equals(this.senha)) {
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
			
			String url = "/financeiro/ConsultaLancamentos.xhtml";
			
			context.getExternalContext().redirect(url);
			
		} else {
			FacesMessage mensagem = new FacesMessage (
					"Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
					
		}
		
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
