package model;

public class Pessoa {
	String nome;
	boolean presente;
	String servico;
	String valor;
	String data;
	
	public Pessoa() {
		
	}

	public Pessoa(String nome, boolean presente, String servico, String valor) {
		super();
		this.nome = nome;
		this.presente = presente;
		this.servico = servico;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
