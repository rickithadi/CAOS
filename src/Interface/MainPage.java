package Interface;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class MainPage extends JPanel {

	private Mainframe mf;

	public MainPage(Mainframe mf) {
		this.mf = mf;
		setLayout(null);//set the gui to absolute
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 32);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(37, 43, 371, 257);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("CPU Scheduling");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToCPU();
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Main Memory");
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Virtual Memory");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.showVirtual();
			}
		});
		panel_1.add(btnNewButton_2);

	}

	public void goToCPU() {
		this.mf.showCPU();
	}
}
