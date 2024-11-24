package controller;

import java.util.List;

import dao.ContaDAO;
import model.Cliente;

public abstract class ContaController {
	
	private ContaDAO dao;

    public ContaController(ContaDAO dao) {
        if (dao == null) {
            throw new IllegalArgumentException("O DAO não pode ser nulo.");
        }
        this.dao = dao;
    }
	
	public double consultarSaldo(Cliente cliente) {
		try {
			return dao.consultarSaldo(cliente);
		} catch(Exception e) {
			System.out.println("Erro ao consultar saldo: " + e.getMessage());
			return 0;
		}
	}
	
	public void depositar(Cliente cliente, double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor para depósito deve ser positivo.");
		}
		if (dao.depositar(cliente, valor)) {
			System.out.println("Depósito realizado com sucesso.");
		}
	}
	public boolean sacar(Cliente cliente, double valor) {
        if (valor <= 0) {
            System.err.println("O valor para saque deve ser maior que zero.");
            return false;
        }
        try {
            return dao.sacar(cliente, valor);
        } catch (Exception e) {
            System.err.println("Erro ao realizar saque: " + e.getMessage());
            return false;
        }
    }
	public List<String> consultarExtrato(Cliente cliente) {
        try {
            return dao.consultarExtrato(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao consultar extrato: " + e.getMessage());
            return null;
        }
    }


}
