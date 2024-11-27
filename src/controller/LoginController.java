package controller;

import dao.ClienteDAO;
import model.Cliente;

public class LoginController {

    private ClienteDAO clienteDAO;

    public LoginController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Lógica para validar o login usando o id do cliente e senha
    public boolean validarLogin(int idCliente, String senha) {
        try {
            // Busca o cliente pelo id
            Cliente cliente = clienteDAO.buscarPorId(idCliente);

            // Verifica se o cliente foi encontrado
            if (cliente != null) {
                // Compara a senha fornecida com a armazenada
                if (cliente.getSenha().equals(senha)) {  // Comparação de senha (hash recomendado)
                    return true;  // Login válido
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
        }

        return false;  // Se o cliente não for encontrado ou a senha não corresponder
    }
}
