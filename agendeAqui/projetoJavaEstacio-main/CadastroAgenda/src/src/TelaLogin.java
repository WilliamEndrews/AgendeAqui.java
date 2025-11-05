package src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class TelaLogin extends LoginGUI {

	private JFrame frame;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setForeground(new Color(255, 255, 0));
		frame.setBounds(100, 100, 647, 372);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SISTEMA AGENDA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(393, 26, 170, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setBounds(348, 87, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(348, 112, 235, 20);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setBounds(348, 136, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton EntrarButton = new JButton("Entrar");
		EntrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				LoginGUI loginGUI = new LoginGUI();
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				if (loginGUI.usuarioDAO.validarLogin(login, senha)) {
					 JOptionPane.showMessageDialog(null, "Login bem-sucedido! " + " Bem-vindo, " + login + "!");
					/*System.out.println("Bem-vindo, " + login + "!");*/
					AgendaFrame agenda = new AgendaFrame();
					agenda.setVisible(true);
					
					// Aqui você pode adicionar o código para abrir a tela principal
				} else {
					System.out.println("Login ou senha incorretos!");
					 JOptionPane.showMessageDialog(null, "Login ou senha incorretos!!");
				}
			}
		});
		EntrarButton.setBounds(348, 190, 89, 23);
		frame.getContentPane().add(EntrarButton);
		
		JButton EsqueceuSenhaButton = new JButton("Esqueceu a Senha");
		EsqueceuSenhaButton.setBounds(440, 190, 143, 23);
		frame.getContentPane().add(EsqueceuSenhaButton);

		
		JButton btnNewButton_2 = new JButton("Cadastrar-se");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(348, 224, 235, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 318, 309);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\PC\\eclipse-workspace\\novo\\CadastroAgenda\\IMGSISTEMA.png"));
		lblNewLabel_3.setBounds(0, 11, 308, 287);
		panel.add(lblNewLabel_3);
		
		txtSenha = new JPasswordField();
		txtSenha.setEchoChar('*');
		txtSenha.setBounds(348, 159, 235, 20);
		frame.getContentPane().add(txtSenha);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		System.out.println("Tela de Login Visível" + TelaLogin.class);
		
	}
}
