package swingTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List; //util에 있는 List는 Arraylist
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ListTest2 extends JFrame{
	private JTextField tf;
	private JTextArea ta;
	private List list;
	private JCheckBox cb;
	
	public ListTest2() { //생성자 만들기
		setTitle("List 예제");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p1=new JPanel();
		tf=new JTextField(15);
		JButton btn=new JButton("추가");
		cb=new JCheckBox("다중선택");
		p1.setBackground(Color.DARK_GRAY);
		p1.add(tf); p1.add(btn); p1.add(cb);
		
		JPanel p2=new JPanel(new GridLayout(1,2));
		list=new List();
		ta=new JTextArea();
		p2.add(list); p2.add(ta);
		ActionH ah=new ActionH();
		ItemH ih=new ItemH();
		btn.addActionListener(ah); 
		tf.addActionListener(ah); 
		list.addItemListener(ih); 
		cb.addItemListener(ih); 
		
		add(BorderLayout.NORTH,p1);
		add(BorderLayout.CENTER,p2);
		
		setSize(400,400);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new ListTest2(); //생성자 부르기
	}
	//ActionH
	class ActionH implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			if(tf.getText().isEmpty()) return; 
			list.add(tf.getText());
			tf.setText(""); //비우기
		}
		
	}
	//ItemH
	class ItemH implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			Object obj=e.getSource();
			if(obj==list) { //list가 이벤트 발생
				ta.setText("");
				if(list.isMultipleMode()) { //리스트가 다중선택
					String []arr=list.getSelectedItems();
					for(int i=0;i<arr.length;i++) {
						ta.append(arr[i]+"\n");
					}
				}else { //리스트가 단일선택
					//리스트(list)에서 선택한 내용을 textarea(ta)에 출력하기
					ta.setText(list.getSelectedItem());
				}
			}else if(obj==cb) { //checkbox가 이벤트 발생
				if(cb.isSelected()) { //checkbox 선택인지->리스트가 다중모드
					list.setMultipleMode(true);
				}else { //checkbox 해제인지->리스트가 단일모드
					list.setMultipleMode(false);
				}
			}
		}
		
		
	}
}
