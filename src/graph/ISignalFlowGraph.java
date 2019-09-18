package graph;

import java.util.List;

import graph.node.IEdge;
import graph.node.INode;


/**
 * 
 * @author Michael.
 *
 */
public interface ISignalFlowGraph {
	/**
	 * Adds a node to the graph.
	 * @param str
	 * The name of the node to add to the graph.
	 * @return
	 * True if it is added.
	 */
	boolean addNode(String str);
	/**
	 * Adds an edge to the graph.
	 * @param src
	 * The name of the src node.
	 * @param dest
	 * The name of the destination node.
	 * @param gain
	 * The gain of the edge.
	 * @return
	 * True if the edge is added, false if it
	 * isn't permitted to add the specified edge.
	 */
	boolean addEdge(String src, String dest, double gain);
	/**
	 * Adds an input node.
	 * @param str
	 * The name of the node to be the input node.
	 * @return
	 * True if it is added.
	 */
	boolean addInputNode(String str);
	/**
	 * Sets the input node of signal flow graph to a specified node
	 * that is already in the SFG.
	 * @param str
	 * The name of the node to set as input node that it is already in the SFG.
	 * @return
	 * True if it is made as the input node.
	 */
	boolean setInputNode(String str);
	/**
	 * Deletes an edge of the SFG.
	 * @param src
	 * The edge to delete.
	 * @param dest
	 * The name of the edge to delete.
	 * @param gain
	 * The gain of the edge to delete.
	 * @return
	 * True if deleted.
	 */
	boolean removeEdge(String src, String dest, double gain);
	/**
	 * Deletes a node of the SFG with its edges.
	 * @param str
	 * The name of the node to delete.
	 * @return
	 * True if deleted.
	 */
	boolean removeNode(String str);
	/**
	 * Returns the input node.
	 * @return
	 * input node.
	 */
	INode getInputNode();
	/**
	 * Returns the list of nodes.
	 * @return
	 * List of nodes.
	 */
	List<INode> getNodes();
	/**
	 * Returns the list of edges.
	 * @return
	 * List of edges.
	 */
	List<IEdge> getEdges();
}
