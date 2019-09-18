package gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import calculations.imp.CombinationLoops;

/**
 * Combination of loop info panel.
 * @author Amr
 *
 */
public class CombInfoPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 957989267885615651L;
	/**
	 * The padding of the frame.
	 */
	private static final int PADDING = 10,
			FONT_SIZE = 13, PANE_WIDTH = 100,
					PANE_HEIGHT = 200;
	/**
	 * Start label.
	 */
	private JLabel intro;
	/**
	 * Forward Label.
	 */
	private JLabel combLabel, gainLabel;
	/**
	 * The loop table.
	 */
	private JTable loopTable;
	/**
	 * Pane of the loop table.
	 */
	private JScrollPane loopPane;
	/**
	 * Columns in the table.
	 */
	private static final String[] COLUMNS = {
		"Loop label", "Loop gain"	
	};
	/**
	 * Static constants.
	 */
	private static final String LABEL_PREFIX = "Combination level : ",
			GAIN_PREFIX = "Loop combination gain : ";
	/**
	 * Constructor.
	 */
	public CombInfoPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.BLACK),
				new EmptyBorder(PADDING, PADDING, PADDING, PADDING)));
		Font font = new Font(Font.SANS_SERIF,
				Font.BOLD,
				FONT_SIZE);
		intro = new JLabel("Loop combination info panel");
		intro.setFont(font);
		intro.setAlignmentX(CENTER_ALIGNMENT);
		JPanel introP  = new JPanel();
		introP.setLayout(new BoxLayout(introP, BoxLayout.Y_AXIS));
		introP.add(intro);
		JPanel pP  = new JPanel();
		pP.setAlignmentX(CENTER_ALIGNMENT);
		introP.setAlignmentX(CENTER_ALIGNMENT);
		pP.setLayout(new BoxLayout(pP, BoxLayout.Y_AXIS));
		DefaultTableModel loopTableModel = new DefaultTableModel();
		loopTableModel.setColumnIdentifiers(COLUMNS);
		loopTable = new JTable(loopTableModel);
		combLabel = new JLabel();
		gainLabel = new JLabel();
		loopPane = new JScrollPane();
		Dimension d = new Dimension(PANE_WIDTH, PANE_HEIGHT);
		Dimension f = new Dimension(PANE_WIDTH * 2, PANE_HEIGHT);
		loopPane.setPreferredSize(d);
		loopPane.setMinimumSize(d);
		loopTable.setPreferredSize(d);
		loopTable.setMinimumSize(d);
		loopTable.setMaximumSize(f);
		loopPane.setMaximumSize(f);
		combLabel.setAlignmentX(LEFT_ALIGNMENT);
		gainLabel.setAlignmentX(LEFT_ALIGNMENT);
		loopPane.setAlignmentX(LEFT_ALIGNMENT);
		loopPane.add(loopTable);
		pP.add(combLabel);
		pP.add(gainLabel);
		pP.add(loopPane);
		this.setComb(null);
		this.add(introP);
		this.add(pP);
	}
	/**
	 * Sets the combination of loops to display its data.
	 * @param combLoop
	 * The combination of loops to display.
	 */
	public final void setComb(final CombinationLoops combLoop) {
		String label, gain;
		DefaultTableModel loopTableModel = new DefaultTableModel();
		loopTableModel.setColumnIdentifiers(COLUMNS);
		if (combLoop == null) {
			label = "Undefined";
			gain = "Undefined";
		} else {
			label = String.valueOf(combLoop.getLevel());
			gain = String.valueOf(combLoop.getGain());
			for (int i = 0; i < combLoop.getLoops().size(); i++) {
				String[] temp = new String[2];
				temp[0] = combLoop.getLoops().get(i).getLoopNodesName();
				temp[1] = String.valueOf(
						combLoop.getLoops().get(i).getLoopGain()
						);
				loopTableModel.addRow(temp);
			}
		}
		combLabel.setText(LABEL_PREFIX + label);
		gainLabel.setText(GAIN_PREFIX + gain);
		loopTable.setModel(loopTableModel);
		Point temp = loopPane.getViewport().getViewPosition();
		loopPane.setViewportView(loopTable);
		loopPane.getViewport().setViewPosition(temp);
	}
}
