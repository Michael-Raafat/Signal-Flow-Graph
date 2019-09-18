package calculations.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import calculations.GraphCalculator;
import calculations.imp.GraphCalculatorImp;
import graph.ISignalFlowGraph;
import graph.imp.SignalFlowGraph;
import graph.node.imp.Node;

/**
 * 
 * @author Michael.
 *
 */
public class GraphCalculatorTest {
	/**
	 * Signal flow graph.
	 */
	private ISignalFlowGraph sfg;
	/**
	 * Graph calculator.
	 */
	private GraphCalculator graphCalc;
	/**
	 * Sets up the variables.
	 */
	@Before
	public final void setUp() {
		sfg = new SignalFlowGraph();
		graphCalc = new GraphCalculatorImp(sfg);
	}
	/**
	 * Generates a number of nodes in the graph and
	 * assigns the first node added as input graph.
	 * The names of the nodes start from 0.
	 * @param n
	 * Number of nodes.
	 */
	public final void generateNodes(final int n) {
		for (int i = 0; i < n; i++) {
			sfg.addNode(String.valueOf(i));
		}
		sfg.setInputNode("0");
	}
	/**
	 * Test on a first example graph.
	 */
	@Test
	public final void test() {
		generateNodes(6);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("1", "3", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "3", -1);
		sfg.addEdge("2", "1", -1);
		sfg.addEdge("4", "1", -1);
		sfg.addEdge("4", "5", 1);
		graphCalc.setOutputNode(new Node("5"));
		graphCalc.calculateProperties();
		Assert.assertEquals(6,
				graphCalc.getDelta(), 0.001);
		Assert.assertEquals(0.333333,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on second graph.
	 */
	@Test
	public final void test2() {
		generateNodes(6);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("1", "3", 1);
		sfg.addEdge("1", "4", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("2", "1", -1);
		sfg.addEdge("3", "2", -1);
		sfg.addEdge("3", "3", -1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "5", 1);
		graphCalc.setOutputNode(new Node("5"));
		graphCalc.calculateProperties();
		Assert.assertEquals(4,
				graphCalc.getDelta(), 0.001);
		Assert.assertEquals(1.25,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a third graph.
	 */
	@Test
	public final void test3() {
		generateNodes(5);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("0", "2", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("3", "2", -1);
		sfg.addEdge("3", "1", -1);
		graphCalc.setOutputNode(new Node("4"));
		graphCalc.calculateProperties();
		Assert.assertEquals(3,
				graphCalc.getDelta(), 0.001);
		Assert.assertEquals(0.666667,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a fourth graph.
	 */
	@Test
	public final void test4() {
		generateNodes(6);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("1", "3", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("2", "1", -1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "3", -1);
		sfg.addEdge("4", "1", -1);
		sfg.addEdge("4", "5", 1);
		graphCalc.setOutputNode(new Node("5"));
		graphCalc.calculateProperties();
		Assert.assertEquals(6,
				graphCalc.getDelta(), 0.001);
		Assert.assertEquals(0.333333,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a fifth graph.
	 */
	@Test
	public final void test5() {
		generateNodes(7);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("1", "4", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("3", "2", -1);
		sfg.addEdge("3", "5", 1);
		sfg.addEdge("4", "4", -1);
		sfg.addEdge("5", "1", -1);
		sfg.addEdge("5", "3", -1);
		sfg.addEdge("5", "6", 1);
		sfg.addEdge("4", "5", 1);
		graphCalc.setOutputNode(new Node("6"));
		graphCalc.calculateProperties();
		Assert.assertEquals(0.4,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a graph 6.
	 */
	@Test
	public final void test6() {
		generateNodes(9);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("2", "7", 1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "5", 1);
		sfg.addEdge("5", "2", -1);
		sfg.addEdge("5", "6", 1);
		sfg.addEdge("6", "7", 1);
		sfg.addEdge("7", "8", 1);
		sfg.addEdge("7", "1", -1);
		sfg.addEdge("7", "4", -1);
		graphCalc.setOutputNode(new Node("8"));
		graphCalc.calculateProperties();
		Assert.assertEquals(0.5,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a graph 7.
	 */
	@Test
	public final void test7() {
		generateNodes(9);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("3", "6", 1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "5", 1);
		sfg.addEdge("5", "4", -1);
		sfg.addEdge("5", "6", 1);
		sfg.addEdge("5", "7", 1);
		sfg.addEdge("6", "7", 1);
		sfg.addEdge("6", "2", -1);
		sfg.addEdge("7", "1", -1);
		sfg.addEdge("7", "5", -1);
		sfg.addEdge("7", "8", 1);
		graphCalc.setOutputNode(new Node("8"));
		graphCalc.calculateProperties();
		Assert.assertEquals(0.33333,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a graph 8.
	 */
	@Test
	public final void test8() {
		generateNodes(8);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("2", "1", -1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "3", -1);
		sfg.addEdge("4", "5", 1);
		sfg.addEdge("5", "6", 1);
		sfg.addEdge("6", "5", -1);
		sfg.addEdge("6", "7", 1);
		graphCalc.setOutputNode(new Node("7"));
		graphCalc.calculateProperties();
		Assert.assertEquals(0.125,
				graphCalc.getTransferFunction(), 0.001);
	}
	/**
	 * Test on a graph 9.
	 */
	@Test
	public final void test9() {
		generateNodes(10);
		sfg.addEdge("0", "1", 1);
		sfg.addEdge("1", "2", 1);
		sfg.addEdge("2", "1", -1);
		sfg.addEdge("2", "3", 1);
		sfg.addEdge("3", "4", 1);
		sfg.addEdge("4", "3", -1);
		sfg.addEdge("4", "5", 1);
		sfg.addEdge("5", "6", 1);
		sfg.addEdge("6", "5", -1);
		sfg.addEdge("6", "7", 1);
		sfg.addEdge("7", "8", 1);
		sfg.addEdge("8", "7", -1);
		sfg.addEdge("8", "9", 1);
		graphCalc.setOutputNode(new Node("9"));
		graphCalc.calculateProperties();
		Assert.assertEquals(0.0625,
				graphCalc.getTransferFunction(), 0.001);
	}
}
