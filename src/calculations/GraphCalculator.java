package calculations;

import java.util.List;

import calculations.elements.IForwardPath;
import calculations.elements.ILoop;
import calculations.imp.CombinationLoops;
import graph.node.INode;
/**
 * 
 * @author Michael.
 *
 */
public interface GraphCalculator {
	/**
	 * Calculates all the properties and updates all of
	 * them to the appropriate values.
	 */
	void calculateProperties();
	/**
	 * Returns a list of the forward path of the graph for the current
	 * set outputNode.
	 * @return
	 * List of forward paths.
	 */
	List<IForwardPath> getForwardPaths();
	/**
	 * Returns a list of the loops of the graph.
	 * @return
	 * List of loops.
	 */
	List<ILoop> getLoops();
	/**
	 * Sets the current output node of the graph calculator.
	 * @param outputNode
	 * The output node to calculate the transfer function to.
	 */
	void setOutputNode(INode outputNode);
	/**
	 * Calculates the transfer function of graph.
	 * @return
	 * The value of the transfer function.
	 */
	Double getTransferFunction();
	/**
	 * Gets and returns the current output node.
	 * @return
	 * The output node.
	 */
	INode getOutputNode();
	/**
	 * Gets combinational loops.
	 * @return
	 * list of pairs containing each combinational loop with its gain.
	 */
	List<CombinationLoops> getCombinationLoops();
	/**
	 * Returns the delta value.
	 * @return
	 * The general delta value.
	 */
	double getDelta();
}
