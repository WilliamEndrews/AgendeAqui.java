package src;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.Color;

public class MinhaAgenda extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MinhaAgenda() {
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		Panel panel_1 = new Panel();
		panel_1.setEnabled(false);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(0, 128, 255));
		
		Panel panel = new Panel();
		add(panel);

	}

}
