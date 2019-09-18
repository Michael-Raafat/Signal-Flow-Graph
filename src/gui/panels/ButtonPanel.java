package gui.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.listeners.ButtonAction;

/**
 * Button panel.
 * @author Amr
 *
 */
public class ButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1869535166207654639L;
	/**
	 * The button size.
	 */
	private static final int BUTTON_SIZE = 50,
			H_GAP = 30,
			V_GAP = 5;
	/**
	 * Icons of the buttons.
	 */
	private static final String[] ICONS = {
		"addNode.bmp", 	"addEdge.bmp",
		"deleteNode.png", "deleteEdge.png",
		"generateNodes.png", "input.png",
		"output.png", "clear.png", "close.jpg"
	};
	/**
	 * Tool tip strings.
	 */
	private static final String[] TOOL_TIPS = {
			"Add node", "Add edge", "Delete node",
			"Delete edge", "Generate nodes",
			"Choose input node", "Choose Output node",
			"Clear graph", "Close program"
	};
	/**
	 * The button actions.
	 */
	private static final ButtonAction[] ACTIONS = {
		ButtonAction.ADD_NODE, ButtonAction.ADD_EDGE, ButtonAction.REMOVE_NODE,
		ButtonAction.REMOVE_EDGE, ButtonAction.GENEREATE_NODES,
		ButtonAction.CHOOSE_INPUT, ButtonAction.CHOOSE_OUTPUT,
		ButtonAction.CLEAR, ButtonAction.EXIT
	};
	/**
	 * Constructor.
	 * @param listener
	 * The action listener for the buttons.
	 */
	public ButtonPanel(final ActionListener listener) {
		this.setLayout(new FlowLayout(
				FlowLayout.CENTER,
				H_GAP,
				V_GAP));
		this.setBorder(BorderFactory.createEtchedBorder());
		Dimension d = new Dimension(BUTTON_SIZE, BUTTON_SIZE);
		for (int i = 0; i < ICONS.length; i++) {
			ImageIcon icon = new ImageIcon("." + File.separator + "Icons" + File.separator
					+ ICONS[i]);
			icon = resizeIcon(icon);
			JButton temp = new JButton(icon);
			temp.setToolTipText(TOOL_TIPS[i]);
			temp.addActionListener(listener);
			temp.setPreferredSize(d);
			temp.setActionCommand(ACTIONS[i].toString());
			this.add(temp);
		}
	}
	/**
	 * Resizes an icon and returns the resized icon.
	 * @param icon
	 * The image icon to resize.
	 * @return
	 * The resized image icon.
	 */
	private ImageIcon resizeIcon(final ImageIcon icon) {
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(BUTTON_SIZE,
				BUTTON_SIZE,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
}
