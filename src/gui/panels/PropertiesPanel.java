package gui.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import data.DataCore;
/**
 * The properties panel.
 * @author Amr
 *
 */
public class PropertiesPanel extends JPanel implements LiveComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 527584900131071843L;
	/**
	 * Data core.
	 */
	private DataCore dataCore;
	/**
	 * List of items.
	 */
	private JList<String> listForwardPath, listLoops, listComb;
	/**
	 * Scroll panes.
	 */
	private JScrollPane forwardPane, loopPane, combPane;
	/**
	 * Labels.
	 */
	private JLabel forwardLabel, loopLabel, combLabel;
	/**
	 * The action listener.
	 */
	private ListSelectionListener actionListener;
	/**
	 * Prefered width.
	 */
	private static final int PANE_WIDTH = 100,
			PANE_HEIGHT = 200,
			PADDING = 10,
			FONT_SIZE = 20;
	/**
	 * Constructor.
	 * @param core
	 * The data core.
	 * @param listener
	 * The action listener.
	 */
	public PropertiesPanel(final DataCore core,
			final ListSelectionListener listener) {
		dataCore = core;
		actionListener = listener;
		forwardLabel = new JLabel("Forward Paths");
		forwardLabel.setAlignmentX(CENTER_ALIGNMENT);
		loopLabel = new JLabel("Loops");
		loopLabel.setAlignmentX(CENTER_ALIGNMENT);
		combLabel = new JLabel("Loop Combinations");
		combLabel.setAlignmentX(CENTER_ALIGNMENT);
		forwardPane = new JScrollPane();
		loopPane = new JScrollPane();
		combPane = new JScrollPane();
		Dimension d = new Dimension(PANE_WIDTH, PANE_HEIGHT);
		Dimension f = new Dimension(PANE_WIDTH * 2, PANE_HEIGHT);
		forwardPane.setPreferredSize(d);
		forwardPane.setMaximumSize(f);
		forwardPane.setMinimumSize(d);
		loopPane.setPreferredSize(d);
		loopPane.setMaximumSize(f);
		loopPane.setMinimumSize(d);
		combPane.setPreferredSize(d);
		combPane.setMaximumSize(f);
		combPane.setMinimumSize(d);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(PADDING,
				PADDING, PADDING, PADDING));
		JLabel prop = new JLabel("Properties");
		prop.setFont(new Font(Font.SANS_SERIF,
				Font.BOLD,
				FONT_SIZE));
		prop.setAlignmentX(CENTER_ALIGNMENT);
		this.add(prop);
		this.add(Box.createVerticalGlue());
		this.add(forwardLabel);
		this.add(forwardPane);
		this.add(loopLabel);
		this.add(loopPane);
		this.add(combLabel);
		this.add(combPane);
		this.add(Box.createVerticalGlue());
		this.refresh();
	}
	@Override
	public final void refresh() {
		 DefaultListModel<String> listModel
		 = new DefaultListModel<String>();
		 if (dataCore.getGraphCalculator().getForwardPaths() != null) {
			 for (int i = 1; i
					 <= dataCore.getGraphCalculator().getForwardPaths().size();
					 i++) {
				 listModel.addElement("Forward Path " + i);
			 }
		 }
		 this.setPane(forwardPane, listForwardPath, listModel, "forward");
		 listModel = new DefaultListModel<String>();
		 if (dataCore.getGraphCalculator().getLoops() != null) {
			 for (int i = 1; i
					 <= dataCore.getGraphCalculator().getLoops().size();
					 i++) {
				 listModel.addElement("Loop " + i);
			 }
		 }
		 this.setPane(loopPane, listLoops, listModel, "loop");
		 listModel = new DefaultListModel<String>();
		 if (dataCore.getGraphCalculator().getCombinationLoops() != null) {
			 for (int i = 1; i
					 <= dataCore.getGraphCalculator()
					 .getCombinationLoops().size();
					 i++) {
				 listModel.addElement("Loop Combination["
					 + dataCore.getGraphCalculator()
					 .getCombinationLoops().get(i - 1).getLevel()
					 + "] " + i);
			 }
		 }
		 this.setPane(combPane, listComb, listModel, "comb");
	}
	/**
	 * Sets the pane to the according list of a model.
	 * @param pane
	 * The scroll pane.
	 * @param list
	 * The JList to show scroll pane.
	 * @param model
	 * The model to put in the list.
	 * @param name
	 * The name of the list.
	 */
	private void setPane(final JScrollPane pane, JList<String> list,
			final DefaultListModel<String> model, final String name) {
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(actionListener);
		list.setName(name);
		Point temp = pane.getViewport().getViewPosition();
		pane.setViewportView(list);
		pane.getViewport().setViewPosition(temp);
	}
}
