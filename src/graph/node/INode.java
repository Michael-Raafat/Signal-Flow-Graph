package graph.node;

import java.util.List;

/**
 * 
 * @author Michael.
 *
 */
public interface INode {
	/**
	 * set name of the node.
	 * @param label
	 * The label of the node.
	 */
	void setLabel(String label);
	/**
	 * @return label of the node.
	 */
	String getLabel();
	/**
	 * checks if the Node is equal to another one.
	 * @param node
	 * The node to compare to.
	 * @return
	 * True if they are equal.
	 */
	boolean equals(Object node);
	/**
	 * Returns a list of the edges leaving the node.
	 * @return
	 * List of edges.
	 */
	List<IEdge> getEdges();
	/**
	 * adds an edge to the node.
	 * @param edge
	 * The edge to add to the node.
	 */
	void addEdge(IEdge edge);
	/**
	 * Removes edge from the node list.
	 * @param edge
	 * The edge to remove.
	 */
	void removeEdge(IEdge edge);
}
