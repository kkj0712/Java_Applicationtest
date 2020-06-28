package swingTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalTest2 extends JFrame {
	private JTextField tf1, tf2, tf3;
	
	public CalTest2() {
		setTitle("계산기2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		tf1=new JTextField(5); //숫자 1
		tf2=new JTextField(5); //숫자 2
		tf3=new JTextField(20); //결과
		
		JButton b1=new JButton("+");
		JButton b2=new JButton("-");
		JButton b3=new JButton("*");
		JButton b4=new JButton("/");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a= Integer.parseInt(tf1.getText());
				int b= Integer.parseInt(tf2.getText());
				tf3.setText(a+b+"");
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a= Integer.parseInt(tf1.getText());
				int b= Integer.parseInt(tf2.getText());
				tf3.setText(a-b+"");
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a= Integer.parseInt(tf1.getText());
				int b= Integer.parseInt(tf2.getText());
				tf3.setText(a*b+"");
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a= Integer.parseInt(tf1.getText());
				int b= Integer.parseInt(tf2.getText());
				tf3.setText(a/b+"");
			}
		});
		
		add(new JLabel("숫자1"));
		add(tf1);
		add(new JLabel("숫자2"));
		add(tf2);
		add(b1); add(b2); add(b3); add(b4);
		add(new JLabel("결과"));
		add(tf3);
		
		setSize(700,100);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new CalTest2();
	}
	
	
	
		
	
}
