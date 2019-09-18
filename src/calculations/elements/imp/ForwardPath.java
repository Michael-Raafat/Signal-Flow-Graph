package calculations.elements.imp;
import java.util.List;
import calculations.elements.IForwardPath;
import calculations.elements.ILoop;
import calculations.imp.CombinationLoops;
import graph.node.IEdge;
import graph.node.INode;
/**
 * Forward path implementation.
 * @author Amr
 *
 */
public class ForwardPath implements IForwardPath {
	/**
	 * list of combination loops.
	 */
	private List<CombinationLoops> combs;
	/**
	 * list of nodes.
	 */
	private List<INode> nodes;
	/**
	 * list of edges.
	 */
	private List<IEdge> edgs;
	/**
	 * list of loops.
	 */
	private List<ILoop> loops;
	/**
	 * gain of forward path.
	 */
	private double pathGain;
	/**
	 * name of loop nodes.
	 */
	private String forwardPathName;
	/**
	 * constructor of forward path.
	 * @param nodes of path.
	 * @param edgs of nodes.
	 * @param loops of the graph.
	 */
	public ForwardPath(final List<INode> nodes, final List<IEdge> edgs,
			final List<ILoop> loops, final List<CombinationLoops> combs) {
		this.nodes = nodes;
		this.edgs = edgs;
		this.loops = loops;
		this.combs = combs;
		pathGain = 1;
		for (int i = 0; i < edgs.size(); i++) {
			pathGain *= edgs.get(i).getValue();
		}
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < nodes.size(); i++) {
			str.append(nodes.get(i).getLabel() + " ");
		}
		forwardPathName = str.toString();	
	}

	@Override
	public final List<INode> getNodes() {
		return nodes;
	}

	@Override
	public final List<IEdge> getEdges() {
		return edgs;
	}

	@Override
	public final String getForwardPathName() {
		return forwardPathName;
	}

	@Override
	public final double getForwardPathGain() {
		return pathGain;
	}
	
	@Override
	public final double getDeltaOfPath() {
		DeltaCalculator calc = new DeltaCalculator();
		calc.setLoopList(loops);
		calc.setCombinationLoopList(combs);
		return calc.calculateDelta(this);
	}
}
