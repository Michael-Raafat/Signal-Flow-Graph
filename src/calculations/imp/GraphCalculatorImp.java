package calculations.imp;

import java.util.ArrayList;
import java.util.List;

import calculations.GraphCalculator;
import calculations.elements.IDeltaCalculator;
import calculations.elements.IForwardPath;
import calculations.elements.ILoop;
import calculations.elements.imp.DeltaCalculator;
import calculations.elements.imp.ForwardPath;
import calculations.elements.imp.Loop;
import graph.ISignalFlowGraph;
import graph.node.IEdge;
import graph.node.INode;
/**
 * 
 * @author Michael.
 *
 */
public class GraphCalculatorImp implements GraphCalculator {
	/**
	 * List of the combinations of loops.
	 */
	private List<CombinationLoops> combs;
	/**
	 * List of nodes.
	 */
	private List<INode> nodees;
	/**
	 * list of forward paths.
	 */
	private List<IForwardPath> fPaths;
	/**
	 * list of individual loops.
	 */
	private List<ILoop> loops;
	/**
	 * output node.
	 */
	private INode outputNode;
	/**
	 * input node.
	 */
	private INode inputNode;
	/**
	 * The signal flow graph.
	 */
	private ISignalFlowGraph sfGraph;
	/**
	 * Delta of the the system.
	 */
	private Double delta;
	/**
	 * Transfer function of the system.
	 */
	private Double transferFunction;
	/**
	 * constructor of GraphCalculator.
	 * @param sfg signal flow graph to know input node.
	 */
	public GraphCalculatorImp(final ISignalFlowGraph sfg) {
		outputNode = null;
		sfGraph = sfg;
		fPaths = new ArrayList<IForwardPath>();
		combs = new ArrayList<CombinationLoops>();
		loops = new ArrayList<ILoop>();
	}
	/**
	 * Updates the properties connected to the sfg.
	 */
	private void updateProperties() {
		inputNode = sfGraph.getInputNode();
		nodees = copyList(sfGraph.getNodes());
	}
	/**
	 * Copies a list into another one.
	 * @param nodes
	 * The list of nodes to copy.
	 * @return
	 * A copied list.
	 */
	private List<INode> copyList(final List<INode> nodes) {
		List<INode> temp = new ArrayList<INode>();
		for (int i = 0; i < nodes.size(); i++) {
			temp.add(nodes.get(i));
		}
		return temp;
	}
	/**
	 * Copies a list into another one.
	 * @param edges
	 * The list of edges to copy.
	 * @return
	 * A copied list.
	 */
	private List<IEdge> copyListE(final List<IEdge> edges) {
		List<IEdge> temp = new ArrayList<IEdge>();
		for (int i = 0; i < edges.size(); i++) {
			temp.add(edges.get(i));
		}
		return temp;
	}
	/**
	 * Copies a list into another one.
	 * @param loops
	 * The list of loops to copy.
	 * @return
	 * A copied list.
	 */
	private List<ILoop> copyListL(final List<ILoop> loops) {
		List<ILoop> temp = new ArrayList<ILoop>();
		for (int i = 0; i < loops.size(); i++) {
			temp.add(loops.get(i));
		}
		return temp;
	}
	@Override
	public final List<IForwardPath> getForwardPaths() {
		return fPaths;
	}
	/**
	 * get all forward paths by traversal.
	 * @param node contains edges we looking for.
	 * @param nodes that we travel in.
	 * @param edges we pass through.
	 */
	private void traversalOfNodes(final INode node, final List<INode> nodes,
			final List<IEdge> edges) {
		if (nodes.contains(node)) {
			nodes.add(node);
			return;
		}
		nodes.add(node);
		if (node.equals(outputNode)) {
			IForwardPath path = new ForwardPath(copyList(nodes),
					copyListE(edges), loops, combs);
			fPaths.add(path);
			return;
		}
		for (int i = 0; i < node.getEdges().size(); i++) {
			IEdge temp = node.getEdges().get(i);
			if (temp.getOrigin().equals(node)) {
				edges.add(temp);
				traversalOfNodes(temp.getDestination(), nodes, edges);
				edges.remove(edges.size() - 1);
				nodes.remove(nodes.size() - 1);
			}
		}
		
	}
	/**
	 * to check if the given node is before a given index in a list of nodes.
	 * @param nodes list of nodes of path.
	 * @param index of the node we stop at.
	 * @param node that we want to remove.
	 * @return true if the node is present before index, false otherwise.
	 */
	private boolean checkBefore(List<INode> nodes, int index, INode node) {
		for (int i = 0; i < index; i++) {
			if (nodes.get(i).equals(node)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public final List<ILoop> getLoops() {
		return loops;
	}

	/**
	 * get all forward paths by traversal.
	 * @param first the start node.
	 * @param node contains edges we looking for.
	 * @param nodes that we travel in.
	 * @param edges we pass through.
	 * @param nds The nodes we already tested.
	 */
	private void traversalOfLoops(final INode first, final INode node,
			final List<INode> nodes, final List<IEdge> edges,
			final List<INode> nds) {
		if (nds.contains(node) && !nodes.contains(node)) {
			nodes.add(node);
			return;
		} else if (nds.contains(node)) {
			nodes.add(node);
			return;
		} else if (nodes.contains(node) && node.equals(first)) {
			ILoop loop  = new Loop(copyList(nodes),
					copyListE(edges));
			loops.add(loop);
			nodes.add(node);
			return;
		} else if (nodes.contains(node)) {
			nodes.add(node);
			return;
		} else {
			nodes.add(node);
			for (int i = 0; i < node.getEdges().size(); i++) {
				IEdge temp = node.getEdges().get(i);
				if (!edges.contains(temp) && temp.getOrigin().equals(node)) {
					edges.add(temp);
					traversalOfLoops(first, temp.getDestination(),
							nodes, edges, nds);
					nodes.remove(nodes.size() - 1);
					edges.remove(edges.size() - 1);
				}
			}
		}
	}
	@Override
	public final void setOutputNode(final INode outputNd) {
		this.outputNode = outputNd;
	}

	@Override
	public final Double getTransferFunction() {
		return transferFunction;
	}

	@Override
	public final INode getOutputNode() {
		return outputNode;
	}

	@Override
	public final List<CombinationLoops> getCombinationLoops() {
		return combs;
	}
	
	@Override
	public final void calculateProperties() {
		this.updateProperties();
		// Updating the loops list.
		List<INode> nodes = new ArrayList<INode>();
		List<IEdge> edges = new ArrayList<IEdge>();
		loops = new ArrayList<ILoop>();
		List<INode> nds = new ArrayList<INode>();
		for (int i = 0; i < nodees.size(); i++) {
			traversalOfLoops(nodees.get(i),
					nodees.get(i), nodes,
					edges, nds);
			nds.add(nodees.get(i));
			nodes.clear();
			edges.clear();
		}
		combs = new ArrayList<CombinationLoops>();
		for (int i = 0; i < loops.size() - 1; i++) {
			for (int j = i + 1; j < loops.size(); j++) {
				if (loops.get(j).isUntouched(loops.get(i))) {
					CombinationLoops comb = new CombinationLoops();
					comb.addLoop(loops.get(i));
					comb.addLoop(loops.get(j));
					comb.setGain(loops.get(i).getLoopGain()
							* loops.get(j).getLoopGain());
					comb.setLevel(2);
					combs.add(comb);
				}
			}
		}
		// Updating the combinations list.
		int count = 1;
		for (int i = 0; i < combs.size(); i++) {
			CombinationLoops tempComb = combs.get(i);
			if (tempComb.getLevel() == count + 2) {
				count++;
			}
			int f = loops.lastIndexOf(
					tempComb.getLoops().get(
							tempComb.getLoops().size() - 1)
					);
			for (int j = f; j < loops.size(); j++) {
				boolean flag = false;
				for (int k = 0; k < tempComb.getLoops().size(); k++) {
					if (!tempComb.getLoops().get(k).isUntouched(loops.get(j))) {
						flag = true;
					}
				}
				if (!flag) {
					CombinationLoops comb = new CombinationLoops(
							copyListL(tempComb.getLoops()));
					comb.addLoop(loops.get(j));
					comb.setGain(tempComb.getGain()
							* loops.get(j).getLoopGain());
					comb.setLevel(count + 2);
					combs.add(comb);
				}
			}
		}
		// Updating forward paths.
		nodes = new ArrayList<INode>();
		edges = new ArrayList<IEdge>();
		fPaths = new ArrayList<IForwardPath>();
		if (outputNode != null && inputNode != null) {
			traversalOfNodes(inputNode, nodes, edges);
		}
		// Calculating delta.
		IDeltaCalculator calc = new DeltaCalculator();
		calc.setLoopList(loops);
		calc.setCombinationLoopList(combs);
		delta = calc.calculateDelta();
		// The transfer function of the system.
		double tf = 0;
		for (int i = 0; i < fPaths.size(); i++) {
			tf += fPaths.get(i).getForwardPathGain()
					* fPaths.get(i).getDeltaOfPath();
		}
		tf /= calc.calculateDelta();
		transferFunction = tf;
	}

	@Override
	public final double getDelta() {
		return delta;
	}

}
