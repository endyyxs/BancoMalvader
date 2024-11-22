package Controller;

import DAO.ClienteDAO;

public class ClienteController {
	private ClienteDAO DAO;
	
	public ClienteController() {
		this.DAO = new ClienteDAO();
	}
	
	public double consultarSaldo(int ClienteId) {
		return DAO.consultarSaldo(ClienteId);
	}
	
	public void depositar(int clienteId, double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor para depósito deve ser positivo.");
		}
		if (DAO.depositar(clienteId, valor)) {
			System.out.println("Depósito realizado com sucesso.");
		}
	}
}
