package graph.imp;

import java.util.ArrayList;
import java.util.List;

import graph.ISignalFlowGraph;
import graph.node.IEdge;
import graph.node.INode;
import graph.node.imp.Edge;
import graph.node.imp.Node;
/**
 * Signal flow graph.
 * @author Amr
 *
 */
public class SignalFlowGraph implements ISignalFlowGraph {
	/**
	 * list of nodes in the graph.
	 */
	private List<INode> nodes;
	/**
	 * The input node of the SFG.
	 */
	private INode inputNode;
	/**
	 * list of edges in the graph.
	 */
	private List<IEdge> edges;
	/**
	 * Constructor.
	 */
	public SignalFlowGraph() {
		nodes = new ArrayList<INode>();
		edges = new ArrayList<IEdge>();
	}
	@Override
	public final boolean addNode(final String str) {
		INode node = new Node(str);
		if (!nodes.contains(node)) {
			nodes.add(node);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final boolean addEdge(final String src,
			final String dest,
			final double gain) {
		INode srcNode = new Node(src),
				destNode = new Node(dest);
		if (nodes.contains(srcNode) && nodes.contains(destNode)) {
			srcNode = nodes.get(nodes.lastIndexOf(srcNode));
			destNode = nodes.get(nodes.lastIndexOf(destNode));
			IEdge edge = new Edge(srcNode,
					destNode,
					gain);
			if (!edge.getDestination().equals(inputNode)) {
				edges.add(edge);
				edge.getOrigin().addEdge(edge);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public final boolean addInputNode(final String str) {
		INode node = new Node(str);
		if (nodes.contains(node)) {
			return false;
		} else {
			inputNode = node;
			nodes.add(node);
			return true;
		}
	}

	@Override
	public final boolean setInputNode(final String str) {
		INode node = new Node(str);
		if (!nodes.contains(node)) {
			return false;
		}
		boolean input = true;
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getDestination().equals(node)) {
				input = false;
			}
		}
		if (input) {
			node = nodes.get(nodes.lastIndexOf(node));
			inputNode = node;
		}
		return input;
	}

	@Override
	public final boolean removeEdge(final String src,
			final String dest,
			final double gain) {
		IEdge edge = new Edge(new Node(src),
				new Node(dest),
				gain);
		if (edges.contains(edge)) {
			edge = edges.get(edges.lastIndexOf(edge));
			edges.remove(edge);
			edge.getOrigin().removeEdge(edge);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final boolean removeNode(final String str) {
		INode node = new Node(str);
		if (!nodes.contains(node)) {
			return false;
		} else {
			if (node.equals(inputNode)) {
				inputNode = null;
			}
			for (int i = edges.size() - 1; i > -1; i--) {
				if (edges.get(i).getDestination().equals(node)) {
					edges.get(i).getOrigin().removeEdge(edges.get(i));
				}
				if (edges.get(i).getOrigin().equals(node)
						|| edges.get(i).getDestination().equals(node)) {
					edges.remove(i);
				}
			}
			nodes.remove(node);
			return true;
		}
	}

	@Override
	public final INode getInputNode() {
		return inputNode;
	}

	@Override
	public final List<INode> getNodes() {
		return nodes;
	}

	@Override
	public final List<IEdge> getEdges() {
		return edges;
	}

}
