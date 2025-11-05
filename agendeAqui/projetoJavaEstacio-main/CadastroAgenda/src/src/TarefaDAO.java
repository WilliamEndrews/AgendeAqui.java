import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE DAO: TarefaDAO
 * Responsabilidade: Gerenciar a persistência (CRUD) da entidade Tarefa no banco de dados.
 * Padrão: Data Access Object (DAO) - Isola a lógica de acesso a dados.
 */
public class TarefaDAO {

    /**
     * Verifica se o usuário pode inserir mais uma tarefa em uma data específica.
     * Regra de Negócio: Máximo de 3 tarefas por dia.
     * @param idUsuario O ID do usuário.
     * @param data A data da tarefa.
     * @return true se o número de tarefas for menor que 3, false caso contrário.
     */
    public boolean podeInserirTarefa(int idUsuario, LocalDate data) {
        // Didático: Usamos COUNT(*) para obter o número de tarefas existentes.
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_usuario = ? AND data_tarefa = ?";
        
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            ps.setDate(2, Date.valueOf(data));
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    // Regra de Negócio: Se o contador for menor que 3, pode inserir.
                    return count < 3; 
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar limite de tarefas: " + e.getMessage());
            e.printStackTrace();
        }
        // Em caso de erro, por segurança, retornamos false para evitar inserções indevidas.
        return false; 
    }
    
    /**
     * Insere uma nova tarefa no banco de dados.
     * @param tarefa O objeto Tarefa a ser inserido.
     * @return true se a inserção foi bem-sucedida, false caso contrário.
     */
    public boolean inserir(Tarefa tarefa) {
        // 1. VERIFICAÇÃO DA REGRA DE NEGÓCIO
        if (!podeInserirTarefa(tarefa.getIdUsuario(), tarefa.getData())) {
            System.out.println("Regra de Negócio Violada: Limite de 3 tarefas por dia atingido.");
            return false;
        }
        
        // 2. PERSISTÊNCIA (se a regra for atendida)
        // SQL: Comando para inserir os dados na tabela 'tarefas'.
        String sql = "INSERT INTO tarefas (id_usuario, descricao, data_tarefa, periodicidade, email_notificacao, alarme_ativo) VALUES (?, ?, ?, ?, ?, ?)";
        
        // try-with-resources: Garante que a conexão (conn) e o statement (ps) sejam fechados automaticamente.
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, tarefa.getIdUsuario());
            ps.setString(2, tarefa.getDescricao());
            // Conversão: O JDBC exige java.sql.Date, então convertemos o LocalDate.
            ps.setDate(3, Date.valueOf(tarefa.getData())); 
            ps.setString(4, tarefa.getPeriodicidade());
            ps.setString(5, tarefa.getEmailNotificacao());
            ps.setBoolean(6, tarefa.isAlarmeAtivo());
            
            // Execução: executeUpdate() retorna o número de linhas afetadas.
            int rowsAffected = ps.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir tarefa: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca todas as tarefas de um usuário em uma data específica.
     * @param idUsuario O ID do usuário logado.
     * @param data A data para buscar as tarefas.
     * @return Uma lista de objetos Tarefa.
     */
    public List<Tarefa> buscarPorUsuarioEData(int idUsuario, LocalDate data) {
        List<Tarefa> tarefas = new ArrayList<>();
        // SQL: Busca tarefas que pertencem ao usuário E que são na data especificada.
        String sql = "SELECT id, id_usuario, descricao, data_tarefa, periodicidade, email_notificacao, alarme_ativo FROM tarefas WHERE id_usuario = ? AND data_tarefa = ?";
        
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            ps.setDate(2, Date.valueOf(data));
            
            try (ResultSet rs = ps.executeQuery()) {
                // Iteração: Percorre cada linha (registro) retornado pelo banco.
                while (rs.next()) {
                    // Cria um novo objeto Tarefa para cada registro.
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setIdUsuario(rs.getInt("id_usuario"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    // Conversão: Converte java.sql.Date de volta para LocalDate.
                    tarefa.setData(rs.getDate("data_tarefa").toLocalDate()); 
                    tarefa.setPeriodicidade(rs.getString("periodicidade"));
                    tarefa.setEmailNotificacao(rs.getString("email_notificacao"));
                    tarefa.setAlarmeAtivo(rs.getBoolean("alarme_ativo"));
                    
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas: " + e.getMessage());
            e.printStackTrace();
        }
        return tarefas;
    }

    /**
     * Busca todas as tarefas de um usuário dentro de um período de datas.
     * @param idUsuario O ID do usuário logado.
     * @param dataInicio O primeiro dia do período (ex: Segunda-feira).
     * @param dataFim O último dia do período (ex: Domingo).
     * @return Uma lista de objetos Tarefa.
     */
    public List<Tarefa> buscarPorUsuarioEPeriodo(int idUsuario, LocalDate dataInicio, LocalDate dataFim) {
        List<Tarefa> tarefas = new ArrayList<>();
        // SQL: Busca tarefas que pertencem ao usuário E que estão no intervalo de datas.
        String sql = "SELECT id, id_usuario, descricao, data_tarefa, periodicidade, email_notificacao, alarme_ativo FROM tarefas WHERE id_usuario = ? AND data_tarefa BETWEEN ? AND ?";
        
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            ps.setDate(2, Date.valueOf(dataInicio));
            ps.setDate(3, Date.valueOf(dataFim));
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setIdUsuario(rs.getInt("id_usuario"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setData(rs.getDate("data_tarefa").toLocalDate()); 
                    tarefa.setPeriodicidade(rs.getString("periodicidade"));
                    tarefa.setEmailNotificacao(rs.getString("email_notificacao"));
                    tarefa.setAlarmeAtivo(rs.getBoolean("alarme_ativo"));
                    
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar tarefas por período: " + e.getMessage());
            e.printStackTrace();
        }
        return tarefas;
    }
}