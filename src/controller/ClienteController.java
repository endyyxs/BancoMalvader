package controller;

import java.util.List;

import DAO.ClienteDAO;
import model.Cliente;

public class ClienteController {
	private ClienteDAO DAO;
	
	public ClienteController() {
		this.DAO = new ClienteDAO();
	}
	
	public double consultarSaldo(Cliente cliente) {
		try {
			return DAO.consultarSaldo(cliente);
		} catch(Exception e) {
			System.out.println("Erro ao consultar saldo: " + e.getMessage());
			return 0;
		}
		
	}
	
	public void depositar(Cliente cliente, double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor para depósito deve ser positivo.");
		}
		if (DAO.depositar(cliente, valor)) {
			System.out.println("Depósito realizado com sucesso.");
		}
	}
	public boolean sacar(Cliente cliente, double valor) {
        if (valor <= 0) {
            System.err.println("O valor para saque deve ser maior que zero.");
            return false;
        }
        try {
            return DAO.sacar(cliente, valor);
        } catch (Exception e) {
            System.err.println("Erro ao realizar saque: " + e.getMessage());
            return false;
        }
    }
	public List<String> consultarExtrato(Cliente cliente) {
        try {
            return DAO.consultarExtrato(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao consultar extrato: " + e.getMessage());
            return null;
        }
    }
	public double consultarLimite(Cliente cliente) {
        try {
            return DAO.consultarLimite(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao consultar limite: " + e.getMessage());
            return 0;
        }
    }

}
