package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.Usuario;
import util.DBUtil;

public class ContaDAO {
    private Connection connection;

    public ContaDAO() {
        this.connection = DBUtil.getConnection();
    }
    
    public boolean deletarConta(Integer numero) {
    	String sql = "DELETE FROM conta WHERE numero_conta = ?";
    	
    	try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    		stmt.setInt(1, numero);  
    		
    		stmt.executeUpdate();
            connection.commit(); 
            
    		return true;
    	} catch (SQLException e) {
            System.err.println("Erro ao deletar conta: " + e.getMessage());
        } catch (Exception e) {
        	System.err.println("Erro: " + e.getMessage());
        }
    	
		return false;
    }
    
    public double consultarSaldo(Cliente cliente) {
        String sql = "SELECT saldo FROM conta WHERE cliente_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar saldo: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar saldo", e);
        }
        return 0;
    }

    
    public boolean depositar(Cliente cliente, double valor) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE cliente_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, cliente.getId());  
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                registrarTransacao(cliente, valor, "deposito");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao depositar: " + e.getMessage());
            throw new RuntimeException("Erro ao depositar", e);
        }
    }

    
    public boolean sacar(Cliente cliente, double valor) {
        String sql = "UPDATE conta SET saldo = saldo - ? WHERE cliente_id = ? AND saldo >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, cliente.getId());  
            stmt.setDouble(3, valor);  
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                registrarTransacao(cliente, valor, "saque");
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao sacar: " + e.getMessage());
            throw new RuntimeException("Erro ao sacar", e);
        }
    }

    
    public List<String> consultarExtrato(Cliente cliente) {
        String sql = "SELECT t.data_hora, t.tipo_transacao, t.valor " +
                     "FROM transacao t " +
                     "JOIN conta c ON t.conta_id = c.id_conta " +
                     "WHERE c.cliente_id = ? ORDER BY t.data_hora DESC";
        List<String> extrato = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());  
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String linha = String.format("Data: %s, Tipo: %s, Valor: R$ %.2f",
                        rs.getTimestamp("data_hora"),
                        rs.getString("tipo_transacao"),
                        rs.getDouble("valor"));
                extrato.add(linha);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar extrato: " + e.getMessage());
            throw new RuntimeException("Erro ao consultar extrato", e);
        }
        return extrato;
    }

    
    private void registrarTransacao(Cliente cliente, double valor, String tipoTransacao) {
        String sql = "INSERT INTO transacao (tipo_transacao, valor, data_hora, conta_id) " +
                     "SELECT ?, ?, NOW(), id_conta FROM conta WHERE cliente_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoTransacao);
            stmt.setDouble(2, valor);
            stmt.setInt(3, cliente.getId());  
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao registrar transação: " + e.getMessage());
            throw new RuntimeException("Erro ao registrar transação", e);
        }
    }

    
    public boolean cadastrarConta(Conta conta, Cliente cliente) {
        try {
            connection.setAutoCommit(false);
           
            if(!inserirUsuario((Cliente) cliente)) {
            	return false;
            }
            
            if (!inserirCliente(cliente)) {
                return false;
            }

            if (!inserirConta(conta, cliente)) {
                return false;
            }
            
            if (conta instanceof ContaCorrente) {
                if (!inserirContaCorrente((ContaCorrente) conta)) {
                    return false;
                }
            } else if (conta instanceof ContaPoupanca) {
                if (!inserirContaPoupanca((ContaPoupanca) conta)) {
                    return false;
                }
            }

            connection.commit();  
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();  
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao reverter transação: " + rollbackEx.getMessage());
            }
            
            System.err.println("Erro ao cadastrar conta: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);  
            } catch (SQLException autoCommitEx) {
                System.err.println("Erro ao restaurar auto-commit: " + autoCommitEx.getMessage());
            }
        }
    }
    
    private boolean inserirUsuario(Usuario usuario) {
    	String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, ?)";
    	
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario instanceof Cliente ? "cliente" : "funcionario");
            stmt.setString(6, usuario.getSenha());
            
            stmt.executeUpdate();
            connection.commit(); 
            
            return true;
        } catch(SQLException e) {
        	System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        	return false;
        }
    }
    
    private boolean inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (usuario_id) VALUES (LAST_INSERT_ID())";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
            connection.commit(); 
            
            return true;
        } catch(SQLException e) {
        	System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        	return false;
        }
    }

    
    private boolean inserirConta(Conta conta, Cliente cliente) {
        String sql = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, cliente_id) VALUES (?, ?, ?, ?, LAST_INSERT_ID())";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, conta.getNumero());
            stmt.setString(2, conta.getAgencia());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setString(4, conta instanceof ContaCorrente ? "corrente" : "poupanca");
            
            stmt.executeUpdate();
            connection.commit(); 
            
            return true;
        } catch(SQLException e) {
        	System.err.println("Erro ao cadastrar conta: " + e.getMessage());
        	return false;
        }
    }

    
    private boolean inserirContaCorrente(ContaCorrente contaCorrente) {
        String sql = "INSERT INTO conta_corrente (limite, data_vencimento, conta_id) VALUES (?, ?, LAST_INSERT_ID())";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, contaCorrente.getLimite());
            stmt.setDate(2, java.sql.Date.valueOf(contaCorrente.getDataVencimento()));
            
            stmt.executeUpdate();
            connection.commit(); 
            
            return true;
        } catch(SQLException e) {
        	System.err.println("Erro ao cadastrar conta corrente: " + e.getMessage());
        	return false;
        }
    }
    
    private boolean inserirContaPoupanca(ContaPoupanca contaPoupanca) {
        String sql = "INSERT INTO conta_poupanca (taxa_rendimento, conta_id) VALUES (?, LAST_INSERT_ID())";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, contaPoupanca.getTaxaRendimento());
            
            stmt.executeUpdate();
            connection.commit(); 
            return true;
        } catch(SQLException e) {
        	System.err.println("Erro ao cadastrar conta poupança: " + e.getMessage());
        	return false;
        }
    }      
        
        public void salvar(Conta conta) throws Exception {
            String sql = "INSERT INTO conta (numero_conta, saldo, agencia, cliente_id) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, conta.getNumero());
                stmt.setDouble(2, conta.getSaldo());
                stmt.setString(3, conta.getAgencia());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Erro ao salvar a conta: " + e.getMessage());
                throw new Exception("Erro ao salvar a conta: " + e.getMessage());
            }
        }
    }