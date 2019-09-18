package graph.node;
/**
 * The interface of an edge.
 * @author Amr
 *
 */
public interface IEdge {
	/**
	 * Gets the origin of the edge.
	 * @return
	 * The node that the edge originates from.
	 */
	INode getOrigin();
	/**
	 * Gets the destination of the edge.
	 * @return
	 * The node that the edge goes to.
	 */
	INode getDestination();
	/**
	 * Gets the gain of the edge.
	 * @return
	 * The value of the gain.
	 */
	double getValue();
	/**
	 * Sets the origin node of the edge.
	 * @param org
	 * The new origin node.
	 */
	void setOrigin(INode org);
	/**
	 * Sets the destination node of the edge.
	 * @param dest
	 * The new destination node.
	 */
	void setDestination(INode dest);
	/**
	 * Sets the value of the edge.
	 * @param val
	 * The new value of the edge.
	 */
	void setValue(double val);
	/**
	 * Function to check if this edge is equal to another one.
	 * @param edge
	 * The edge to test its equality.
	 * @return
	 * True if it is equal.
	 */
	boolean equals(Object edge);
}
