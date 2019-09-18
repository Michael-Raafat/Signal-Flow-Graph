package gui.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculations.elements.IForwardPath;
/**
 * The forward path info panel.
 * @author Amr
 *
 */
public class ForwardPathInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8426662622500491244L;
	/**
	 * The padding of the frame.
	 */
	private static final int PADDING = 10,
			FONT_SIZE = 13;
	/**
	 * Start label.
	 */
	private JLabel intro;
	/**
	 * Forward Label.
	 */
	private JLabel forwardLabel, gainLabel, deltaLabel;
	/**
	 * Static constants.
	 */
	private static final String LABEL_PREFIX = "Forward path label : ",
			GAIN_PREFIX = "Forward path gain : ",
			DELTA_PREFIX = "\u0394 of forward path : ";
	/**
	 * Constructor.
	 */
	public ForwardPathInfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
		Font font = new Font(Font.SANS_SERIF,
				Font.BOLD,
				FONT_SIZE);
		intro = new JLabel("Forward path info panel");
		intro.setFont(font);
		intro.setAlignmentX(CENTER_ALIGNMENT);
		JPanel introP  = new JPanel();
		introP.setLayout(new BoxLayout(introP, BoxLayout.Y_AXIS));
		introP.add(intro);
		JPanel pP  = new JPanel();
		pP.setAlignmentX(CENTER_ALIGNMENT);
		introP.setAlignmentX(CENTER_ALIGNMENT);
		pP.setLayout(new BoxLayout(pP, BoxLayout.Y_AXIS));
		forwardLabel = new JLabel();
		gainLabel = new JLabel();
		deltaLabel = new JLabel();
		forwardLabel.setAlignmentX(LEFT_ALIGNMENT);
		gainLabel.setAlignmentX(LEFT_ALIGNMENT);
		deltaLabel.setAlignmentX(LEFT_ALIGNMENT);
		pP.add(forwardLabel);
		pP.add(gainLabel);
		pP.add(deltaLabel);
		this.setForwardPath(null);
		this.add(introP);
		this.add(pP);
	}
	/**
	 * Sets the forward path to display its data.
	 * @param path
	 * The path to display.
	 */
	public final void setForwardPath(final IForwardPath path) {
		String label, gain, delta;
		if (path == null) {
			label = "Undefined";
			gain = "Undefined";
			delta = "Undefined";
		} else {
			label = path.getForwardPathName();
			gain = String.valueOf(path.getForwardPathGain());
			delta = String.valueOf(path.getDeltaOfPath());
		}
		forwardLabel.setText(LABEL_PREFIX + label);
		gainLabel.setText(GAIN_PREFIX + gain);
		deltaLabel.setText(DELTA_PREFIX + delta);
	}
}
