package gui.panels;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import calculations.elements.IForwardPath;
import calculations.elements.ILoop;
import calculations.imp.CombinationLoops;
/**
 * Information panel.
 * @author Amr
 *
 */
public class InformationPanel extends JPanel implements LiveComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5187449302850290709L;
	/**
	 * Constants.
	 */
	private static final int PADDING = 10;
	/**
	 * The forward panel.
	 */
	private ForwardPathInfoPanel forwardPanel;
	/**
	 * The loop panel.
	 */
	private LoopInfoPanel loopPanel;
	/**
	 * The combination of loops info.
	 */
	private CombInfoPanel combPanel;
	/**
	 * Constructor.
	 */
	public InformationPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
				new EmptyBorder(PADDING,
				PADDING, PADDING, PADDING)));
		forwardPanel = new ForwardPathInfoPanel();
		loopPanel = new LoopInfoPanel();
		combPanel = new CombInfoPanel();
		this.add(Box.createVerticalGlue());
		this.add(forwardPanel);
		this.add(Box.createVerticalGlue());
		this.add(loopPanel);
		this.add(Box.createVerticalGlue());
		this.add(combPanel);
		this.add(Box.createVerticalGlue());
		this.refresh();
	}
	@Override
	public final void refresh() {
		forwardPanel.setForwardPath(null);
		loopPanel.setLoop(null);
		combPanel.setComb(null);
	}
	/**
	 * Sets the current forward path.
	 * @param forwardPath
	 * The forward path to display its data.
	 */
	public final void setForwardPath(final IForwardPath forwardPath) {
		forwardPanel.setForwardPath(forwardPath);
	}
	/**
	 * Sets the current loop path.
	 * @param loop
	 * The loop to display its data.
	 */
	public final void setLoop(final ILoop loop) {
		loopPanel.setLoop(loop);
	}
	/**
	 * Sets the current combination of loops path.
	 * @param comb
	 * The combination of loops to display its data.
	 */
	public final void setComb(final CombinationLoops comb) {
		combPanel.setComb(comb);
	}
}
