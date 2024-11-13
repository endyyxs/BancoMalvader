package model;

public class Endereco {
	private String cep;
	private String local;
	private int numeroCasa;
	private String bairro;
	private String cidade;
	private String estado;
	
	public String toString() {
		return "Endereco "
				+ "\nCEP: " + cep + ", Local: " + local + ", Número da casa: " + numeroCasa + ", Bairro: " + bairro
				+ ", Cidade: " + cidade + ", Estado:" + estado;
	}
	
}
