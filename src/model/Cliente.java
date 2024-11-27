package model;

import java.time.LocalDate;

public class Cliente extends Usuario {
    public Cliente(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha) {
		super(id, nome, cpf, dataNascimento, telefone, endereco, senha);
    }


	public double consultarSaldo() {
		return 0;
	}
    
	@Override
	public boolean login(String senha) {
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	}

	@Override
	public String consultarDados() {
		return null;
	}
}