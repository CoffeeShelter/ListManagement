package listmanagement.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CircleButton extends JButton {
	private Color backgroundColor =  new Color(255, 247, 242); // ���� ����
	private Color fontColor = new Color(247, 99, 12); // ���ڻ� ����
	
	public CircleButton() {
		super();
		decorate();
	}

	public CircleButton(String text) {
		super(text);
		decorate();
	}

	public CircleButton(Action action) {
		super(action);
		decorate();
	}

	public CircleButton(Icon icon) {
		super(icon);
		decorate();
	}

	public CircleButton(String text, Icon icon) {
		super(text, icon);
		decorate();
	}
	
	public CircleButton(Color bgColor, Color fontColor) {
		super();
		this.backgroundColor = bgColor;
		this.fontColor = fontColor;
		decorate();
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color c = backgroundColor;
		Color o = fontColor;
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (getModel().isArmed()) {
			graphics.setColor(c.darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(c.brighter());
		} else {	
			graphics.setColor(c);
		}

		graphics.fillOval(0, 0, width, height);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(o);
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}
