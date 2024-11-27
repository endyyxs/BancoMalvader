package model;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(int numero, String agencia, double saldo, double taxaRendimento) {
        super(numero, agencia, saldo);
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
    
    public int getNumeroConta() {
        return getNumero(); 
    }

    public double calcularRendimento() {
        return getSaldo() * taxaRendimento;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
        } else {
            System.err.println("Valor de depósito inválido.");
        }
    }

    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            return true;
        } else {
            System.err.println("Saldo insuficiente ou valor inválido.");
            return false;
        }
    }

    
    @Override
    public double consultarSaldo() {
        return getSaldo(); 
    }
}
