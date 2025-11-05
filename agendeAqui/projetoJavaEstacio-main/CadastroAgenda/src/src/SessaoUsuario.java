/**
 * CLASSE DE SESSÃO: SessaoUsuario
 * Responsabilidade: Armazenar o objeto Usuario que está logado no sistema.
 * Padrão: Singleton (apenas uma instância da classe pode existir por vez), 
 * o que garante que apenas um usuário esteja logado.
 */
public class SessaoUsuario {
    private static SessaoUsuario instance;
    private Usuario usuarioLogado;

    // Construtor privado para impedir a criação de novas instâncias.
    private SessaoUsuario() {
    }

    // Método estático para obter a única instância da classe.
    public static SessaoUsuario getInstance() {
        if (instance == null) {
            instance = new SessaoUsuario();
        }
        return instance;
    }

    // Define o usuário logado após o login bem-sucedido.
    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    // Retorna o usuário logado.
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    // Verifica se há um usuário logado.
    public boolean isLogado() {
        return usuarioLogado != null;
    }

    // Encerra a sessão.
    public void logout() {
        this.usuarioLogado = null;
    }
}
