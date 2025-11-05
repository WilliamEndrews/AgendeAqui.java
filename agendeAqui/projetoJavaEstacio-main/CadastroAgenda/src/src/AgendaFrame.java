import java.awt.EventQueue;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

public class AgendaFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected static Object usuarioDAO;
	private JPanel contentPane;
	private JTextField DescricaoEvento;
	private JTextField DatadoEvento;
	private JTextField Email;
	private JTable table;
	
	// Atributos para os componentes da tela
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JCheckBox chckbxNewCheckBox;
	
	// Novo atributo para as áreas de texto dos dias da semana
	private JTextArea[] diasDaSemana = new JTextArea[7];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaFrame frame = new AgendaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgendaFrame() {
		setBounds(100, 100, 664, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("tab1");
		tabbedPane.setBounds(10, 11, 5, 5);
		contentPane.add(tabbedPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 11, 628, 440);
		contentPane.add(tabbedPane_1);
		
		// =================================================================
		// ABA 1: CADASTRO DE EVENTOS
		// =================================================================
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Cadastro Eventos", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descrição do Evento:");
		lblNewLabel.setBounds(10, 11, 115, 14);
		panel.add(lblNewLabel);
		
		DescricaoEvento = new JTextField();
		DescricaoEvento.setBounds(10, 29, 603, 20);
		panel.add(DescricaoEvento);
		DescricaoEvento.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Data do Evento:");
		lblNewLabel_1.setBounds(10, 60, 89, 14);
		panel.add(lblNewLabel_1);
		
		DatadoEvento = new JTextField("dd/MM/yyyy");
		DatadoEvento.setBounds(109, 57, 177, 20);
		panel.add(DatadoEvento);
		DatadoEvento.setColumns(10);
				
		JLabel lblNewLabel_2 = new JLabel("Encaminhar e-mail:");
		lblNewLabel_2.setBounds(10, 89, 101, 14);
		panel.add(lblNewLabel_2);
		
		Email = new JTextField();
		Email.setBounds(109, 86, 246, 20);
		panel.add(Email);
		Email.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Periocidade do Evento:");
		lblNewLabel_3.setBounds(10, 124, 127, 14);
		panel.add(lblNewLabel_3);
		
		rdbtnNewRadioButton = new JRadioButton("Uma vez");
		rdbtnNewRadioButton.setBounds(141, 120, 75, 23);
		panel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Semanal");
		rdbtnNewRadioButton_1.setBounds(226, 120, 75, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Mensal");
		rdbtnNewRadioButton_2.setBounds(303, 120, 109, 23);
		panel.add(rdbtnNewRadioButton_2);
		
		chckbxNewCheckBox = new JCheckBox("Alarme");
		chckbxNewCheckBox.setBounds(2, 150, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Obter o ID do usuário logado
			    Usuario usuario = SessaoUsuario.getInstance().getUsuarioLogado();
			    if (usuario == null) {
			        JOptionPane.showMessageDialog(null, "Nenhum usuário logado. Faça o login novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
			        return;
			    }
			    int idUsuario = usuario.getId();

			    // 2. Coletar dados da tela
			    String descricao = DescricaoEvento.getText();
			    String dataStr = DatadoEvento.getText();
			    String email = Email.getText();
			    boolean alarme = chckbxNewCheckBox.isSelected();
			    String periodicidadeSelecionada = "";
			    
			    // Lógica para obter a periodicidade
			    if (rdbtnNewRadioButton.isSelected()) {
			        periodicidadeSelecionada = "Uma vez";
			    } else if (rdbtnNewRadioButton_1.isSelected()) {
			        periodicidadeSelecionada = "Semanal";
			    } else if (rdbtnNewRadioButton_2.isSelected()) {
			        periodicidadeSelecionada = "Mensal";
			    }

			    // 3. Validação e Conversão da Data
			    LocalDate dataTarefa;
			    try {
			        // Didático: Converte a String da tela ("dd/MM/yyyy") para o objeto LocalDate
			        dataTarefa = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			    } catch (java.time.format.DateTimeParseException ex) {
			        JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.", "Erro de Data", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    // 4. Criar o objeto Tarefa
			    Tarefa novaTarefa = new Tarefa(idUsuario, descricao, dataTarefa, periodicidadeSelecionada, email, alarme);

			    // 5. Chamar o DAO para inserir
			    TarefaDAO dao = new TarefaDAO();
			    boolean sucesso = dao.inserir(novaTarefa);

			    // 6. Feedback para o usuário
			    if (sucesso) {
			        JOptionPane.showMessageDialog(null, "Tarefa salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			        
			        // Limpar campos
			        DescricaoEvento.setText("");
			        DatadoEvento.setText("dd/MM/yyyy");
			        Email.setText("");
			        rdbtnNewRadioButton.setSelected(false);
			        rdbtnNewRadioButton_1.setSelected(false);
			        rdbtnNewRadioButton_2.setSelected(false);
			        chckbxNewCheckBox.setSelected(false);
			        
			        // Recarrega a lista de eventos (se a aba estiver aberta)
			        carregarAgendaSemanal();
			    } else {
			        // Verifica se a falha foi devido à regra de negócio (limite de 3)
			        if (!dao.podeInserirTarefa(idUsuario, dataTarefa)) {
			             JOptionPane.showMessageDialog(null, "Limite de 3 tarefas por dia atingido para esta data!", "Regra de Negócio", JOptionPane.WARNING_MESSAGE);
			        } else {
			             JOptionPane.showMessageDialog(null, "Erro ao salvar a tarefa. Verifique o console para detalhes.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			}
		});
		btnNewButton.setBounds(120, 150, 127, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(257, 150, 98, 23);
		panel.add(btnNewButton_1);
		
		// =================================================================
		// ABA 2: LISTA DE EVENTOS (MANTIDA, MAS SERÁ SUBSTITUÍDA PELA AGENDA SEMANAL)
		// =================================================================
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("Lista de Eventos", null, panel_1, null);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Data", "Descri\u00E7\u00E3o", "Periodicidade", "E-mail", "Alarme"
			}
		));
		table.setBounds(10, 11, 603, 187);
		panel_1.add(table);
		
		// =================================================================
		// ABA 3: AGENDA SEMANAL (NOVA)
		// =================================================================
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("Agenda Semanal", null, panel_2, null);
		// Didático: Usamos GridLayout para organizar os 7 dias em 7 linhas e 1 coluna
		panel_2.setLayout(new GridLayout(7, 1)); 

		String[] nomesDias = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"};

		for (int i = 0; i < 7; i++) {
		    JTextArea area = new JTextArea();
		    area.setEditable(false);
		    // Didático: BorderFactory.createTitledBorder adiciona um título ao painel do dia
		    area.setBorder(BorderFactory.createTitledBorder(nomesDias[i]));
		    diasDaSemana[i] = area;
		    // JScrollPane adiciona barra de rolagem se o conteúdo for muito grande
		    panel_2.add(new JScrollPane(area)); 
		}
		
		// Adiciona um Listener para carregar a agenda quando a aba for selecionada
		tabbedPane_1.addChangeListener(e -> {
		    if (tabbedPane_1.getSelectedIndex() == 2) { // Índice 2 é a terceira aba (0, 1, 2)
		        carregarAgendaSemanal();
		    }
		});
	}
	
	/**
	 * Método didático para carregar as tarefas da semana e exibir nas áreas de texto.
	 */
	private void carregarAgendaSemanal() {
	    Usuario usuario = SessaoUsuario.getInstance().getUsuarioLogado();
	    if (usuario == null) {
	        // Não exibe JOptionPane aqui, pois pode ser chamado ao iniciar a tela
	        return;
	    }
	    
	    // 1. Cálculo das Datas da Semana
	    LocalDate hoje = LocalDate.now();
	    // Didático: TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY) encontra a última ou a própria Segunda-feira
	    LocalDate dataInicio = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	    LocalDate dataFim = hoje.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
	    
	    // 2. Busca no Banco de Dados
	    TarefaDAO dao = new TarefaDAO();
	    List<Tarefa> tarefasDaSemana = dao.buscarPorUsuarioEPeriodo(usuario.getId(), dataInicio, dataFim);
	    
	    // 3. Limpa as áreas de texto
	    for (JTextArea area : diasDaSemana) {
	        area.setText("");
	    }
	    
	    // 4. Distribui as tarefas nos dias
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
	    
	    for (Tarefa tarefa : tarefasDaSemana) {
	        // DayOfWeek.getValue() retorna 1 para Segunda, 7 para Domingo.
	        int indiceDia = tarefa.getData().getDayOfWeek().getValue() - 1; 
	        
	        if (indiceDia >= 0 && indiceDia < 7) {
	            JTextArea area = diasDaSemana[indiceDia];
	            
	            // Monta a string da tarefa
	            String textoTarefa = String.format("[%s] %s (%s)\n", 
	                tarefa.getData().format(formatter),
	                tarefa.getDescricao(), 
	                tarefa.getPeriodicidade());
	            
	            area.append(textoTarefa);
	        }
	    }
	}
}


