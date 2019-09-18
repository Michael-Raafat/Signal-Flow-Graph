package calculations.elements;
/**
 * The interface of a forward path.
 * @author Amr
 *
 */
public interface IForwardPath extends IElement {
	/**
	 * Gets the name of each node and combines them.
	 * @return
	 * The name of symbols of the forward path.
	 */
	String getForwardPathName();
	/**
	 * Gets the gain of the forward path.
	 * @return
	 * The gain of the forward path.
	 */
	double getForwardPathGain();
	/**
	 * Returns the delta loop gain of the path.
	 * @return
	 * The delta gain of the loops that are untouched to the forward path.
	 */
	double getDeltaOfPath();
}
