package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; // Importação necessária para Statement

public class ConexaoPostgreSQL {

    private static final String URL = "jdbc:postgresql://localhost:5432/login"; // Altere para o nome do seu banco se for diferente
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    // Retorna uma conexão com o banco (chame em try-with-resources)
    public static Connection getConnection() throws SQLException {
        // Didático: O DriverManager é a classe que gerencia os drivers JDBC.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Insere um usuário na tabela 'usuarios' e retorna o ID gerado.
     * @param u O objeto Usuario a ser inserido.
     * @return O ID gerado pelo banco, ou -1 em caso de falha.
     */
    public static int inserirUsuario(Usuario u) {
        // Didático: Usamos PreparedStatement para segurança (evita SQL Injection).
        String sql = "INSERT INTO usuarios (nome, usuario, email, senha) VALUES (?, ?, ?, ?)";
        int idGerado = -1;
        
        try (Connection conn = getConnection(); 
             // Didático: Statement.RETURN_GENERATED_KEYS diz ao JDBC para retornar o ID gerado.
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, u.getNome());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getSenha());
            
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                // Didático: Obtém o ResultSet com as chaves geradas (o ID).
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGerado = rs.getInt(1); // O ID gerado está na primeira coluna.
                    }
                }
            }
            return idGerado;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Valida o login e retorna o objeto Usuario completo se as credenciais estiverem corretas.
     * @param login O nome de usuário.
     * @param senha A senha.
     * @return O objeto Usuario completo (incluindo o ID) ou null se o login falhar.
     */
    public static Usuario validarLogin(String login, String senha) {
        // Didático: Selecionamos todos os campos para montar o objeto Usuario.
        String sql = "SELECT id, nome, usuario, email, senha FROM usuarios WHERE usuario = ? AND senha = ?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, login);
            ps.setString(2, senha);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Login bem-sucedido: Montamos e retornamos o objeto Usuario.
                    return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("usuario"),
                        rs.getString("email"),
                        rs.getString("senha")
                    );
                }
                // Login falhou
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
