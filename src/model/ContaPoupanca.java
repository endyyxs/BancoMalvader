package model;

public class ContaPoupanca extends Conta {
	private double taxaRendimento;

	public ContaPoupanca(int numero, String agencia, double saldo, Cliente cliente, double taxaRendimento) {
		super(numero, agencia, saldo, cliente);
		this.taxaRendimento = taxaRendimento;
	}
	
	
	public double getTaxaRendimento() {
		return taxaRendimento;
	}


	public void setTaxaRendimento(double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}


	public double calcularRendimento() {
		return getSaldo() * taxaRendimento;
		
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

}
