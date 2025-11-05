package src;

import java.awt.EventQueue;	

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import src.ConexaoPostgreSQL;
import src.Usuario;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldnome;
	private JTextField textFieldemail;
	private JTextField textFieldusuario;
	private JTextField textFieldsenha;
	public Object frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setResizable(false);
		setBounds(100, 100, 329, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CADASTRO USUÁRIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 185, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(10, 45, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldnome = new JTextField();
		textFieldnome.setBounds(10, 64, 296, 20);
		contentPane.add(textFieldnome);
		textFieldnome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setBounds(10, 151, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldemail = new JTextField();
		textFieldemail.setBounds(10, 174, 145, 20);
		contentPane.add(textFieldemail);
		textFieldemail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Usuário:");
		lblNewLabel_3.setBounds(10, 95, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textFieldusuario = new JTextField();
		textFieldusuario.setBounds(10, 120, 296, 20);
		contentPane.add(textFieldusuario);
		textFieldusuario.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Senha:");
		lblNewLabel_4.setBounds(161, 151, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		textFieldsenha = new JTextField();
		textFieldsenha.setBounds(161, 174, 145, 20);
		contentPane.add(textFieldsenha);
		textFieldsenha.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ler valores dos campos
				String nome = textFieldnome.getText().trim();
				String email = textFieldemail.getText().trim();
				String usuario = textFieldusuario.getText().trim();
				String senha = textFieldsenha.getText().trim();

				// Validação básica
				if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Preencha os campos obrigatórios: Nome, Usuário, Senha", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Usuario u = new Usuario(nome, usuario, email, senha);
				boolean ok = ConexaoPostgreSQL.inserirUsuario(u);
				if (ok) {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					// Limpar campos
					textFieldnome.setText("");
					textFieldemail.setText("");
					textFieldusuario.setText("");
					textFieldsenha.setText("");
				} else {
					JOptionPane.showMessageDialog(TelaCadastro.this, "Erro ao cadastrar usuário. Veja o console para detalhes.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(10, 210, 171, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(191, 210, 115, 23);
		contentPane.add(btnNewButton_1);

	}
}