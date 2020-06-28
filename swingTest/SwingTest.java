package swingTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingTest extends JFrame implements ActionListener{
	private JTextField tf1;
	private JTextArea ta;
	
	public SwingTest() {
		setTitle("연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X표시를 누르면 JFrame 종료
		setLayout(new FlowLayout());
		tf1=new JTextField(20);
		JButton btn=new JButton("클릭");
		ta=new JTextArea(5, 20);
		btn.addActionListener(this);
		add(tf1);
		add(btn);
		add(ta);
		setSize(500,500);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new SwingTest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ta.append(tf1.getText()+"\n");
		tf1.setText("");
		
	}
	
	

}
