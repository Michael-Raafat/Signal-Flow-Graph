package graph.node.imp;

import java.util.ArrayList;
import java.util.List;

import graph.node.IEdge;
import graph.node.INode;
/**
 * Implementation of a node.
 * @author Amr
 *
 */
public class Node implements INode {
	/**
	 * Name of the node.
	 */
	private String name;
	/**
	 * List of edges.
	 */
	private List<IEdge> edges;
	/**
	 * Constructor of the node.
	 * @param label
	 * The label of the node.
	 */
	public Node(final String label) {
		name = label;
		edges = new ArrayList<IEdge>();
	}
	@Override
	public final void setLabel(final String label) {
		name = label;
	}

	@Override
	public final String getLabel() {
		return name;
	}

	@Override
	public final boolean equals(final Object node) {
		if (node instanceof INode) {
			return name.equals(((INode) node).getLabel());
		} else {
			return false;
		}
	}

	@Override
	public final List<IEdge> getEdges() {
		return edges;
	}

	@Override
	public final void addEdge(final IEdge edge) {
		edges.add(edge);
	}
	@Override
	public final void removeEdge(final IEdge edge) {
		edges.remove(edge);
	}

}
