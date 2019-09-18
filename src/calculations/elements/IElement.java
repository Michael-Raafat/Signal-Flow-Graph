package calculations.elements;

import java.util.List;

import graph.node.IEdge;
import graph.node.INode;

/**
 * Interface of calculation element.
 * @author Amr
 *
 */
public interface IElement {
	/**
	 * Returns the list of nodes contained in the element.
	 * @return
	 * A list of nodes of the element.
	 */
	List<INode> getNodes();
	/**
	 * Return the list edges contained in the element.
	 * @return
	 * A list of edges of the element.
	 */
	List<IEdge> getEdges();
}
