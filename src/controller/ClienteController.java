package controller;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

    private ClienteDAO clienteDAO;

    // Construtor que recebe o ClienteDAO, para poder realizar as operações no banco
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Método para salvar um cliente no banco de dados
    public void salvarCliente(Cliente cliente) {
        try {
            clienteDAO.salvar(cliente);  // Chama o método salvar do DAO
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        try {
            return clienteDAO.buscarPorId(id);  // Chama o método buscarPorId do DAO
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    // Método para atualizar as informações de um cliente
    public void atualizarCliente(Cliente cliente) {
        try {
            clienteDAO.atualizar(cliente);  // Chama o método atualizar do DAO
            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Método para excluir um cliente do banco
    public void excluirCliente(int id) {
        try {
            clienteDAO.excluir(id);  // Chama o método excluir do DAO
            System.out.println("Cliente excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
