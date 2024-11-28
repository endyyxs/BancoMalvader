package controller;

import java.util.List;

import dao.ContaDAO;
import dao.ContaCorrenteDAO;
import model.Cliente;

public class ContaCorrenteController extends ContaController {
    
    public ContaCorrenteController(ContaDAO dao) {
        super(dao);
    }

    public double consultarLimite(Cliente cliente) {
        if (dao instanceof ContaCorrenteDAO) {
            try {
                return ((ContaCorrenteDAO) dao).consultarLimite(cliente);
            } catch (Exception e) {
                System.err.println("Erro ao consultar limite: " + e.getMessage());
                return 0;
            }
        } else {
            System.err.println("Erro: DAO não é do tipo ContaCorrenteDAO.");
            return 0;
        }
    }
}
