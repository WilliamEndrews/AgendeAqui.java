package src;
import javax.swing.*;	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField loginField;
    private JPasswordField senhaField;
    private JButton loginButton;
    ConexaoPostgreSQL usuarioDAO;

    public LoginGUI() {
    	    super("Tela de Login");
    	    this.usuarioDAO = new ConexaoPostgreSQL(); // Inicializa a variável de INSTÂNCIA
    	    // ...
    	 // Estas linhas devem estar ANTES de addActionListener
    	    loginField = new JTextField(15);
    	    senhaField = new JPasswordField(15);
    	    loginButton = new JButton("Login"); // ESTA LINHA É ESSENCIAL!
        // Ação do Botão
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	String login = txtLogin.getText();
            	String senha = new String(txtSenha.getPassword());

            	// O método validarLogin agora retorna o objeto Usuario ou null
            	Usuario usuario = ConexaoPostgreSQL.validarLogin(login, senha);

            	if (usuario != null) {
            	    // 1. Armazena o usuário na sessão
            	    SessaoUsuario.getInstance().setUsuarioLogado(usuario);
            	    
            	    // 2. Abre a tela principal (AgendaFrame)
            	    AgendaFrame frame = new AgendaFrame();
            	    frame.setVisible(true);
            	    
            	    // 3. Fecha a tela de login
            	    dispose(); 
            	} else {
            	    JOptionPane.showMessageDialog(this, "Login ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            	}

        });

        // Configurações da Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajusta o tamanho da janela aos componentes
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(false); // Torna a janela visível
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}