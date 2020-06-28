package swingTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MessageBox extends JFrame{
	public MessageBox(String title, String msg) {
	setTitle(title);
	setLayout(new FlowLayout());
//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); Bank클래스의 프레임까지 같이 닫아버림
	JButton closeBtn=new JButton("닫기");
	JLabel lbl=new JLabel(msg);
	add(lbl);
	add(closeBtn);
	closeBtn.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			dispose(); //자신의 창만 닫도록 함
		}
	});
	setSize(300,100);
	setVisible(true);
	}
	
	
}
