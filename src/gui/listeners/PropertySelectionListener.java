package gui.listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.DataCore;
import gui.panels.InformationPanel;
/**
 * Selection listener.
 * @author Amr
 *
 */
public class PropertySelectionListener implements ListSelectionListener {
	/**
	 * The data core.
	 */
	private DataCore dataCore;
	/**
	 * The information panel.
	 */
	private InformationPanel infoPanel;
	/**
	 * Constructor.
	 * @param core
	 * The data core to get information from.
	 * @param panel
	 * The gui information panel to display information on.
	 */
	public PropertySelectionListener(final DataCore core,
			final InformationPanel panel) {
		dataCore = core;
		infoPanel = panel;
	}
	@Override
	public final void valueChanged(final ListSelectionEvent arg0) {
		if (arg0.getSource() instanceof JList) {
			@SuppressWarnings("rawtypes")
			JList list = (JList)
					arg0.getSource();
			if (list.getName().equals("forward")) {
				if (list.getSelectedIndex() == -1) {
					infoPanel.setForwardPath(null);
				} else {
					infoPanel.setForwardPath(
							dataCore.getGraphCalculator().
							getForwardPaths().get(list.getSelectedIndex()));
				}
			} else if (list.getName().equals("loop")) {
				if (list.getSelectedIndex() == -1) {
					infoPanel.setLoop(null);
				} else {
					infoPanel.setLoop(dataCore.getGraphCalculator()
							.getLoops().get(list.getSelectedIndex()));
				}
			} else if (list.getName().equals("comb")) {
				if (list.getSelectedIndex() == -1) {
					infoPanel.setComb(null);
				} else {
					infoPanel.setComb(dataCore.getGraphCalculator()
							.getCombinationLoops().get(
									list.getSelectedIndex()));
				}
			}
		}
	}

}
