package listmanagement.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectorAction implements ActionListener{

	private JPanel showPanel;
	private CardLayout cardLayout;
	private String selectionName;
	private JLabel headerLabel;
	
	public SelectorAction(JPanel panel, JLabel headerLabel, String buttonName) {
		this.showPanel = panel;
		this.cardLayout = (CardLayout) panel.getLayout();
		this.selectionName = buttonName;
		this.headerLabel = headerLabel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.cardLayout.show(showPanel, selectionName);
		this.headerLabel.setText(selectionName);
	}

}
