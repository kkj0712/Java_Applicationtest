package swingTest;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Bank extends JFrame {
	HashMap<String, Integer> hm=new HashMap<String, Integer>(); //이름과 잔액을 기억하기 위함
	
	
	public Bank() {
		setTitle("BANK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,2));
		
		JPanel left=new JPanel(); //왼쪽꺼
		left.setLayout(new GridLayout(5,1));
		JPanel p1=new JPanel(); //왼쪽 첫번째 줄
		p1.add(new JLabel("이름"));
		JTextField tfName=new JTextField(15);
		p1.add(tfName);
		
		JButton btn=new JButton("계좌생성"); //왼쪽 두번째 줄
		JPanel p2=new JPanel(); //왼쪽 세번째 줄
		p2.add(new JLabel("잔액")); 
		JTextField tfBalance=new JTextField(15);
		tfBalance.setEditable(false); //수정 못하게끔
		p2.add(tfBalance);
		
		JPanel p3=new JPanel(); //왼쪽 네번째 줄
		JTextField tfMoney=new JTextField(15);
		p3.add(tfMoney);	p3.add(new JLabel("원"));
		
		JPanel p4=new JPanel(); //왼쪽 다섯번째 줄
		JButton inputBtn=new JButton("예금");
		JButton outputBtn=new JButton("출금");
		JButton fileBtn=new JButton("파일로 저장");
		p4.add(inputBtn);	p4.add(outputBtn);	p4.add(fileBtn);
		
		left.add(p1);	left.add(btn);	left.add(p2); left.add(p3); left.add(p4);
		
		//오른쪽 화면
		List lst=new List();
		//왼쪽 오른쪽 부착
		add(left);	add(lst);
		
		//계좌생성버튼 클릭
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//tfName의 내용을 가져와서 lst에 추가한다. 단 공백은 저장 안함
				if(tfName.getText().isEmpty()) return;
				lst.add(tfName.getText().trim()); //trim은 공백제거
				hm.put(tfName.getText(),0); //맵에 저장
				tfName.setText("");
			}
		});
		//리스트
		lst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			//리스트에서 선택한 이름을 tfName에 넣고 잔액은 tfBalance에 넣기
				String str=lst.getSelectedItem();
				tfName.setText(str);
				tfBalance.setText(hm.get(str)+""); //키값인 str을 넣으면 밸류값(돈)이 반환. setText는 스트링. 
			}
		});
		
		//예금버튼 클릭
		inputBtn.addActionListener(new ActionListener() {
			/* 
			 * 1. 리스트에서 선택한 계좌 잔액에
			 * 2. tfMoney 만큼 더해서
			 * 3. 잔액(tfBalance)을 고쳐주고 맵에 저장 
			 */
			public void actionPerformed(ActionEvent e) {
				String key=lst.getSelectedItem();
				int balance=hm.get(key); //기존잔액
				int value=balance+Integer.parseInt(tfMoney.getText()); //수정잔액
				tfBalance.setText(value+""); //화면에서 잔액수정
				hm.put(key, value); //맵 내용 수정
				tfMoney.setText(""); //편의를 위해 예금액 지우기
			}
		});
		
		//출금버튼 클릭
		outputBtn.addActionListener(new ActionListener() {
			/* 
			 * 1. 리스트에서 선택한 계좌 잔액에
			 * 2. tfMoney 만큼 빼서
			 * 3. 잔액(tfBalance)을 고쳐주고 맵에 저장 
			 */
			public void actionPerformed(ActionEvent e) {
				String key=lst.getSelectedItem();
				int balance=hm.get(key); //기존잔액	
				int value=balance-Integer.parseInt(tfMoney.getText()); //수정잔액
				if(value<0) {
					new MessageBox("잔액부족!!", key+"님 잔액이 부족합니다."); //타이틀, 메시지
					return;
				}
				tfBalance.setText(value+""); //화면에서 잔액수정
				hm.put(key, value); //맵 내용 수정
				tfMoney.setText(""); //편의를 위해 예금액 지우기
			}
		});
		
		setSize(500,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Bank();
		
	}
}
