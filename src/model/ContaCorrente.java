package model;

import java.time.LocalDate;

public class ContaCorrente extends Conta {
	private double limite;
	private LocalDate dataVencimento;
	
	public ContaCorrente(int numero, String agencia, double saldo,double limite, LocalDate dataVencimento) {
		super(numero, agencia, saldo);
		this.limite = limite;
		this.dataVencimento = dataVencimento;
	}
	
	public double consultarLimite() {
		return limite;
		
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public void depositar(double valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean sacar(double valor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double consultarSaldo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getSenha() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getLimiteContaCorrente() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getDataVencimento() {
		// TODO Auto-generated method stub
		return null;
	}
}
