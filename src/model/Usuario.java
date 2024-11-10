package model;

import java.time.LocalDate;

public abstract class Usuario {
	private int id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String telefone;
	private Endereco endereco;
	
	public Usuario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public abstract boolean login(String senha);
	public abstract void logout();
	public abstract String consultarDados();

}
