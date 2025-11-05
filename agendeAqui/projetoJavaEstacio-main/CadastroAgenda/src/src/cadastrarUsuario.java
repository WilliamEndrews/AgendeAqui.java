package src;

import java.sql.Connection;	
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cadastrarUsuario (Usuario usuario) {
	String url = "jdbc:postgresql://localhost:5432/loginAgenda";
	String user = "postgres";
	String password = "1234";
	
	//comando Sql
	String sql = "INSERT INTO usuarios(nome, usuario, email, senha) VALUES (?, ?, ?, ?)";

	try(Connection conn = DriverManager.getConnection(url, user, password)) {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, usuario.getNome());
		pstmt.setString(2, usuario.getEmail());
		pstmt.setString(3, usuario.getUsuario());
		pstmt.setString(4, usuario.getSenha());
		pstmt.executeUpdate();
		System.out.println("Usuário cadastrado com sucesso!");
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}

}
