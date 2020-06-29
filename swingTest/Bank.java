package swingTest;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Bank extends JFrame {
	HashMap<String, Integer> hm=new HashMap<String, Integer>(); //�̸��� �ܾ��� ����ϱ� ����
	List lst;
	
	public Bank() {
		setTitle("BANK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,2));
		
		JPanel left=new JPanel(); //���ʲ�
		left.setLayout(new GridLayout(5,1));
		
		JPanel p1=new JPanel(); //���� ù��° ��
		p1.add(new JLabel("�̸�"));
		JTextField tfName=new JTextField(15);
		p1.add(tfName);
		
		JButton btn=new JButton("���»���"); //���� �ι�° ��
		JPanel p2=new JPanel(); //���� ����° ��
		p2.add(new JLabel("�ܾ�")); 
		
		JTextField tfBalance=new JTextField(15);
		tfBalance.setEditable(false); //���� ���ϰԲ�
		p2.add(tfBalance);
		
		JPanel p3=new JPanel(); //���� �׹�° ��
		JTextField tfMoney=new JTextField(15);
		p3.add(tfMoney);	p3.add(new JLabel("��"));
		
		JPanel p4=new JPanel(); //���� �ټ���° ��
		JButton inputBtn=new JButton("����");
		JButton outputBtn=new JButton("���");
		JButton fileBtn=new JButton("���Ϸ� ����");
		p4.add(inputBtn);	p4.add(outputBtn);	p4.add(fileBtn);
		
		left.add(p1);	left.add(btn);	left.add(p2); left.add(p3); left.add(p4);
		
		//������ ȭ��
		lst=new List();
		//���� ������ ����
		add(left);	add(lst);
		
		//���»�����ư Ŭ��
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//tfName�� ������ �����ͼ� lst�� �߰��Ѵ�. �� ������ ���� ����
				if(tfName.getText().isEmpty()) return;
				lst.add(tfName.getText().trim()); //trim�� �յ� ��������
				hm.put(tfName.getText(),0); //�ʿ� ����
				tfName.setText("");
			}
		});
		//����Ʈ
		lst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			//����Ʈ���� ������ �̸��� tfName�� �ְ� �ܾ��� tfBalance�� �ֱ�
				String str=lst.getSelectedItem().trim(); //trim�� �յ� ��������
				tfName.setText(str);
				tfBalance.setText(hm.get(str)+""); //Ű���� str�� ������ �����(��)�� ��ȯ. setText�� ��Ʈ��. 
			}
		});
		
		//���ݹ�ư Ŭ��
		inputBtn.addActionListener(new ActionListener() {
			/* 
			 * 1. ����Ʈ���� ������ ���� �ܾ׿�
			 * 2. tfMoney ��ŭ ���ؼ�
			 * 3. �ܾ�(tfBalance)�� �����ְ� �ʿ� ���� 
			 */
			public void actionPerformed(ActionEvent e) {
			 try {
				String key=lst.getSelectedItem().trim();
				int balance=hm.get(key); //�����ܾ�
				int value=balance+Integer.parseInt(tfMoney.getText()); //�����ܾ�
				tfBalance.setText(value+""); //ȭ�鿡�� �ܾ׼���
				hm.put(key, value); //�� ���� ����
				tfMoney.setText(""); //���Ǹ� ���� ���ݾ� �����
			 }catch (NullPointerException x) {
				new MessageBox("����", "���¸� ������ �ּ���");
			 }catch (NumberFormatException n) {
				new MessageBox("�Է¿���", "���ڸ� �Է��ϼ���");
			 }
			}
		});
		
		//��ݹ�ư Ŭ��
		outputBtn.addActionListener(new ActionListener() {
			/* 
			 * 1. ����Ʈ���� ������ ���� �ܾ׿�
			 * 2. tfMoney ��ŭ ����
			 * 3. �ܾ�(tfBalance)�� �����ְ� �ʿ� ���� 
			 */
			public void actionPerformed(ActionEvent e) {
			 try {
				String key=lst.getSelectedItem().trim();
//				String key=tfName.getText();
				int balance=hm.get(key); //�����ܾ�	
				int value=balance-Integer.parseInt(tfMoney.getText()); //�����ܾ�
				if(value<0) {
					new MessageBox("�ܾ׺���!!", key+"�� �ܾ��� �����մϴ�."); //Ÿ��Ʋ, �޽���
					return;
				}
				tfBalance.setText(value+""); //ȭ�鿡�� �ܾ׼���
				hm.put(key, value); //�� ���� ����
				tfMoney.setText(""); //���Ǹ� ���� ���ݾ� �����
			 }catch (NullPointerException x) {
					new MessageBox("����", "���¸� ������ �ּ���");
			 }catch (NumberFormatException n) {
					new MessageBox("�Է¿���", "���ڸ� �Է��ϼ���");
			}
			}
		});
		
		//���Ϸ� ����
		fileBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//hm�� ������ ���Ϸ� �������ڴ�.(CapitalApp2�� quiz()�޼ҵ� ����)
				File dir=new File("src\\swingTest");
				File file=new File(dir,"myBank.txt");
				try {
					FileWriter fw=new FileWriter(file);
					Set<String>set=hm.keySet(); //key�� ����
					Iterator<String>it=set.iterator();
					while(it.hasNext()) {
						String key=it.next(); //�ϳ��� key���ϱ�
						fw.write(key+" "); //�̸� ��������
						fw.write(hm.get(key)+"\n"); //�ܾױ��ϱ�
					}
					fw.close();
				}catch (IOException e1) {
					new MessageBox("���Ͽ���!!!", "�������� ����");
					e1.printStackTrace();
				}
			}
		});
		
		setSize(500,300);
		setVisible(true);
		load();
	}
	
	//���Ͽ� ������ ������ list�� �θ���
	private void load() {
		hm.clear();
		File dir=new File("src\\swingTest");
		File file=new File(dir,"myBank.txt");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			Scanner sc=new Scanner(file); //���Ϸ� �о����
			while(sc.hasNext()) {
				String name=sc.next().trim(); //�̸�
				int money=sc.nextInt(); //�ܾ�
				hm.put(name, money); //�ʿ� ����
				lst.add(name+"\n"); //����Ʈ�� �̸� �߰�
			}
			sc.close();
		}catch(Exception e2) {
			e2.printStackTrace();
			System.out.println("����");
		}
	}
	
	public static void main(String[] args) {
		new Bank();
		
	}
}
