package src;

// Nenhuma importação javax.swing ou java.awt.event é mais necessária
// já que não há componentes visuais ou ações de botão.

public class ValidarLogin {
    
    // Apenas a variável de acesso ao banco de dados permanece
    private ConexaoPostgreSQL usuarioDAO; 

    public ValidarLogin() {
        // Inicializa a conexão com o banco de dados quando esta classe é criada
        this.usuarioDAO = new ConexaoPostgreSQL(); 
    }
    
    /**
     * Valida as credenciais do usuário usando a classe ConexaoPostgreSQL.
     * * @param login O nome de usuário fornecido na tela de login.
     * @param senha A senha fornecida na tela de login.
     * @return true se a combinação for válida, false caso contrário.
     */
    public boolean validarCredenciais(String login, String senha) {
        // Chama o método que verifica o login no banco de dados
        return usuarioDAO.validarLogin(login, senha);
    }
}