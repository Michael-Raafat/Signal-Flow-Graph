package data;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import calculations.GraphCalculator;
import calculations.imp.GraphCalculatorImp;
import graph.ISignalFlowGraph;
import graph.imp.SignalFlowGraph;

/**
 * The data core.
 * @author Amr
 *
 */
public class DataCore {
	/**
	 * Graph to draw.
	 */
	private Graph graph;
	/**
	 * Signal flow graph.
	 */
	private ISignalFlowGraph sfgGraph;
	/**
	 * Graph calculator.
	 */
	private GraphCalculator graphCalculator;
	/**
	 * Constructor.
	 */
	public DataCore() {
		graph = new MultiGraph("Drawable graph");
		sfgGraph = new SignalFlowGraph();
		graphCalculator = new GraphCalculatorImp(sfgGraph);
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet",
    			"node {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: above;"
    			+ " text-size: 12;"
    			+ " }"
    			+ "edge {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: center;"
    			+ " text-size: 12;"
    			+ "}");
	}
	/**
	 * @return the graph
	 */
	public final Graph getGraph() {
		return graph;
	}
	/**
	 * @return the sfgGraph
	 */
	public final ISignalFlowGraph getSfgGraph() {
		return sfgGraph;
	}
	/**
	 * @return the graphCalculator
	 */
	public final GraphCalculator getGraphCalculator() {
		return graphCalculator;
	}
	/**
	 * Clears the graphs.
	 */
	public final void clear() {
		graph.clear();
		graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.setStrict(false);
        graph.addAttribute("ui.stylesheet",
    			"node {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: above;"
    			+ " text-size: 12;"
    			+ " }"
    			+ "edge {"
    			+ " text-mode: normal;"
    			+ " text-background-mode: plain;"
    			+ " text-alignment: center;"
    			+ " text-size: 12;"
    			+ "}");
		sfgGraph = new SignalFlowGraph();
		graphCalculator = new GraphCalculatorImp(sfgGraph);
	}
}
