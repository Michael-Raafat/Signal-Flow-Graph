package gui.panels;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DataCore;
/**
 * The status panel.
 * @author Amr
 *
 */
public class StatusPanel extends JPanel implements LiveComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7751546713998407856L;
	/**
	 * The labels.
	 */
	private JLabel inputNode, outputNode, delta, transferFunction;
	/**
	 * String prefixes.
	 */
	private static final String INPUT_PREFIX = "Input node : ",
			OUTPUT_PREFIX = "Output node : ",
			TRANSFER_PREFIX = "Transfer function : ",
			DELTA_PREFIX = "\u0394 : ";
	/**
	 * The datacore.
	 */
	private DataCore dataCore;
	/**
	 * Constructor.
	 * @param core
	 * The data core.
	 */
	public StatusPanel(final DataCore core) {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		dataCore = core;
		inputNode = new JLabel();
		outputNode = new JLabel();
		delta = new JLabel();
		transferFunction = new JLabel();
		this.add(inputNode);
		this.add(outputNode);
		this.add(delta);
		this.add(transferFunction);
		this.refresh();
	}

	@Override
	public final void refresh() {
		String inT, ouT, dT, tT;
		if (dataCore.getSfgGraph().getInputNode() == null) {
			inT = "Undefined";
		} else {
			inT = dataCore.getSfgGraph().getInputNode().getLabel();
		}
		if (dataCore.getGraphCalculator().getOutputNode() == null) {
			ouT = "Undefined";
		} else {
			ouT = dataCore.getGraphCalculator().getOutputNode().getLabel();
		}
		if (dataCore.getGraphCalculator().getForwardPaths() == null
				|| dataCore.getGraphCalculator()
				.getForwardPaths().size() == 0) {
			tT = "Undefined";
			dT = "Undefined";
		} else {
			tT = String.valueOf(
					dataCore.getGraphCalculator().getTransferFunction());
			dT = String.valueOf(
					dataCore.getGraphCalculator().getDelta());
		}
		inputNode.setText(INPUT_PREFIX
				+ inT
				+ " | ");
		outputNode.setText(OUTPUT_PREFIX
				+ ouT
				+ " | ");
		delta.setText(DELTA_PREFIX
				+ dT
				+ " | ");
		transferFunction.setText(TRANSFER_PREFIX
				+ tT);
	}
}
