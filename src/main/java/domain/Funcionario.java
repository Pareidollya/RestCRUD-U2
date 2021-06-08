package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Funcionario {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long matricula;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String dataCadastro;
	
	public Funcionario() {
	}
	
	public Funcionario(long matricula, String nome, String email, String dataCadastro) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.dataCadastro = dataCadastro;
		
	}
	public String getEmail() {
		return email;
	}
	public long getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public String getCadastro() {
		return dataCadastro;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	@Override
	public String toString() {
		return "Sim [matricula: "+ matricula+ ", nome: " + nome + ", email: " + email + ", Data de cadastro: "+ dataCadastro + "]";
	}
}
