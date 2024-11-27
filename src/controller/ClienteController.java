package controller;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

    private ClienteDAO DAO;

    // Construtor que recebe o ClienteDAO, para poder realizar as operações no banco
    public ClienteController(ClienteDAO DAO) {
        if (DAO == null) {
            throw new IllegalArgumentException("DAO não pode ser nulo.");
        }
        this.DAO = DAO;
    }

    // Método para autenticar a senha do cliente
    public Cliente autenticarSenha(JPasswordField senha) {
        try {
            return DAO.autenticarSenha(senha);  // Chama o método autenticarSenha do DAO
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar senha: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para salvar um cliente no banco de dados
    public void salvar(Cliente cliente) {
        try {
            DAO.salvar(cliente);  // Chama o método salvar do DAO
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarClientePorId(int idCliente) {
        try {
            return DAO.buscarPorId(idCliente);  // Chama o método buscarPorId do DAO
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Método para atualizar as informações de um cliente
    public void atualizarCliente(Cliente cliente) {
        try {
            DAO.atualizar(cliente);  // Chama o método atualizar do DAO
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para excluir um cliente do banco
    public void excluirCliente(int id) {
        try {
            DAO.excluir(id);  // Chama o método excluir do DAO
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
