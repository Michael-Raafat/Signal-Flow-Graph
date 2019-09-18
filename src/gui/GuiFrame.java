package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionListener;

import data.DataCore;
import gui.listeners.ButtonListener;
import gui.listeners.PropertySelectionListener;
import gui.panels.ButtonPanel;
import gui.panels.GraphPanel;
import gui.panels.InformationPanel;
import gui.panels.LiveComponent;
import gui.panels.PropertiesPanel;
import gui.panels.StatusPanel;

/**
 * The Jframe of the program.
 * @author Amr
 *
 */
public class GuiFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1064465562347456627L;
	/**
	 * Screen size and width.
	 */
	private static final int DEFAULT_SCREEN_WIDTH = 1000,
			DEFAULT_SCREEN_HEIGHT = 1000;
	/**
	 * The graph panel.
	 */
	private GraphPanel graphPanel;
	/**
	 * The signal flow graph.
	 */
	private DataCore dataCore;
	/**
	 * Button panel.
	 */
	private ButtonPanel buttonPanel;
	/**
	 * Status panel.
	 */
	private StatusPanel statusPanel;
	/**
	 * Properties panel.
	 */
	private PropertiesPanel propertiesPane;
	/**
	 * Listener.
	 */
	private ActionListener listener;
	/**
	 * Selection listener.
	 */
	private ListSelectionListener selectionListener;
	/**
	 * Information panel.
	 */
	private InformationPanel informationPanel;
	/**
	 * Constructor.
	 */
	public GuiFrame() {
		dataCore = new DataCore();
		List<LiveComponent> list = new ArrayList<LiveComponent>();
		listener = new ButtonListener(this, dataCore, list);
		informationPanel = new InformationPanel();
		graphPanel = new GraphPanel(dataCore);
		statusPanel = new StatusPanel(dataCore);
		buttonPanel = new ButtonPanel(listener);
		selectionListener = new PropertySelectionListener(dataCore,
				informationPanel);
		propertiesPane = new PropertiesPanel(dataCore, selectionListener);
		list.add(informationPanel);
		list.add(propertiesPane);
		list.add(statusPanel);
		this.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		this.setLayout(new BorderLayout());
		this.add(graphPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(statusPanel, BorderLayout.SOUTH);
		this.add(propertiesPane, BorderLayout.WEST);
		this.add(informationPanel, BorderLayout.EAST);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
