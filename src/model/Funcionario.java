package model;

import java.time.LocalDate;

import javax.swing.JOptionPane;

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
	
	
	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void abrirConta(Conta conta) {
		JOptionPane.showMessageDialog(null, "Conta aberta para o cliente: " + conta.getCliente().getNome());
	}
	
	public void encerrarConta(Conta conta) {
		JOptionPane.showMessageDialog(null, "Conta " + conta.getNumero() + " encerrada.");
	}
	
	public Conta consultarDadosConta(int numeroConta) {
		JOptionPane.showMessageDialog(null, "Consultando dados da Conta " + numeroConta);
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
