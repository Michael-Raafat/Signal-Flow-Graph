package gui.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calculations.elements.ILoop;
/**
 * Loop info panel.
 * @author Amr
 *
 */
public class LoopInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2692791202512022754L;
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
	private JLabel loopLabel, gainLabel;
	/**
	 * Static constants.
	 */
	private static final String LABEL_PREFIX = "Loop label : ",
			GAIN_PREFIX = "Loop gain : ";
	/**
	 * Constructor.
	 */
	public LoopInfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
		Font font = new Font(Font.SANS_SERIF,
				Font.BOLD,
				FONT_SIZE);
		intro = new JLabel("Loop path info panel");
		intro.setFont(font);
		intro.setAlignmentX(CENTER_ALIGNMENT);
		JPanel introP  = new JPanel();
		introP.setLayout(new BoxLayout(introP, BoxLayout.Y_AXIS));
		introP.add(intro);
		JPanel pP  = new JPanel();
		pP.setAlignmentX(CENTER_ALIGNMENT);
		introP.setAlignmentX(CENTER_ALIGNMENT);
		pP.setLayout(new BoxLayout(pP, BoxLayout.Y_AXIS));
		loopLabel = new JLabel();
		gainLabel = new JLabel();
		loopLabel.setAlignmentX(LEFT_ALIGNMENT);
		gainLabel.setAlignmentX(LEFT_ALIGNMENT);
		pP.add(loopLabel);
		pP.add(gainLabel);
		this.setLoop(null);
		this.add(introP);
		this.add(pP);
	}
	/**
	 * Sets the forward path to display its data.
	 * @param loop
	 * The loop to display.
	 */
	public final void setLoop(final ILoop loop) {
		String label, gain;
		if (loop == null) {
			label = "Undefined";
			gain = "Undefined";
		} else {
			label = loop.getLoopNodesName();
			gain = String.valueOf(loop.getLoopGain());
		}
		loopLabel.setText(LABEL_PREFIX + label);
		gainLabel.setText(GAIN_PREFIX + gain);
	}
}
