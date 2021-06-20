package listmanagement.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.JTextField;

import listmanagement.db.List;
import listmanagement.db.ListDAO;

public class ListInputAction implements ActionListener{

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
		
		list.setDate(Timestamp.valueOf(LocalDateTime.now()));
		list.setAdd(add.getText());
		list.setpNumber(pNum.getText());
		list.setEtc(etc.getText());
		
		listDAO.insertList(list);
		
		add.setText("");
		pNum.setText("");
		etc.setText("");
	}

}
