package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class inserirDados extends TelaCadastro {
	
	private String nome;
	private String usuario;
	private String email;
	private String senha;
	
	//construtor
	public inserirDados(String nome, String usuario, String email, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}
	//Metodos
	public String getNome() {return nome;}
	public String getUsuario() {return usuario;}
	public String getEmail() {return email;}
	public String getSenha() {return senha;}

}
