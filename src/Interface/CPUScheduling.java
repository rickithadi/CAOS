package Interface;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class CPUScheduling extends JPanel {
	
	private Mainframe mf;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public CPUScheduling(Mainframe mf) {
		this.mf = mf;
		setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 32);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 42, 624, 31);
	}
}
