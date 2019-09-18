package gui.listeners;
/**
 * Enum for button actions.
 * @author Amr
 *
 */
public enum ButtonAction {
	/**
	 * .
	 */
	ADD_NODE, ADD_EDGE, REMOVE_NODE, REMOVE_EDGE, GENEREATE_NODES,
	/**
	 * .
	 */
	CHOOSE_INPUT, CHOOSE_OUTPUT, CLEAR, EXIT;
	/**
	 * Transforms enum to string.
	 * @return
	 * String representation of the enum.
	 */
	public String toString() {
		int i = 0;
		for (ButtonAction action : ButtonAction.values()) {
			if (action == this) {
				return String.valueOf(i);
			}
			i++;
		}
		return null;
	}
	/**
	 * Parses a string into the suitable enum.
	 * @param str
	 * The string to parse.
	 * @return
	 * The equivalent enum.
	 */
	public static ButtonAction parseString(final String str) {
		try {
			int i = Integer.parseInt(str);
			return ButtonAction.values()[i];
		} catch (Exception e) {
			return null;
		}
	}
}
