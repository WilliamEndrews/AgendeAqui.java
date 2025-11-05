package src;
/**
 * CLASSE MODELO: Usuario
 * Responsabilidade: Representar a estrutura de um usuário no sistema e no banco de dados.
 * Padrão: POJO (Plain Old Java Object) - Contém apenas atributos, construtores e métodos de acesso (getters/setters).
 */
public class Usuario {
    private int id; // ID único do usuário (chave primária no banco)
    private String nome;
    private String usuario;
    private String email;
    private String senha;

    // Construtor vazio
    public Usuario() {
    }

    // Construtor para Inserção (sem ID, pois o banco gera)
    public Usuario(String nome, String usuario, String email, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }
    
    // Construtor completo (com ID, para leitura do banco)
    public Usuario(int id, String nome, String usuario, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }

    // --- GETTERS e SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', usuario='" + usuario + "', email='" + email + "', senha='" + senha + "'}";
    }
}


ao vivo

Pular para ao vivo
