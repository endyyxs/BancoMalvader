package controller;

import dao.UsuarioDAO;  // Exemplo de DAO para buscar usuário no banco de dados
import model.Usuario;

public class LoginController {

    private UsuarioDAO usuarioDAO;  // DAO para acessar usuários no banco de dados

    public LoginController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Lógica para validar o login
    public boolean validarLogin(String usuario, String senha) {
        try {
            // Buscar o usuário no banco de dados
            Usuario usuarioEncontrado = usuarioDAO.buscarPorNome(usuario);

            // Verifica se o usuário foi encontrado
            if (usuarioEncontrado != null) {
                // Aqui você pode usar uma função para comparar a senha de forma segura (com hash)
                if (usuarioEncontrado.getSenha().equals(senha)) {  // Apenas para exemplo, use hash na prática
                    return true; // Login bem-sucedido
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
        }

        return false; // Login falhou
    }
}

