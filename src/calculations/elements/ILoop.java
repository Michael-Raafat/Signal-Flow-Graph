package calculations.elements;
/**
 * Interface of a loop element.
 * @author Amr
 *
 */
public interface ILoop extends IElement {
	/**
	 * Gets the name of each node and combines them.
	 * @return
	 * The name of symbols of the loop.
	 */
	String getLoopNodesName();
	/**
	 * Gets the gain of the loop.
	 * @return
	 * The gain of the loop.
	 */
	double getLoopGain();
	/**
	 * Checks if an element is untouched with the loop.
	 * @param element
	 * The element to see if it is untouched.
	 * @return
	 * True if it is untouched.
	 */
	boolean isUntouched(IElement element);
}
