package gui.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import data.DataCore;
/**
 * Panel to draw the graph on.
 * @author Amr
 *
 */
public class GraphPanel extends JPanel {
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -4512219623781217332L;
	/**
	 * viewer.
	 */
	private Viewer viewer;
    /**
     * view of the graph.
     */
	private View view;
	/**
	 * scroll pane for the graph.
	 */
	private JScrollPane pane;
	/**
	 * Constructor.
	 * @param core
	 * The data core.
	 */
    public GraphPanel(final DataCore core) {
    	System.setProperty("org.graphstream.ui.renderer",
    			"org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		viewer =  new Viewer(core.getGraph(),
				Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		view = viewer.addDefaultView(false);
		this.setLayout(new BorderLayout());
		pane = new JScrollPane((Component) view);
        this.add(pane, BorderLayout.CENTER);
	}

}
