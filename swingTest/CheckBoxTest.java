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

public class CheckBoxTest extends JFrame implements ItemListener{
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JTextArea ta;
	private JPanel p1;
	private JPanel p2;
	
	public CheckBoxTest() {
		setTitle("CheckBoxTest");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p1=new JPanel();
		cb1=new JCheckBox("바나나");
		cb2=new JCheckBox("오렌지");
		ta=new JTextArea();
		JScrollPane jsp=new JScrollPane(ta);
		p1.add(cb1); p1.add(cb2); 
		
//		cb1.addItemListener(this);
//		cb2.addItemListener(this);
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,jsp);
		
		setSize(500,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new CheckBoxTest_inner();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox cb=(JCheckBox)e.getSource();
        if(cb.isSelected()) {
            ta.append(cb.getText()+"선택\n");
            ta.setBackground(Color.green);
        }else {
            ta.append(cb.getText()+"선택해제\n");
            ta.setBackground(Color.white);
        }
		
	}
	

}