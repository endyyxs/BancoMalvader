package model;

public abstract class Conta {
	private int numero;
	private String agencia;
	private double saldo;
	private Cliente cliente;
	
	public Conta(int numero, String agencia, double saldo, Cliente cliente) {
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.cliente = cliente;
	}
	
	

	public int getNumero() {
		return numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public abstract void depositar(double valor);
	
	public abstract boolean sacar(double valor);
	
	public abstract double consultarSaldo();
}
