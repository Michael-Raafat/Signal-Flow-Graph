package calculations.elements.imp;

import java.util.List;

import calculations.elements.IElement;
import calculations.elements.ILoop;
import graph.node.IEdge;
import graph.node.INode;
/**
 * Implementation of the ILoop interface.
 * @author Amr
 *
 */
public class Loop implements ILoop {
	/**
	 * List of the nodes.
	 */
	private List<INode> nodes;
	/**
	 * List of the edges.
	 */
	private List<IEdge> edges;
	/**
	 * The loop gain.
	 */
	private double loopGain;
	/**
	 * The loop name.
	 */
	private String loopName;
	/**
	 * Constructor of the loop.
	 * @param nds
	 * The list of nodes.
	 * @param edgs
	 * The list of edges.
	 */
	public Loop(final List<INode> nds, final List<IEdge> edgs) {
		edges = edgs;
		nodes = nds;
		loopGain = 1;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < edgs.size(); i++) {
			loopGain *= edgs.get(i).getValue();
		}
		for (int i = 0; i < nds.size(); i++) {
			str.append(nds.get(i).getLabel() + " ");
		}
		str.append(nds.get(0).getLabel());
		loopName = str.toString();
	}
	@Override
	public final List<INode> getNodes() {
		return nodes;
	}

	@Override
	public final List<IEdge> getEdges() {
		return edges;
	}

	@Override
	public final String getLoopNodesName() {
		return loopName;
	}

	@Override
	public final double getLoopGain() {
		return loopGain;
	}

	@Override
	public final boolean isUntouched(final IElement element) {
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < element.getNodes().size(); j++) {
				if (element.getNodes().get(j).equals(nodes.get(i))) {
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean equals(final Object object) {
		if (object instanceof ILoop) {
			ILoop loop = (ILoop) object;
			return loop.getLoopGain() == loopGain
					&& loop.getLoopNodesName().compareTo(loopName) == 0;
		} else {
			return false;
		}
	}
}
