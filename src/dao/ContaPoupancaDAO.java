package dao;

import java.sql.Connection;
import java.util.List;

import model.Cliente;
import util.DBUtil;

public class ContaPoupancaDAO extends ContaDAO {
	
	private Connection conexao;
	
	public ContaPoupancaDAO() {
		this.conexao = DBUtil.getConnection();
	}

	@Override
	public double consultarSaldo(Cliente cliente) {
		// TODO Auto-generated method stub
		return super.consultarSaldo(cliente);
	}

	@Override
	public boolean depositar(Cliente cliente, double valor) {
		// TODO Auto-generated method stub
		return super.depositar(cliente, valor);
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
