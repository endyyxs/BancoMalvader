package model;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    // Construtor da conta poupança
    public ContaPoupanca(int numero, String agencia, double saldo, Cliente cliente, double taxaRendimento) {
        super(numero, agencia, saldo, cliente);
        this.taxaRendimento = taxaRendimento;
    }

    // Getter e Setter para taxa de rendimento
    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
    
    public int getNumeroConta() {
        return getNumero(); // Chama o getNumero() da classe pai
    }

    // Método para calcular o rendimento da conta poupança
    public double calcularRendimento() {
        return getSaldo() * taxaRendimento;
    }

    // Método para depositar valores na conta
    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
        } else {
            System.err.println("Valor de depósito inválido.");
        }
    }

    // Método para sacar valores da conta (considerando saldo suficiente)
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

    // Método para consultar o saldo da conta (herdado da classe Conta)
    @Override
    public double consultarSaldo() {
        return getSaldo(); // A classe pai já gerencia o saldo
    }
}
