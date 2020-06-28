package swingTest;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class ItemEventH implements ItemListener{
	JTextArea ta;
	public ItemEventH(JTextArea ta) {
		this.ta=ta;
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
