package Interface;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class Mainframe extends JFrame{
	
	private CardLayout card;
	
	public Mainframe() {

		this.setTitle("Temasek Pizzas");//set the title to "Temasek Pizzas" for all screens
		this.setSize(475, 350);//set the gui size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the program when the "X" button is pressed

		this.card = new CardLayout();//initialising for CardLayout
		this.setLayout(this.card);//to set the JFrame Layout
		this.showMainMenu();//to show the first page
		this.setVisible(true);//to make sure the gui is visible

	}	
	
	public static void main(String[] args) {

		Mainframe mf = new Mainframe();//object of MainFrame

	}
	
	public void showMainMenu() {
		
		MainPage mp = new MainPage(this);//instance of the MainMenu class
		this.add(mp, "Main Page");//adding it to mainframe
		this.card.show(this.getContentPane(), "Main Page");//to prompt cardlayout to display the screen
		
	}
	
	public void showCPU() {
		CPUScheduling cpu = new CPUScheduling(this);
		this.add(cpu, "CPU Scheduling");
		this.card.show(this.getContentPane(), "CPU Scheduling");
	}
	
	public void showVirtual() {
		VirtualMem vm = new VirtualMem(this);
		this.add(vm, "virtual memory");
		this.card.show(this.getContentPane(), "virtual memory");
	}

}
