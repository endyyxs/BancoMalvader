package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
	private ClienteDAO DAO;
	
	
	 // Construtor que recebe o ClienteDAO, para poder realizar as operações no banco
	public ClienteController() {
		this.DAO = new ClienteDAO();
	}
	
	//MÉTODO PARA VERIFICAR A SENHA
	public Cliente autenticarSenha(JPasswordField senha) {
        return ClienteDAO.autenticarSenha(senha);
    }
	
	 // Método para salvar um cliente no banco de dados
    public void salvar(Cliente cliente) {
        try {
        	ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.salvar(cliente);  // Chama o método salvar do DAO
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarClientePorId(int idCliente) {
        try {
        	ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.buscarPorId(idCliente);  // Chama o método buscarPorId do DAO
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    // Método para atualizar as informações de um cliente
    public void atualizarCliente(Cliente cliente) {
        try {
            ClienteDAO clienteDAO2 = new ClienteDAO();
			clienteDAO2.atualizar(cliente);  // Chama o método atualizar do DAO
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Método para excluir um cliente do banco
    public void excluirCliente(int id) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(id);  // Chama o método excluir do DAO
            System.out.println("Cliente excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}