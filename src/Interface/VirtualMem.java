package Interface;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VirtualMem extends JPanel {
	Mainframe mf;
	JTextField noFrames;
	JTextField lengthrefString;
	JLabel hitlbl, ratiolbl, faultlbl;
	int frames, pointer = 0, hit = 0, fault = 0, ref_len;
	int buffer[];
	int reference[];
	int mem_layout[][];
	Boolean isFull = false;
    ArrayList<Integer> stack = new ArrayList<Integer>();

	JButton FIFObtn = new JButton("FIFO");
		JButton LRUbtn = new JButton("LRU");
		JButton OPTIMALbtn = new JButton("OPTIMAL");
		JButton btnBack = new JButton("back");

	public static void main(String[] args) {
		
	}

	public VirtualMem(Mainframe mf) {
		this.mf = mf;
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 32);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		

		JLabel hitslbl = new JLabel("No. of hits:");
		hitslbl.setBounds(12, 211, 88, 15);
		add(hitslbl);

		JLabel ratiolbl = new JLabel("Hit Ratio:");
		ratiolbl.setBounds(140, 211, 116, 15);
		add(ratiolbl);

		JLabel faultlbl = new JLabel("No. of faults:");
		faultlbl.setBounds(308, 211, 100, 15);
		add(faultlbl);

		JOptionPane inputs = new JOptionPane("input reference string");
		inputs.setBounds(308, 211, 100, 15);
		add(inputs);

		FIFObtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frames = Integer.parseInt(noFrames.getText());
				ref_len = Integer.parseInt(lengthrefString.getText());

				reference = new int[ref_len];
				mem_layout = new int[ref_len][frames];
				buffer = new int[frames];

				for (int j = 0; j < frames; j++)
					buffer[j] = -1;

				for (int i = 0; i < ref_len; i++) {

					reference[i] = Integer.parseInt(inputs.showInputDialog("enter integer for index " + (i + 1)));
				}

				System.out.println();
				for (int i = 0; i < ref_len; i++) {
					int search = -1;
					for (int j = 0; j < frames; j++) {
						if (buffer[j] == reference[i]) {
							search = j;
							hit++;
							break;
						}
					}
					if (search == -1) {
						buffer[pointer] = reference[i];
						fault++;
						pointer++;
						if (pointer == frames)
							pointer = 0;
					}
					for (int j = 0; j < frames; j++)
						mem_layout[i][j] = buffer[j];
				}

				for (int i = 0; i < frames; i++) {
					for (int j = 0; j < ref_len; j++)
						System.out.printf("%3d ", mem_layout[j][i]);
					System.out.println();
				}

				hitslbl.setText("No. of hits:" + hit);
				ratiolbl.setText("Hit Ratio:" + (float) ((float) hit / ref_len));
				faultlbl.setText("No. of faults:" + fault);
				 frames=0;
				 pointer = 0;
				 hit = 0;
				 fault = 0;
				 ref_len=0;
				 
				 buffer=null;
				 reference=null;
				 mem_layout=null;;
				isFull = false;
			   
			}

		});
		panel.add(FIFObtn);

		LRUbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frames = Integer.parseInt(noFrames.getText());
				ref_len = Integer.parseInt(lengthrefString.getText());

				reference = new int[ref_len];
				mem_layout = new int[ref_len][frames];
				buffer = new int[frames];

				for (int j = 0; j < frames; j++)
					buffer[j] = -1;

				for (int i = 0; i < ref_len; i++) {

					reference[i] = Integer.parseInt(inputs.showInputDialog("enter integer for index " + (i + 1)));
				}
				 System.out.println();
			        for(int i = 0; i < ref_len; i++)
			        {
			            if(stack.contains(reference[i]))
			            {
			             stack.remove(stack.indexOf(reference[i]));
			            }
			            stack.add(reference[i]);
			            int search = -1;
			            for(int j = 0; j < frames; j++)
			            {
			                if(buffer[j] == reference[i])
			                {
			                    search = j;
			                    hit++;
			                    break;
			                }
			            }
			            if(search == -1)
			            {
			             if(isFull)
			             {
			              int min_loc = ref_len;
			                    for(int j = 0; j < frames; j++)
			                    {
			                     if(stack.contains(buffer[j]))
			                        {
			                            int temp = stack.indexOf(buffer[j]);
			                            if(temp < min_loc)
			                            {
			                                min_loc = temp;
			                                pointer = j;
			                            }
			                        }
			                    }
			             }
			                buffer[pointer] = reference[i];
			                fault++;
			                pointer++;
			                if(pointer == frames)
			                {
			                 pointer = 0;
			                 isFull = true;
			                }
			            }
			            for(int j = 0; j < frames; j++)
			                mem_layout[i][j] = buffer[j];
			        }
			        
			        for(int i = 0; i < frames; i++)
			        {
			            for(int j = 0; j < ref_len; j++)
			                System.out.printf("%3d ",mem_layout[j][i]);
			            System.out.println();
			        }
			        
			        hitslbl.setText("No. of hits:" + hit);
					ratiolbl.setText("Hit Ratio:" + (float) ((float) hit / ref_len));
					faultlbl.setText("No. of faults:" + fault);
					 frames=0;
					 pointer = 0;
					 hit = 0;
					 fault = 0;
					 ref_len=0;
					 
					 buffer=null;
					 reference=null;
					 mem_layout=null;;
					isFull = false;
					stack.clear();
				

			}
		});
		panel.add(LRUbtn);

		OPTIMALbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frames = Integer.parseInt(noFrames.getText());
				ref_len = Integer.parseInt(lengthrefString.getText());

				reference = new int[ref_len];
				mem_layout = new int[ref_len][frames];
				buffer = new int[frames];

				for (int j = 0; j < frames; j++)
					buffer[j] = -1;

				for (int i = 0; i < ref_len; i++) {

					reference[i] = Integer.parseInt(inputs.showInputDialog("enter integer for index " + (i + 1)));
				}
				
				 System.out.println();
			        for(int i = 0; i < ref_len; i++)
			        {
			         int search = -1;
			         for(int j = 0; j < frames; j++)
			         {
			          if(buffer[j] == reference[i])
			          {
			           search = j;
			           hit++;
			           break;
			          } 
			         }
			         if(search == -1)
			         {
			          if(isFull)
			          {
			           int index[] = new int[frames];
			           boolean index_flag[] = new boolean[frames];
			           for(int j = i + 1; j < ref_len; j++)
			           {
			            for(int k = 0; k < frames; k++)
			            {
			             if((reference[j] == buffer[k]) && (index_flag[k] == false))
			             {
			              index[k] = j;
			              index_flag[k] = true;
			              break;
			             }
			            }
			           }
			           int max = index[0];
			           pointer = 0;
			           if(max == 0)
			            max = 200;
			           for(int j = 0; j < frames; j++)
			           {
			            if(index[j] == 0)
			             index[j] = 200;
			            if(index[j] > max)
			            {
			             max = index[j];
			             pointer = j;
			            }
			           }
			          }
			          buffer[pointer] = reference[i];
			          fault++;
			          if(!isFull)
			          {
			           pointer++;
			              if(pointer == frames)
			              {
			               pointer = 0;
			               isFull = true;
			              }
			          }
			         }
			            for(int j = 0; j < frames; j++)
			                mem_layout[i][j] = buffer[j];
			        }
			        
			        for(int i = 0; i < frames; i++)
			        {
			            for(int j = 0; j < ref_len; j++)
			                System.out.printf("%3d ",mem_layout[j][i]);
			            System.out.println();
			        }
			        
			        hitslbl.setText("No. of hits:" + hit);
					ratiolbl.setText("Hit Ratio:" + (float) ((float) hit / ref_len));
					faultlbl.setText("No. of faults:" + fault);
					
					 frames=0;
					 pointer = 0;
					 hit = 0;
					 fault = 0;
					 ref_len=0;
					
					 
					 buffer=null;
					 reference=null;
					 mem_layout=null;;
					isFull = false;

			}
		});
		panel.add(OPTIMALbtn);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("integers");

				mf.showMainMenu();
			}
		});
		btnBack.setBounds(12, 263, 117, 25);
		panel.add(OPTIMALbtn);

		JLabel lblNewLabel_1 = new JLabel("No. of frames:");
		lblNewLabel_1.setBounds(12, 73, 100, 15);
		add(lblNewLabel_1);

		noFrames = new JTextField();
		noFrames.setBounds(212, 71, 44, 19);
		add(noFrames);
		noFrames.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Length of Reference String:");
		lblNewLabel_2.setBounds(10, 44, 196, 15);
		add(lblNewLabel_2);

		lengthrefString = new JTextField();
		lengthrefString.setBounds(212, 44, 196, 19);
		add(lengthrefString);
		lengthrefString.setColumns(10);

		
	}
}
