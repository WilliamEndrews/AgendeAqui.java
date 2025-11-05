package src;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import java.awt.Component;

import javax.swing.JButton;

public class CadastroEventoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField lblDescEvento;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 * @param listaEventosPanel 
	 * @wbp.parser.constructor
	 */
	public CadastroEventoPanel(ListaEventosPanel listaEventosPanel) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descriçao:");
		lblNewLabel.setBounds(10, 11, 71, 14);
		add(lblNewLabel);
		
		lblDescEvento = new JTextField();
		lblDescEvento.setBounds(10, 36, 591, 20);
		add(lblDescEvento);
		lblDescEvento.setColumns(10);
		
		JLabel lblDataEvento = new JLabel("Data do Evento:");
		lblDataEvento.setBounds(10, 70, 86, 14);
		add(lblDataEvento);
		
		textField = new JTextField();
		textField.setBounds(124, 67, 244, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEncaminharEmail = new JLabel("Encaminhar Email:");
		lblEncaminharEmail.setBounds(10, 101, 106, 14);
		add(lblEncaminharEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 98, 244, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Periocidade do Evento:");
		lblNewLabel_1.setBounds(10, 140, 112, 14);
		add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Uma vez");
		rdbtnNewRadioButton.setBounds(124, 136, 71, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Semanal");
		rdbtnNewRadioButton_1.setBounds(208, 136, 71, 23);
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Mensal");
		rdbtnNewRadioButton_2.setBounds(289, 136, 109, 23);
		add(rdbtnNewRadioButton_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Alarme");
		chckbxNewCheckBox.setBounds(10, 161, 97, 23);
		add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(106, 161, 121, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.setBounds(247, 161, 121, 23);
		add(btnNewButton_1);

	}

	public CadastroEventoPanel(Component listaEventosPanel) {
		// TODO Auto-generated constructor stub
	}
}
