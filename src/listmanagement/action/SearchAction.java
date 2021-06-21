package listmanagement.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import listmanagement.db.List;
import listmanagement.db.ListDAO;
import listmanagement.gui.MainWindow;

public class SearchAction implements ActionListener {

	private ListDAO listDAO = new ListDAO();
	
	private JTable table;
	private JSpinner start;
	private JSpinner end;
	private JTextField add;
	
	private MainWindow frame;

	public SearchAction(JTable table, JSpinner start, JSpinner end, JTextField add, MainWindow frame) {
		this.table = table;
		this.start = start;
		this.end = end;
		this.add = add;

		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date startDate = (Date) start.getValue();
		Date endDate = (Date) end.getValue();
		
		Timestamp startStamp = new Timestamp(startDate.getTime());
		Timestamp endStamp = new Timestamp(endDate.getTime());
		
		ListDAO listDAO = new ListDAO();
		Vector<List> lists = listDAO.search(startStamp, endStamp, add.getText());
		frame.setCurrentList(lists);
		
		add.setText("");
		
		String header[] = { "날짜", "거주지", "핸드폰 번호", "비고" };
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		if (lists != null) {
			for (List list : lists) {
				String u[] = new String[4];
				u[0] = list.getDate().toString();
				u[1] = list.getAdd();
				u[2] = list.getpNumber();
				u[3] = list.getEtc();

				tableModel.addRow(u);
			}
		}
		
		table.setModel(tableModel);
	}

}
