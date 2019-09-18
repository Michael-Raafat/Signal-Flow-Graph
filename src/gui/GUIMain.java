package gui;

import javax.swing.SwingUtilities;
/**
 * The main for the the gui.
 * @author Amr
 *
 */
public final class GUIMain {
	/**
	 * Private constructor for final class.
	 */
	private GUIMain() {
	}
	/**
	 * Main function.
	 * @param args
	 * The cmd arguments.
	 */
	public static void main(final String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new GuiFrame();
	                }
	        });
	}

}
