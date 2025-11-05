import java.time.LocalDate;

/**
 * Classe de Modelo (POJO - Plain Old Java Object) para representar uma Tarefa.
 * Esta classe contém apenas atributos, construtores, getters e setters.
 * É a representação da tabela 'tarefas' no banco de dados.
 */
public class Tarefa {
    private int id;
    private int idUsuario; // Chave estrangeira para ligar a tarefa ao usuário
    private String descricao;
    private LocalDate data; // Usando LocalDate para melhor manipulação de datas
    private String periodicidade;
    private String emailNotificacao;
    private boolean alarmeAtivo;

    // Construtor Completo
    public Tarefa(int id, int idUsuario, String descricao, LocalDate data, String periodicidade, String emailNotificacao, boolean alarmeAtivo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.data = data;
        this.periodicidade = periodicidade;
        this.emailNotificacao = emailNotificacao;
        this.alarmeAtivo = alarmeAtivo;
    }

    // Construtor para Inserção (sem ID, pois o banco gera)
    public Tarefa(int idUsuario, String descricao, LocalDate data, String periodicidade, String emailNotificacao, boolean alarmeAtivo) {
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.data = data;
        this.periodicidade = periodicidade;
        this.emailNotificacao = emailNotificacao;
        this.alarmeAtivo = alarmeAtivo;
    }

    // Construtor Vazio
    public Tarefa() {
    }

    // Getters e Setters (Essenciais para o padrão POJO)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getEmailNotificacao() {
        return emailNotificacao;
    }

    public void setEmailNotificacao(String emailNotificacao) {
        this.emailNotificacao = emailNotificacao;
    }

    public boolean isAlarmeAtivo() {
        return alarmeAtivo;
    }

    public void setAlarmeAtivo(boolean alarmeAtivo) {
        this.alarmeAtivo = alarmeAtivo;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", periodicidade='" + periodicidade + '\'' +
                ", emailNotificacao='" + emailNotificacao + '\'' +
                ", alarmeAtivo=" + alarmeAtivo +
                '}';
    }
}
