package controller;

import java.util.List;
import java.util.Optional;

import dao.ContaDAO;
import model.Cliente;

public abstract class ContaController {
	protected ContaDAO dao;

    public ContaController(ContaDAO dao) {
        this.dao = dao;
    }

    public double consultarSaldo(Cliente cliente) {
        return tratarOperacao(() -> dao.consultarSaldo(cliente));
    }

    public void depositar(Optional<Cliente> clienteOpt, double valor) {
        clienteOpt.orElseThrow(() -> new IllegalArgumentException("Cliente não pode ser nulo."));
        
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor para depósito deve ser positivo.");
        }
        
        try {
            if (dao.depositar(clienteOpt.get(), valor)) {
                System.out.println("Depósito realizado com sucesso.");
            } else {
                throw new RuntimeException("Falha ao realizar depósito.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar o depósito: " + e.getMessage());
        }
    }

    public boolean sacar(Cliente cliente, double valor) {
        if (valor <= 0) {
            System.err.println("O valor para saque deve ser maior que zero.");
            return false;
        }
        return tratarOperacao(() -> dao.sacar(cliente, valor));
    }

    public List<String> consultarExtrato(Cliente cliente) {
        return tratarOperacao(() -> dao.consultarExtrato(cliente));
    }

    private <T> T tratarOperacao(Operacao<T> operacao) {
        try {
            return operacao.executar();
        } catch (Exception e) {
            System.err.println("Erro ao realizar operação: " + e.getMessage());
            return null;  // Ou lançar uma exceção personalizada
        }
    }

    // Interface funcional para operações
    @FunctionalInterface
    private interface Operacao<T> {
        T executar() throws Exception;
    }
}
