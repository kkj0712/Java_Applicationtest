package swingTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalTest extends JFrame implements ActionListener {
	private JTextField tf1, tf2, tf3;
	
	public CalTest() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		tf1=new JTextField(5); //숫자 1
		tf2=new JTextField(5); //숫자 2
		tf3=new JTextField(20); //결과
		
		JButton b1=new JButton("+");
		JButton b2=new JButton("-");
		JButton b3=new JButton("*");
		JButton b4=new JButton("/");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
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
		new CalTest();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str=e.getActionCommand();
		try {
		int a= Integer.parseInt(tf1.getText());
		int b= Integer.parseInt(tf2.getText());
		
		switch(str) {
		case "+" : tf3.setText(String.valueOf(a+b)); break;
		case "-" : tf3.setText(a-b+""); break; //문자를 더하면 자동적으로 문자로 바뀜
		case "*" : tf3.setText(a*b+""); break;
		case "/" : tf3.setText(a/b+""); break; 
			}
		}catch (NumberFormatException n) {
			tf3.setText("숫자를 입력하세요");
		}catch (ArithmeticException a) {
			tf3.setText("0으로 나눌 수 없습니다.");
		}
		
	}
	
}
