package controller;

import java.util.List;

import dao.ContaCorrenteDAO;
import dao.ContaDAO;
import model.Cliente;

public class ContaCorrenteController extends ContaController {
	
	public ContaCorrenteController(ContaDAO dao) {
		super(dao);
	}
	private ContaCorrenteDAO dao;

	public double consultarLimite(Cliente cliente) {
        try {
            return dao.consultarLimite(cliente);
        } catch (Exception e) {
            System.err.println("Erro ao consultar limite: " + e.getMessage());
            return 0;
        }
    }

	@Override
	public double consultarSaldo(Cliente cliente) {
		// TODO Auto-generated method stub
		return super.consultarSaldo(cliente);
	}

	@Override
	public void depositar(Cliente cliente, double valor) {
		// TODO Auto-generated method stub
		super.depositar(cliente, valor);
	}

	@Override
	public boolean sacar(Cliente cliente, double valor) {
		// TODO Auto-generated method stub
		return super.sacar(cliente, valor);
	}

	@Override
	public List<String> consultarExtrato(Cliente cliente) {
		// TODO Auto-generated method stub
		return super.consultarExtrato(cliente);
	}
	

}
