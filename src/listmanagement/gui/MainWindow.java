package listmanagement.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.EncryptedDocumentException;

import com.sun.jmx.snmp.Timestamp;

import listmanagement.action.ListInputAction;
import listmanagement.action.SearchAction;
import listmanagement.action.SelectorAction;
import listmanagement.db.List;
import listmanagement.db.ListDAO;
import listmanagement.file.ExcelManagement;

public class MainWindow extends JFrame {
	private JTextField addTextField;
	private JTextField pNumTextField;
	private JTextField etcTextField;
	private JTextField textField;
	private JTable table;

	private JSpinner startDateSpinner;
	private JSpinner endDateSpinner;
	private JTextField pathField;

	private Vector<List> currentList = new Vector<>();

	public MainWindow() {
		setTitle("코로나 명부 관리 프로그램");
		setSize(1028, 760);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel westPanel = new JPanel();
		westPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selector",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		westPanel.setBackground(Color.DARK_GRAY);
		westPanel.setPreferredSize(new Dimension(314, 760));
		mainPanel.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(10, 1, 0, 20));

		RoundedButton writePageButton = new RoundedButton(new Color(50, 50, 50), new Color(250, 250, 250));
		writePageButton.setText("명부 작성");
		westPanel.add(writePageButton);

		RoundedButton searchPageButton = new RoundedButton(new Color(50, 50, 50), new Color(250, 250, 250));
		searchPageButton.setText("검색");
		westPanel.add(searchPageButton);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(0, 50));
		centerPanel.add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));

		JLabel headerLabel = new JLabel("명부 작성");

		headerLabel.setFont(new Font("굴림", Font.BOLD, 30));

		headerLabel.setFont(new Font("����", Font.BOLD, 30));

		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setPreferredSize(new Dimension(512, 0));
		headerPanel.add(headerLabel, BorderLayout.CENTER);

		JPanel viewPanel = new JPanel();
		viewPanel.setBackground(Color.LIGHT_GRAY);
		centerPanel.add(viewPanel, BorderLayout.CENTER);
		viewPanel.setLayout(new CardLayout(0, 0));

		writePageButton.addActionListener(new SelectorAction(viewPanel, headerLabel, writePageButton.getText()));
		searchPageButton.addActionListener(new SelectorAction(viewPanel, headerLabel, searchPageButton.getText()));

		JPanel writePanel = new JPanel();
		writePanel.setBackground(Color.LIGHT_GRAY);
		viewPanel.add(writePanel, "명부 작성");
		writePanel.setLayout(null);

		JPanel boxPanel = new JPanel();
		boxPanel.setBounds(12, 10, 676, 225);
		boxPanel.setLayout(null);
		writePanel.add(boxPanel);

		JLabel addLabel = new JLabel("주소");
		addLabel.setFont(new Font("굴림", Font.BOLD, 14));
		addLabel.setBounds(50, 68, 130, 15);
		addLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		boxPanel.add(addLabel);

		addTextField = new JTextField();
		addTextField.setBounds(195, 65, 191, 21);
		boxPanel.add(addTextField);
		addTextField.setColumns(10);

		JLabel pNumLabel = new JLabel("핸드폰 번호");
		pNumLabel.setFont(new Font("굴림", Font.BOLD, 14));
		pNumLabel.setBounds(50, 104, 130, 15);
		pNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		boxPanel.add(pNumLabel);

		pNumTextField = new JTextField();
		pNumTextField.setBounds(195, 101, 191, 21);
		boxPanel.add(pNumTextField);
		pNumTextField.setColumns(10);

		JLabel etcLabel = new JLabel("기타 사항");
		etcLabel.setFont(new Font("굴림", Font.BOLD, 14));
		etcLabel.setBounds(50, 142, 130, 15);
		etcLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		boxPanel.add(etcLabel);

		etcTextField = new JTextField();
		etcTextField.setBounds(195, 139, 191, 21);
		boxPanel.add(etcTextField);
		etcTextField.setColumns(10);

		RoundedButton button = new RoundedButton(new Color(50, 50, 50), new Color(250, 250, 250));
		button.setText("입력");
		button.setFont(new Font("굴림", Font.BOLD, 12));
		button.setBounds(289, 181, 97, 23);
		button.addActionListener(new ListInputAction(addTextField, pNumTextField, etcTextField));
		boxPanel.add(button);

		JLabel exAdd = new JLabel("ex) 봉명동");
		exAdd.setFont(new Font("굴림", Font.BOLD, 14));
		exAdd.setBounds(400, 68, 200, 15);
		boxPanel.add(exAdd);

		JLabel expNum = new JLabel("ex) 010-1234-5678");
		expNum.setFont(new Font("굴림", Font.BOLD, 14));
		expNum.setBounds(400, 104, 200, 15);
		boxPanel.add(expNum);

		JLabel exEtc = new JLabel("ex) 2주 전 해외 방문");
		exEtc.setFont(new Font("굴림", Font.BOLD, 14));
		exEtc.setBounds(400, 142, 200, 15);
		boxPanel.add(exEtc);

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.LIGHT_GRAY);
		viewPanel.add(searchPanel, "검색");
		searchPanel.setLayout(new BorderLayout(0, 0));

		JPanel searchNorthPanel = new JPanel();
		searchNorthPanel.setBackground(Color.GRAY);
		searchPanel.add(searchNorthPanel, BorderLayout.NORTH);
		searchNorthPanel.setPreferredSize(new Dimension(0, 100));
		searchNorthPanel.setLayout(null);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date value = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);

		calendar.add(Calendar.YEAR, -50);
		Date start = calendar.getTime();
		calendar.add(Calendar.YEAR, 50);

		calendar.add(Calendar.YEAR, 50);
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);

		SpinnerDateModel startDateModel = new SpinnerDateModel(value, start, end, Calendar.YEAR);
		startDateSpinner = new JSpinner(startDateModel);
		startDateSpinner.setFont(new Font("굴림", Font.BOLD, 12));
		startDateSpinner.setBounds(56, 10, 212, 22);

		startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy/MM/dd")); // 날짜 편집기 지정

		startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy/MM/dd")); // ��¥ ������ ����

		searchNorthPanel.add(startDateSpinner);

		JLabel label = new JLabel("~");
		label.setFont(new Font("굴림", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(280, 13, 43, 15);
		searchNorthPanel.add(label);

		Date endValue = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);
		Date endStart = calendar.getTime();

		calendar.add(Calendar.YEAR, 100);
		Date endEnd = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);

		SpinnerDateModel endDateModel = new SpinnerDateModel(endValue, endStart, endEnd, Calendar.YEAR);
		endDateSpinner = new JSpinner(endDateModel);
		endDateSpinner.setFont(new Font("굴림", Font.BOLD, 12));

		endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy/MM/dd")); // 날짜 편집기 지정

		endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy/MM/dd")); // ��¥ ������ ����

		searchNorthPanel.add(endDateSpinner);
		endDateSpinner.setBounds(335, 10, 212, 22);

		RoundedButton searchButton = new RoundedButton(new Color(50, 50, 50), new Color(250, 250, 250));
		searchButton.setText("검색");
		searchButton.setBounds(592, 10, 80, 80);
		searchNorthPanel.add(searchButton);

		textField = new JTextField();
		textField.setBounds(335, 65, 212, 21);
		searchNorthPanel.add(textField);
		textField.setColumns(10);

		JLabel infoLabel = new JLabel("주소:");
		infoLabel.setFont(new Font("굴림", Font.BOLD, 14));
		infoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		infoLabel.setBounds(217, 68, 106, 15);
		searchNorthPanel.add(infoLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 49, 573, 2);
		searchNorthPanel.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		searchPanel.add(scrollPane, BorderLayout.CENTER);

		String header[] = { "날짜", "거주지", "핸드폰 번호", "비고" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		ListDAO listDAO = new ListDAO();
		Vector<List> listVector = new Vector<>();
		listVector = listDAO.getList();
		currentList = listVector;

		if (listVector != null) {
			for (List list : listVector) {
				String u[] = new String[4];
				u[0] = list.getDate().toString();
				u[1] = list.getAdd();
				u[2] = list.getpNumber();
				u[3] = list.getEtc();

				tableModel.addRow(u);
			}
		}

		table = new JTable();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);

		textField.addActionListener(new SearchAction(table, startDateSpinner, endDateSpinner, textField, this));
		searchButton
				.addActionListener(new SearchAction(table, startDateSpinner, endDateSpinner, textField, this));

		RoundedButton refreshButton = new RoundedButton(new Color(50, 50, 50), new Color(250, 250, 250));
		refreshButton.setText("새로고침");
		refreshButton.setBounds(56, 64, 86, 23);
		searchNorthPanel.add(refreshButton);

		JPanel searchSouthPanel = new JPanel();
		searchSouthPanel.setBorder(
				new TitledBorder(null, "엑셀 파일로 내보내기", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		searchSouthPanel.setBackground(Color.GRAY);
		searchSouthPanel.setPreferredSize(new Dimension(0, 80));
		searchPanel.add(searchSouthPanel, BorderLayout.SOUTH);
		searchSouthPanel.setLayout(null);

		pathField = new JTextField();
		pathField.setText("");
		pathField.setBounds(56, 33, 362, 21);
		searchSouthPanel.add(pathField);
		pathField.setColumns(10);

		RoundedButton selectPathButton = new RoundedButton(new Color(100, 100, 100), new Color(250, 250, 250));
		selectPathButton.setText("경로 선택");
		selectPathButton.setBounds(430, 32, 97, 23);
		searchSouthPanel.add(selectPathButton);

		selectPathButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = selectPath();
				String path = file.getPath();
				path += "\\list.xlsx";
				pathField.setText(path);
			}
		});

		RoundedButton saveButton = new RoundedButton(new Color(50, 50, 50), new Color(250, 250, 250));
		saveButton.setText("저장");
		saveButton.setFont(new Font("굴림", Font.BOLD, 15));
		saveButton.setBounds(586, 18, 50, 50);
		searchSouthPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExcelManagement excel = new ExcelManagement(pathField.getText());
				try {
					excel.writeExcelFile(currentList);
				} catch (EncryptedDocumentException | IOException e1) {
					e1.printStackTrace();
				}
				pathField.setText("");
				JOptionPane.showMessageDialog(null, "저장 완료");
			}
		});

		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});

		JPanel writeListPanel = new JPanel();
		writeListPanel.setBackground(Color.LIGHT_GRAY);

		setVisible(true);
	}

	public void refreshTable() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		Date value = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);

		startDateSpinner.setValue(value);

		Date endValue = calendar.getTime();

		endDateSpinner.setValue(endValue);

		String header[] = { "날짜", "거주지", "핸드폰 번호", "비고" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		ListDAO listDAO = new ListDAO();
		Vector<List> listVector = new Vector<>();
		listVector = listDAO.getList();
		setCurrentList(listVector);

		if (listVector != null) {
			for (List list : listVector) {
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

	public File selectPath() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = jfc.showSaveDialog(null);
		if (returnVal == 0) {
			return jfc.getSelectedFile();
		}

		return null;
	}
	
	public void setCurrentList(Vector<List> lists) {
		this.currentList = lists;
	}
	
}
