package model;

public class Endereco {
	private String cep;
	private String local;
	private int numeroCasa;
	private String bairro;
	private String cidade;
	private String estado;
	
	public String toString() {
		return "Endereco [cep=" + cep + ", local=" + local + ", numeroCasa=" + numeroCasa + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
}
