package model;

public abstract class Conta {
    private int numero;
    private String agencia;
    private double saldo;

    public Conta(int numero, String agencia, double saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
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

    public void atualizarSaldo(double valor) {
        this.saldo += valor;
    }

    public abstract void depositar(double valor);
    public abstract boolean sacar(double valor);
    public abstract double consultarSaldo();
}
