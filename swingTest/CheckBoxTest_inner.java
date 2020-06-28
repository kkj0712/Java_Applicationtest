package swingTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CheckBoxTest_inner extends JFrame{
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JTextArea ta;
	private JPanel p1;
	private JPanel p2;
	
	public CheckBoxTest_inner() {
		setTitle("CheckBoxTest_inner");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p1=new JPanel();
		cb1=new JCheckBox("�ٳ���");
		cb2=new JCheckBox("������");
		ta=new JTextArea();
		JScrollPane jsp=new JScrollPane(ta); //���� �Ű������� 10�� 20�����? 
		p1.add(cb1); p1.add(cb2); 
		
		ItemEventH ih=new ItemEventH(ta); //�Ű������� �̿��Ͽ� ���� ��Ŵ
		cb1.addItemListener(ih);
		cb2.addItemListener(ih);
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,jsp);
		
		setSize(500,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new CheckBoxTest_inner();
	}
	
	
}
