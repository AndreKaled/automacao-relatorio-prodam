package model;

public class Pessoa {
	String nome;
	int presente;
	String servico;
	String valor;
	String data;
	String cpf;
	
	public Pessoa() {
		
	}

	public Pessoa(String nome, String cpf, int presente, String servico, String valor, String data) {
		super();
		this.nome = nome;
		this.presente = presente;
		this.servico = servico;
		this.valor = valor;
		this.cpf = cpf;
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPresente() {
		return presente;
	}

	public void setPresente(int presente) {
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
	
	public String getCpf(){
		return cpf;
	}

	public void setCpf(String cpf){
		this.cpf = cpf;
	}

	@Override
	public String toString(){
		return  nome +"{cpf:" +cpf +"|Presente:" +presente
		+"|Data:" +data +"|Valor:" +valor +"|Serviço:" +servico +"}";
	}
}
