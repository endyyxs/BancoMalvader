package controller;

import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.ContaDAO;
import dao.ContaPoupancaDAO;
import dao.FuncionarioDAO;

public class FuncionarioController {
	 private FuncionarioDAO funcionarioDAO;
	 private String usuarioDefault = "admin";
	 private String senhaDefault = "admin123";
	 
	 public FuncionarioController(FuncionarioDAO funcionarioDAO) {
	      this.funcionarioDAO = funcionarioDAO;
	 }
    
    public boolean validarLogin(String usuario, String senha) {
        if(usuario.equals(usuarioDefault) && senha.equals(senhaDefault)) {
        	return true;
        }
        
        return false;  
    }
    
    public void processarEncerramentoConta(String senha) {
    	
        if (senha.equals(senhaDefault)) {

        	String numeroConta = JOptionPane.showInputDialog("Digite o número da conta a ser encerrada:");

        	if (numeroConta == null || numeroConta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Número de conta inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return; 
            }
       	 
       	 ContaPoupancaDAO contaDAO = new ContaPoupancaDAO();
       	 
       	 try {
	       	 if(contaDAO.deletarConta(Integer.parseInt(numeroConta))) {
	       		 JOptionPane.showMessageDialog(null, "Conta " + numeroConta + " encerrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	       	 } else {
	       		 JOptionPane.showMessageDialog(null, "Conta " + numeroConta + " não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
	       	 } 
       	 } catch(Exception e) {
       		JOptionPane.showMessageDialog(null, "Conta " + numeroConta + " não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
       	 }
            
        } else {
        	JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return; 
        }
    }
}

