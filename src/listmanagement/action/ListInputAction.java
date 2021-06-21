package listmanagement.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import listmanagement.db.List;
import listmanagement.db.ListDAO;

public class ListInputAction implements ActionListener {

	private List list;
	JTextField add;
	JTextField pNum;
	JTextField etc;

	public ListInputAction(JTextField add, JTextField pNum, JTextField etc) {
		list = new List();

		this.add = add;
		this.pNum = pNum;
		this.etc = etc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ListDAO listDAO = new ListDAO();
		
		String phoneNumber = pNum.getText();
		phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
		
		char c[] = phoneNumber.toCharArray();
		if (c.length != 13) {
			
			if(c.length == 11) {
				if (c[3] != '-' || c[8] != '-') {
					//  c[] => 01012345678 => String 010-1234-5678
					// index : 0 1 2 3 4 5 6 7 8 9 10
					// 	c    : 0 1 0 1 2 3 4 5 6 7 8
					String tmp = String.valueOf(c[0]);
					tmp += String.valueOf(c[1]);
					tmp += String.valueOf(c[2]);
					tmp += "-";
					tmp += String.valueOf(c[3]);
					tmp += String.valueOf(c[4]);
					tmp += String.valueOf(c[5]);
					tmp += String.valueOf(c[6]);
					tmp += "-";
					tmp += String.valueOf(c[7]);
					tmp += String.valueOf(c[8]);
					tmp += String.valueOf(c[9]);
					tmp += String.valueOf(c[10]);
					
					phoneNumber = tmp;
				}
			}else {
				JOptionPane.showMessageDialog(null, "전화번호를 잘 못 입력하였습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				pNum.setText("");
				return;
			}

		}

		list.setDate(Timestamp.valueOf(LocalDateTime.now()));
		list.setAdd(add.getText());
		list.setpNumber(phoneNumber);
		list.setEtc(etc.getText());

		listDAO.insertList(list);

		add.setText("");
		pNum.setText("");
		etc.setText("");
	}

}
