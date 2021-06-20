package listmanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MainWindow {
	public MainWindow() {
		JFrame frame = new JFrame();
		frame.setTitle("�ڷγ� ���� ��� �ۼ� ���α׷�");
		frame.setSize(780,100);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		topPanel.setPreferredSize(new Dimension(780, 20));
		topPanel.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.RED);
		centerPanel.setPreferredSize(new Dimension(780, 100));
		centerPanel.setLayout(new BorderLayout());
		frame.add(centerPanel, BorderLayout.CENTER);
		
		// ���� ��
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setPreferredSize(new Dimension(780,20));
		frame.add(bottomPanel,BorderLayout.SOUTH);
		
		JTextField addField = new JTextField();
		addField.setPreferredSize(new Dimension(200,50));
		centerPanel.add(addField);
		TitledBorder addBorder = new TitledBorder("������");
		addField.setBorder(addBorder);
		
		JTextField pNumField = new JTextField();
		pNumField.setPreferredSize(new Dimension(200,50));
		centerPanel.add(pNumField);
		TitledBorder pNumBorder = new TitledBorder("�ڵ��� ��ȣ");
		pNumField.setBorder(pNumBorder);
		
		JTextField etcField = new JTextField();
		etcField.setPreferredSize(new Dimension(200,50));
		centerPanel.add(etcField);
		TitledBorder etcBorder = new TitledBorder("��Ÿ����");
		etcField.setBorder(etcBorder);
		
		JButton button = new JButton("�Է�");
		button.setPreferredSize(new Dimension(80,30));
		centerPanel.add(button);
		
		frame.setVisible(true);
	}
}
