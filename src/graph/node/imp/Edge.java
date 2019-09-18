package graph.node.imp;

import graph.node.IEdge;
import graph.node.INode;

/**
 * Edge implementation of the edge interface.
 * @author Amr
 *
 */
public class Edge implements IEdge {
	/**
	 * The nodes of origin and destination.
	 */
	private INode origin, destination;
	/**
	 * The gain of the edge.
	 */
	private double gain;
	/**
	 * Constructor of the edge.
	 * @param org
	 * The origin node.
	 * @param dest
	 * The destination node.
	 * @param val
	 * The gain of the edge.
	 */
	public Edge(final INode org, final INode dest,
			final double val) {
		origin = org;
		destination = dest;
		gain = val;
	}
	@Override
	public final INode getOrigin() {
		return origin;
	}

	@Override
	public final INode getDestination() {
		return destination;
	}

	@Override
	public final double getValue() {
		return gain;
	}

	@Override
	public final void setOrigin(final INode org) {
		origin = org;
	}

	@Override
	public final void setDestination(final INode dest) {
		destination = dest;
	}

	@Override
	public final void setValue(final double val) {
		gain = val;
	}

	@Override
	public final boolean equals(final Object edge) {
		if (edge instanceof IEdge) {
			IEdge temp = (IEdge) edge;
			return gain == temp.getValue()
					&& temp.getOrigin().equals(origin)
					&& temp.getDestination().equals(destination);
		} else {
			return false;
		}
	}

}
