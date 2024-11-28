package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.ContaDAO;
import dao.FuncionarioDAO;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Funcionario;

public class BancoController {
    private List<Conta> contas; // Lista de contas em memória
    private List<Funcionario> funcionarios; // Lista de funcionários em memória
    private FuncionarioDAO funcionarioDAO;
    private ContaDAO contaDAO;

    // Construtor recebe a conexão com o banco de dados
    public BancoController(Connection connection) {
        this.contas = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.funcionarioDAO = new FuncionarioDAO();
        this.contaDAO = new ContaDAO(); // Inicializando o ContaDAO também
    }

    public void adicionarConta(Conta conta, Cliente cliente) {
        try {
            // Verificar se a conta já existe localmente
            if (contas.stream().anyMatch(c -> c.getNumero() == conta.getNumero())) {
                System.err.println("Conta já existe localmente: " + conta.getNumero());
                return;
            }

            boolean sucesso = false;
            // Chama o método adequado para o tipo de conta
            if (conta instanceof ContaCorrente) {
                sucesso = contaDAO.cadastrarConta((ContaCorrente) conta, cliente);
            } else if (conta instanceof ContaPoupanca) {
                sucesso = contaDAO.cadastrarConta((ContaPoupanca) conta, cliente);
            } else {
                System.err.println("Tipo de conta desconhecido.");
                return;
            }

            // Verifica se o cadastro foi bem-sucedido
            if (sucesso) {
                // Adicionar na lista local caso a conta tenha sido cadastrada com sucesso
                this.contas.add(conta);
                System.out.println("Conta adicionada localmente e salva no banco: " + conta.getNumero());
            } else {
                System.err.println("Falha ao salvar conta no banco.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar conta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Remover uma conta da lista local
    public void removerConta(int numeroConta) {
        contas.removeIf(c -> c.getNumero() == numeroConta);
        System.out.println("Conta removida localmente: " + numeroConta);
    }

    // Adicionar um funcionário na lista local
    public void adicionarFuncionarioLocal(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
        System.out.println("Funcionário adicionado localmente: " + funcionario.getNome());
    }

    // Listar todas as contas locais
    public void listarContasLocais() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada localmente.");
        } else {
            for (Conta conta : contas) {
                System.out.println("Conta número: " + conta.getNumero());
            }
        }
    }

    // Listar todos os funcionários locais
    public void listarFuncionariosLocais() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado localmente.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println("Funcionário: " + funcionario.getNome() + ", Cargo: " + funcionario.getCargo());
            }
        }
    }

    // Persistir os funcionários locais no banco de dados
    public void salvarFuncionariosNoBanco() {
        for (Funcionario funcionario : funcionarios) {
            try {
                funcionarioDAO.cadastrarFuncionario(funcionario);
                System.out.println("Funcionário salvo no banco: " + funcionario.getNome());
            } catch (Exception e) {
                System.err.println("Erro ao salvar funcionário no banco: " + funcionario.getNome());
            }
        }
    }

    // Métodos de interação com o DAO (já implementados antes)
    public void cadastrarFuncionario(Funcionario funcionario) {
        try {
            funcionarioDAO.cadastrarFuncionario(funcionario);
            this.funcionarios.add(funcionario); // Também adiciona à lista local
            System.out.println("Funcionário cadastrado no banco e na lista local.");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public Funcionario consultarFuncionario(int id) {
        try {
            return funcionarioDAO.consultarFuncionario(id);
        } catch (Exception e) {
            System.err.println("Erro ao consultar funcionário: " + e.getMessage());
            return null;
        }
    }

    public void alterarFuncionario(Funcionario funcionario) {
        try {
            funcionarioDAO.alterarFuncionario(funcionario);
            System.out.println("Funcionário atualizado no banco.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    public void excluirFuncionario(int id) {
        try {
            funcionarioDAO.excluirFuncionario(id);
            funcionarios.removeIf(f -> f.getId() == id); // Remove da lista local também
            System.out.println("Funcionário excluído do banco e da lista local.");
        } catch (Exception e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
}