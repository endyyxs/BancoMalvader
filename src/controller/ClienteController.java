package controller;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

    private ClienteDAO DAO;

    public ClienteController(ClienteDAO DAO) {
        if (DAO == null) {
            throw new IllegalArgumentException("DAO não pode ser nulo.");
        }
        this.DAO = DAO;
    }

    public Cliente autenticarSenha(JPasswordField senha) {
        try {
            return DAO.autenticarSenha(senha); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar senha: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void salvar(Cliente cliente) {
        try {
            DAO.salvar(cliente);  // Chama o método salvar do DAO
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Cliente buscarClientePorId(int idCliente) {
        try {
            return DAO.buscarPorId(idCliente);  // Chama o método buscarPorId do DAO
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            DAO.atualizar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirCliente(int id) {
        try {
            DAO.excluir(id); 
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
