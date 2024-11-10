package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
	private String senha;

	public Cliente(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha) {
		super(id, nome, cpf, dataNascimento, telefone, endereco);
		this.senha = senha;
	}
	
	public double consultarSaldo() {
		return 0;
	}
	
	public void depositar(double valor) {
		
	}
	
	public boolean sacar(double valor) {
		return false;
	}
	
	public String consultarExtrato() {
		return null;
	}
	
	public double consultarLimite() {
		return 0;
	}

	@Override
	public boolean login(String senha) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String consultarDados() {
		// TODO Auto-generated method stub
		return null;
	}

}
