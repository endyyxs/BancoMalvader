package controller;

import dao.ClienteDAO;
import model.Cliente;

public class LoginController {
    private ClienteDAO clienteDAO;

    public LoginController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    public boolean validarLogin(String idCliente, String senha) {
        try {
            Cliente cliente = clienteDAO.buscarPorId(Integer.parseInt(idCliente));
            
            if (cliente != null) {
                if (cliente.getSenha().equals(senha)) {  
                    return true;  
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
        }

        return false;  
    }

    public boolean validarLogin(int idCliente, String senha) {
        try {
            Cliente cliente = clienteDAO.buscarPorId(idCliente);

            if (cliente != null) {
                
                if (cliente.getSenha().equals(senha)) {  
                    return true;  
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
        }

        return false;  
    }
    
    
}
