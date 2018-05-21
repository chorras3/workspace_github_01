package grotto_007;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.filters.KNeighborhoodFilter.EdgeType;
import edu.uci.ics.jung.algorithms.flows.EdmondsKarpMaxFlow;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


/*
 * Refereneces ->  ** "JUNG 2.0 TutorialOr how to achieve graph based nirvana in Java", "last updated April 22nd, 2009", http://www.grotto-networking.com/JUNG/JUNG2-Tutorial.pdf 
 */

class Grotto_001 {
	public static void main(String[] args) {
		String sClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		System.out.println("This class name: "+sClassName+".java");

//		test_section_02_01();
//		test_section_03_01();
//		test_section_03_02();
//		test_section_03_03();
//		test_section_04_01();
		test_section_04_02();

	}

	
//	private static void test_section_04_02() {
//		
//		String currentMethod = getMethodName(1);
//		System.out.println("method: "+currentMethod);
//		
//		// The class "SimpleGraphView2.java" was downloaded from http://www.grotto-networking.com/JUNG/
//		SimpleGraphView2 sgv = new SimpleGraphView2(); // This builds the graph
//		// Layout<V, E>, BasicVisualizationServer<V,E>
//		Layout<Integer, String> layout = new CircleLayout(sgv.g);
//		layout.setSize(new Dimension(300,300));
//		BasicVisualizationServer<Integer,String> vv =
//		new BasicVisualizationServer<Integer,String>(layout);
//		vv.setPreferredSize(new Dimension(350,350));
//		// Setup up a new vertex to paint transformer...
//		Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
//		public Paint transform(Integer i) {
//		return Color.GREEN;
//		}
//		};
//		// Set up a new stroke Transformer for the edges
//		float dash[] = {10.0f};
//		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
//		BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
//		Transformer<String, Stroke> edgeStrokeTransformer =
//		new Transformer<String, Stroke>() {
//		public Stroke transform(String s) {
//		return edgeStroke;
//		}
//		};
//		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
//		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
//		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
//		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
//		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
//		JFrame frame = new JFrame("Simple Graph View 2");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(vv);
//		frame.pack();
//		frame.setVisible(true);
//	}
	private static void test_section_04_02() {
		
		String currentMethod = getMethodName(1);
		System.out.println("method: "+currentMethod);
		
		// The class "SimpleGraphView2.java" was downloaded from http://www.grotto-networking.com/JUNG/
		SimpleGraphView2 sgv = new SimpleGraphView2(); // This builds the graph
		// Layout<V, E>, BasicVisualizationServer<V,E>
		Layout<Integer, String> layout = new CircleLayout(sgv.g);
		layout.setSize(new java.awt.Dimension(300,300));
		BasicVisualizationServer<Integer,String> vv =
		new BasicVisualizationServer<Integer,String>(layout);
		vv.setPreferredSize(new java.awt.Dimension(350,350));
		// Setup up a new vertex to paint transformer...
		org.apache.commons.collections15.Transformer<Integer,java.awt.Paint> vertexPaint = new org.apache.commons.collections15.Transformer<Integer,java.awt.Paint>() {
			public java.awt.Paint transform(Integer i) {
				return java.awt.Color.GREEN;
		}
		};
		// Set up a new stroke Transformer for the edges
		float dash[] = {10.0f};
		final java.awt.Stroke edgeStroke = new java.awt.BasicStroke(1.0f, java.awt.BasicStroke.CAP_BUTT,
				java.awt.BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		org.apache.commons.collections15.Transformer<String, java.awt.Stroke> edgeStrokeTransformer =
		new org.apache.commons.collections15.Transformer<String, java.awt.Stroke>() {
		public java.awt.Stroke transform(String s) {  //java.awt.
		return edgeStroke;
		}
		};
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		JFrame frame = new JFrame("Simple Graph View 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}	

	
	
//	private static void test_section_04_01() {
//		
//		SimpleGraphView sgv = new SimpleGraphView(); //We create our graph in here
//		// The Layout<V, E> is parameterized by the vertex and edge types
//		Layout<Integer, String> layout = new CircleLayout(sgv.g);
//		layout.setSize(new Dimension(300,300)); // sets the initial size of the space
//		// The BasicVisualizationServer<V,E> is parameterized by the edge types
//		BasicVisualizationServer<Integer,String> vv =
//		new BasicVisualizationServer<Integer,String>(layout);
//		vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
//		JFrame frame = new JFrame("Simple Graph View");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(vv);
//		frame.pack();
//		frame.setVisible(true);
//		
//	}
	private static void test_section_04_01() {
		
		String currentMethod = getMethodName(1);
		System.out.println("method: "+currentMethod);
		
		// The class "SimpleGraphView.java" was downloaded from http://www.grotto-networking.com/JUNG/
		SimpleGraphView sgv = new SimpleGraphView(); //We create our graph in here
		// The Layout<V, E> is parameterized by the vertex and edge types
		Layout<Integer, String> layout = new CircleLayout(sgv.g);
		////Layout<Integer, String> layout = new CircleLayout<Integer, String>((Graph<Integer, String>) sgv);
		layout.setSize(new java.awt.Dimension(300,300)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Integer,String> vv =
		new BasicVisualizationServer<Integer,String>(layout);
		vv.setPreferredSize(new java.awt.Dimension(350,350)); //Sets the viewing area size
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
		
	}	
	
	
	private static void test_section_03_03() {

		/*
		 * Section of pdf ( 3.3 Max-Flows and the Factory Idiom )
		 */
		
		String currentMethod = getMethodName(1);
		System.out.println("method: "+currentMethod);

		// //g = new DirectedSparseMultigraph<MyNode, MyLink>();
		////Graph g4 = new DirectedSparseMultigraph<MyNode, MyLink>();
		Graph g4 = new DirectedSparseMultigraph<MyNode, MyLink>();

		// Create some MyNode objects to use as vertices
		MyNode n1 = new MyNode(1);
		MyNode n2 = new MyNode(2);
		MyNode n3 = new MyNode(3);
		MyNode n4 = new MyNode(4);
		MyNode n5 = new MyNode(5); // note n1-n5 declared elsewhere.
		// Add some directed edges along with the vertices to the graph
		g4.addEdge(new MyLink(2.0, 48), n1, n2); // This method
		g4.addEdge(new MyLink(2.0, 48), n2, n3);
		g4.addEdge(new MyLink(3.0, 192), n3, n5);
		g4.addEdge(new MyLink(2.0, 48), n5, n4); // or we can use
		g4.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
		g4.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
		g4.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the
													// destination

		System.out.println("The graph g3 = " + g4.toString());

		// org.apache.commons.collections15.Transformer<MyLink, Double>
		// wtTransformer = new
		// org.apache.commons.collections15.Transformer<MyLink, Double>() {
		// public Double transform(MyLink link) {
		// return link.weight;
		// }
		// };
		//
		// DijkstraShortestPath<MyNode, MyLink> alg = new
		// DijkstraShortestPath(g4,
		// wtTransformer);
		// List<MyLink> l = alg.getPath(n1, n4);
		// Number dist = alg.getDistance(n1, n4);
		// System.out
		// .println("The shortest path from" + n1 + " to " + n4 + " is:");
		// System.out.println(l.toString());
		// System.out.println("and the length of the path is: " + dist);

		org.apache.commons.collections15.Transformer<MyLink, Double> capTransformer = new org.apache.commons.collections15.Transformer<MyLink, Double>() {
			public Double transform(MyLink link) {
				return link.capacity;
			}
		};
		Map<MyLink, Double> edgeFlowMap = new HashMap<MyLink, Double>();

		// This Factory produces new edges for use by the algorithm
		org.apache.commons.collections15.Factory<MyLink> edgeFactory = new org.apache.commons.collections15.Factory<MyLink>() {
			public MyLink create() {
				return new MyLink(1.0, 1.0);
			}
		};
		
//		EdmondsKarpMaxFlow<MyNode, MyLink> alg =
//				new EdmondsKarpMaxFlow(g4,n2, n5, capTransformer, edgeFlowMap,
//				edgeFactory);		
		EdmondsKarpMaxFlow<MyNode, MyLink> alg =
				new EdmondsKarpMaxFlow((DirectedGraph) g4,n2, n5, capTransformer, edgeFlowMap,
				edgeFactory);			// Required to cast to DirectedGraph!
		
		alg.evaluate();
		System.out.println("The max flow is: " + alg.getMaxFlow());
		System.out.println("The edge set is: " + alg.getMinCutEdges().toString());		

	}

	private static void test_section_03_02() {
		/*
		 * section of pdf ( 3.2 A Weighted Shortest Path and Transformer
		 * classes )
		 */
		
		String currentMethod = getMethodName(1);
		System.out.println("method: "+currentMethod);		

		// //g = new DirectedSparseMultigraph<MyNode, MyLink>();
		Graph<MyNode, MyLink> g4 = new DirectedSparseMultigraph<MyNode, MyLink>();

		// Create some MyNode objects to use as vertices
		MyNode n1 = new MyNode(1);
		MyNode n2 = new MyNode(2);
		MyNode n3 = new MyNode(3);
		MyNode n4 = new MyNode(4);
		MyNode n5 = new MyNode(5); // note n1-n5 declared elsewhere.
		// Add some directed edges along with the vertices to the graph
		g4.addEdge(new MyLink(2.0, 48), n1, n2); // This method
		g4.addEdge(new MyLink(2.0, 48), n2, n3);
		g4.addEdge(new MyLink(3.0, 192), n3, n5);
		g4.addEdge(new MyLink(2.0, 48), n5, n4); // or we can use
		g4.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
		g4.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
		g4.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the
													// destination

		System.out.println("The graph g3 = " + g4.toString());

		org.apache.commons.collections15.Transformer<MyLink, Double> wtTransformer = new org.apache.commons.collections15.Transformer<MyLink, Double>() {
			public Double transform(MyLink link) {
				return link.weight;
			}
		};

		DijkstraShortestPath<MyNode, MyLink> alg = new DijkstraShortestPath<MyNode, MyLink>(g4,
				wtTransformer);
		List<MyLink> l = alg.getPath(n1, n4);
		Number dist = alg.getDistance(n1, n4);
		System.out
				.println("The shortest path from" + n1 + " to " + n4 + " is:");
		System.out.println(l.toString());
		System.out.println("and the length of the path is: " + dist);

	}

	// private static void test_002( ) {
	// g = new DirectedSparseMultigraph<MyNode, MyLink>();
	// // Create some MyNode objects to use as vertices
	// n1 = new MyNode(1); n2 = new MyNode(2); n3 = new MyNode(3);
	// n4 = new MyNode(4); n5 = new MyNode(5); // note n1-n5 declared elsewhere.
	// // Add some directed edges along with the vertices to the graph
	// g.addEdge(new MyLink(2.0, 48),n1, n2, EdgeType.DIRECTED); // This method
	// g.addEdge(new MyLink(2.0, 48),n2, n3, EdgeType.DIRECTED);
	// g.addEdge(new MyLink(3.0, 192), n3, n5, EdgeType.DIRECTED);
	// g.addEdge(new MyLink(2.0, 48), n5, n4, EdgeType.DIRECTED); // or we can
	// use
	// g.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
	// g.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
	// g.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the destination
	// }
	private static void test_section_03_01() {
		
		/*
		 * 3.2 section of pdf ( 3.1 An Unweighted Shortest Path )
		 */
		
		String currentMethod = getMethodName(1);
		System.out.println("method: "+currentMethod);
		System.out.println("=====");
		

		// //g = new DirectedSparseMultigraph<MyNode, MyLink>();
		Graph<MyNode, MyLink> g3 = new DirectedSparseMultigraph<MyNode, MyLink>();

		// Create some MyNode objects to use as vertices
		MyNode n1 = new MyNode(1);
		MyNode n2 = new MyNode(2);
		MyNode n3 = new MyNode(3);
		MyNode n4 = new MyNode(4);
		MyNode n5 = new MyNode(5); // note n1-n5 declared elsewhere.
		// Add some directed edges along with the vertices to the graph
		g3.addEdge(new MyLink(2.0, 48), n1, n2); // This method
		g3.addEdge(new MyLink(2.0, 48), n2, n3);
		g3.addEdge(new MyLink(3.0, 192), n3, n5);
		g3.addEdge(new MyLink(2.0, 48), n5, n4); // or we can use
		g3.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
		g3.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
		g3.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the
													// destination

		System.out.println("The graph g3 = " + g3.toString());

		DijkstraShortestPath<MyNode, MyLink> alg = new DijkstraShortestPath<MyNode, MyLink>(g3);
		List<MyLink> l = alg.getPath(n1, n4);
		System.out.println("The shortest unweighted path from" + n1 + " to "
				+ n4 + " is:");
		System.out.println(l.toString()); // Note: path calculated is different
											// if nodes are non-directed.

	}

	private static void test_section_02_01() {
		
		/*
		 * 2.1 Creating a Graph and adding vertices and edges
		 */		
		
		
		String currentMethod = getMethodName(1);
		System.out.println("method: "+currentMethod);
		System.out.println("=====");
		
		
		// Graph<V, E> where V is the type of the vertices
		// and E is the type of the edges
		Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
		// Add some vertices. From above we defined these to be type Integer.
		g.addVertex((Integer) 1);
		g.addVertex((Integer) 2);
		g.addVertex((Integer) 3);
		// Add some edges. From above we defined these to be of type String
		// Note that the default is for undirected edges.
		g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives
		g.addEdge("Edge-B", 2, 3);
		// Let's see what we have. Note the nice output from the
		// SparseMultigraph<V,E> toString() method
		System.out.println("The graph g = " + g.toString());
		// Note that we can use the same nodes and edges in two different
		// graphs.
		Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();
		g2.addVertex((Integer) 1);
		g2.addVertex((Integer) 2);
		g2.addVertex((Integer) 3);
		g2.addEdge("Edge-A", 1, 3);
		// //g2.addEdge("Edge-B", 2,3, EdgeType.DIRECTED);
		g2.addEdge("Edge-B", 2, 3);
		// //g2.addEdge("Edge-C", 3, 2, EdgeType.DIRECTED);
		g2.addEdge("Edge-C", 3, 2);
		g2.addEdge("Edge-P", 2, 3); // A parallel edge
		System.out.println("The graph g2 = " + g2.toString());

	}
	
	
	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */

	/*
	 * References: "Getting the name of the current executing method", "answered Jan 14 '09 at 12:33" "by VonC	589k17316401723", http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method
	 */	
	public static String getMethodName(final int depth)
	{
	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

	  //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
	  // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
	  return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
	}	

} // End of class