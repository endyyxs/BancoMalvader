package model;

import java.time.LocalDate;

public class Funcionario extends Usuario {
	private String codigoFuncionario;
	private String cargo;
	private String senha;
	
	
	public Funcionario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String codigoFuncionario, String cargo, String senha) {
		super(id, nome, cpf, dataNascimento, telefone, endereco);
		this.codigoFuncionario = codigoFuncionario;
		this.cargo = cargo;
		this.senha = senha;
	}
	
	public void abrirConta(Conta conta) {
		
	}
	
	public void encerrarConta(Conta conta) {
		
	}
	
	public Conta consultarDadosConta(int numeroConta) {
		return null;
		
	}
	
	public Cliente consultarDadosCliente(int idCliente) {
		return null;
		
	}
	
	public void alterarDadosConta(Conta conta) {
		
	}
	
	public void alterarDadosCliente(Cliente cliente) {
		
	}
	
	public void cadastrarFuncionario(Funcionario funcionario) {
		
	}
	
	public void gerarRelatorioMovimentacao() {
		
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
