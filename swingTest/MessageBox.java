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
//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); BankŬ������ �����ӱ��� ���� �ݾƹ���
	JButton closeBtn=new JButton("�ݱ�");
	JLabel lbl=new JLabel(msg);
	add(lbl);
	add(closeBtn);
	closeBtn.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			dispose(); //�ڽ��� â�� �ݵ��� ��
		}
	});
	setSize(300,100);
	setVisible(true);
	}
	
	
}
