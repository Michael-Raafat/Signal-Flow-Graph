package gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.graphstream.graph.Edge;

import data.DataCore;
import graph.node.IEdge;
import graph.node.INode;
import graph.node.imp.Node;
import gui.panels.LiveComponent;
/**
 * Action listener implementation.
 * @author Amr
 *
 */
public class ButtonListener implements ActionListener {
	/**
	 * Components to referesh after each action.
	 */
	private List<LiveComponent> componentList;
	/**
	 * ID.
	 */
	private int id;
	/**
	 * The data core.
	 */
	private DataCore dataCore;
	/**
	 * The parent frame.
	 */
	private JFrame parent;
	/**
	 * Constructor.
	 * @param components
	 * The list of components to referesh after each action.
	 * @param core
	 * The data core.
	 * @param frame
	 * The parent frame.
	 */
	public ButtonListener(final JFrame frame,
			final DataCore core,
			final List<LiveComponent> components) {
		parent = frame;
		componentList = components;
		dataCore = core;
		id = 0;
	}
	@Override
	public final void actionPerformed(final ActionEvent e) {
		ButtonAction action = ButtonAction.parseString(e.getActionCommand());
		String ans;
		switch (action) {
			case ADD_NODE :
				ans = JOptionPane.showInputDialog(parent,
						"Enter your node name : ");
				if (ans != null && ans.matches("[a-zA-Z0-9]+")) {
					if (addNode(ans)) {
						refresh();
					} else {
						JOptionPane.showMessageDialog(parent,
								"Duplicate node can't be added", "Error", 1);
					}
				} else if (ans != null) {
					JOptionPane.showMessageDialog(parent,
							"Invalid input. Node not added", "Error", 1);
				}
				break;
			case ADD_EDGE :
				addEdgeAction();
				break;
			case REMOVE_EDGE :
				removeEdgeAction();
				break;
			case REMOVE_NODE :
				if (nodeArray().length != 0) {
					ans = (String) JOptionPane.showInputDialog(parent,
							"Choose the node to delete : ",
							"Delete node",
							0,
							null,
							nodeArray(),
							nodeArray()[0]
							);
					if (ans != null) {
						dataCore.getGraph().removeNode(ans);
						dataCore.getSfgGraph().removeNode(ans);
						refresh();
					}
				} else {
					JOptionPane.showMessageDialog(parent,
							"No nodes to delete.", "Error", 1);
				}
				break;
			case GENEREATE_NODES :
				ans = JOptionPane.showInputDialog(parent,
						"Enter the number of nodes wanted : ");
				if (ans != null && ans.matches("[1-9]+[0-9]*")) {
					int wanted = Integer.parseInt(ans);
					int i = 0;
					int f = 0;
					while (i < wanted) {
						if (addNode("x" + f)) {
							i++;
						}
						f++;
					}
					refresh();
				} else if (ans != null) {
					JOptionPane.showMessageDialog(parent,
							"Invalid input. Nodes not added", "Error", 1);
				}
				break;
			case CHOOSE_INPUT :
				if (nodeArray().length != 0) {
					ans = (String) JOptionPane.showInputDialog(parent,
							"Choose the node to assign input : ",
							"Input node",
							JOptionPane.QUESTION_MESSAGE,
							null,
							nodeArray(),
							nodeArray()[0]
							);
					if (ans != null) {
						if (dataCore.getSfgGraph().setInputNode(ans)) {
							refresh();
						} else {
							JOptionPane.showMessageDialog(parent,
									"Non-Valid input node.", "Error", 1);
						}
					}
				} else {
					JOptionPane.showMessageDialog(parent,
							"No nodes to choose from.", "Error", 1);
				}
				break;
			case CHOOSE_OUTPUT :
				if (nodeArray().length != 0) {
					ans = (String) JOptionPane.showInputDialog(parent,
							"Choose the node to assign output : ",
							"Output node",
							JOptionPane.QUESTION_MESSAGE,
							null,
							nodeArray(),
							nodeArray()[0]
							);
					if (ans != null) {
						dataCore.getGraphCalculator().setOutputNode(
								new Node(ans));
						refresh();
					}
				} else {
					JOptionPane.showMessageDialog(parent,
							"No nodes to choose from.", "Error", 1);
				}
				break;
			case CLEAR :
				dataCore.clear();
				refresh();
				break;
			case EXIT :
				System.exit(0);
				break;
			default:
				break;
		}
	}
	/**
	 * Refreshes all the live components.
	 */
	private void refresh() {
		dataCore.getGraphCalculator().calculateProperties();
		for (int i = 0; i < componentList.size(); i++) {
			componentList.get(i).refresh();
		}
	}
	/**
	 * Adds a node to the graphs.
	 * @param str
	 * The identifier of the node.
	 * @return
	 * True if added.
	 */
	private boolean addNode(final String str) {
		if (dataCore.getSfgGraph().addNode(str)) {
			dataCore.getGraph().addNode(str);
			dataCore.getGraph().getNode(str).addAttribute("ui.label",
					str);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Array of the labels of nodes.
	 * @return
	 * String array.
	 */
	private String[] nodeArray() {
		List<INode> nodes = dataCore.getSfgGraph().getNodes();
		String[] temp = new String
				[nodes.size()];
		for (int i = 0; i < nodes.size(); i++) {
			temp[i] = nodes.get(i).getLabel();
		}
		return temp;
	}
	/**
	 * Array of the labels of nodes.
	 * @return
	 * String array.
	 */
	private String[] edgeArray() {
		List<IEdge> edges = dataCore.getSfgGraph().getEdges();
		String[] temp = new String
				[edges.size()];
		for (int i = 0; i < edges.size(); i++) {
			temp[i] = edges.get(i).getOrigin().getLabel()
					+ "---"
					+ edges.get(i).getValue()
					+ "-->"
					+ edges.get(i).getDestination().getLabel();
		}
		return temp;
	}
	/**
	 * The action of the add edge button.
	 */
	private void addEdgeAction() {
		if (nodeArray().length != 0) {
			JLabel source = new JLabel("Source : "),
				destination = new JLabel("Destination : "),
				gain = new JLabel("Gain : ");
			JComboBox<String> sourceChoice = new JComboBox<String>(
					nodeArray()),
					destinationChoice = new JComboBox<String>(
							nodeArray());
			JTextField gainField = new JTextField();
			JComponent[] arr = {
				source, sourceChoice, destination, destinationChoice,
				gain, gainField
			};
			int response = JOptionPane.showConfirmDialog(parent,
					arr,
					"Add edge",
					JOptionPane.OK_CANCEL_OPTION);
			if (response == JOptionPane.OK_OPTION) {
				if (gainField.getText().matches("-?[1-9]+[0-9]*(.[0-9]+)?")) {
					Double gainV = Double.parseDouble(
							gainField.getText());
					if (dataCore.getSfgGraph().addEdge(
							(String) sourceChoice.getSelectedItem(),
							(String) destinationChoice.
							getSelectedItem(),
							gainV)) {
						Edge ed = dataCore.getGraph().addEdge(
								String.valueOf(id),
								(String) sourceChoice.getSelectedItem(),
								(String) destinationChoice.
								getSelectedItem(),
								true);
						ed.addAttribute("ui.label", gainField.getText());
						ed.addAttribute("gain", String.valueOf(gainV));
						id++;
						refresh();
					} else {
						JOptionPane.showMessageDialog(parent,
								"Invalid edge. Might be "
								+ "an edge with the input node as"
								+ " destination.",
								"Error", 1);
					}
				} else {
					JOptionPane.showMessageDialog(parent,
							"Invalid gain. Must be an integer",
							"Error", 1);
				}
			}
		} else {
			JOptionPane.showMessageDialog(parent,
					"No nodes to add an edge", "Error", 1);
		}
	}
	/**
	 * Action taken on remove edge.
	 */
	private void removeEdgeAction() {
		String ans;
		if (edgeArray().length != 0) {
			ans = (String) JOptionPane.showInputDialog(parent,
					"Choose the edge to delete : ",
					"Delete edge",
					0,
					null,
					edgeArray(),
					edgeArray()[0]
					);
			if (ans != null) {
				String[] arr = ans.split("---|-->");
				if (dataCore.getSfgGraph().removeEdge(arr[0],
						arr[2],
						Double.parseDouble(arr[1]))) {
					for (int i = 0;
							i < dataCore.getGraph().getEdgeCount();
							i++) {
						Edge ed = dataCore.getGraph().getEdge(i);
						if (ed.getSourceNode().getId().equals(arr[0])
								&& ed.getAttribute("gain").equals(arr[1])
								&& ed.getTargetNode().getId()
								.equals(arr[2])) {
							dataCore.getGraph().removeEdge(i);
							break;
						}
					}
					refresh();
				} else {
					JOptionPane.showMessageDialog(parent,
							"Couldn't delete edge", "Error", 1);
				}
			}
		} else {
			JOptionPane.showMessageDialog(parent,
					"No edges to delete.", "Error", 1);
		}
	}
}
