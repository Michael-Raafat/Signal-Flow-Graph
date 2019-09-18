package calculations.elements;

import java.util.List;

import calculations.imp.CombinationLoops;

/**
 * The delta gain.
 * @author Amr
 *
 */
public interface IDeltaCalculator {
	/**
	 * Sets the loop list of the delta calculator.
	 * @param loops
	 * The list of loops to calculate delta from.
	 */
	void setLoopList(List<ILoop> loops);
	/**
	 * Returns the delta value.
	 * @return
	 * The delta value.
	 */
	double calculateDelta();
	/**
	 * Returns the delta value of the forward path.
	 * @param path
	 * The path to calculate the delta.
	 * @return
	 * The delta value of the forward path.
	 */
	double calculateDelta(IForwardPath path);
	/**
	 * Sets the combination loop list of the delta calculator.
	 * @param loops
	 * The list of Combination loops to calculate delta from.
	 */
	void setCombinationLoopList(List<CombinationLoops> loops);
}
