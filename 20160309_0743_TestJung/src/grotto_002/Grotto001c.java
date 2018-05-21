package grotto_002;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.filters.KNeighborhoodFilter.EdgeType;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;



/*
 * Refereneces ->  ** "JUNG 2.0 TutorialOr how to achieve graph based nirvana in Java", "last updated April 22nd, 2009", http://www.grotto-networking.com/JUNG/JUNG2-Tutorial.pdf 
 */

class Grotto001c{
    public static void main(String[] args){
   
//    test_001();
//    test_002();
    test_003();

}
 
    
    
	private static void test_003() {

		// org.apache.commons.collections15.Transformer<MyLink, Double>
		// wtTransformer = new
		// org.apache.commons.collections15.Transformer<MyLink, Double>() {
		// @Override
		// public Double transform(MyLink link) {
		// // TODO Auto-generated method stub
		// ////return null;
		// return link.weight;
		// }
		// // public Double transform(MyLink link) {
		// // return link.weight;
		// };

		org.apache.commons.collections15.Transformer<MyLink, Double> wtTransformer = new org.apache.commons.collections15.Transformer<MyLink, Double>() {
			public Double transform(MyLink link) {
				return link.weight;
			}
		};

		System.out.println("test_003(). start. ");

		// //g = new DirectedSparseMultigraph<MyNode, MyLink>();
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

		////wtTransformer wtTransformer;

		DijkstraShortestPath<MyNode, MyLink> alg = new DijkstraShortestPath(g4,
				wtTransformer);
		List<MyLink> l = alg.getPath(n1, n4);
		Number dist = alg.getDistance(n1, n4);
		System.out
				.println("The shortest path from" + n1 + " to " + n4 + " is:");
		System.out.println(l.toString());
		System.out.println("and the length of the path is: " + dist);

	}    
    
    
  //private static void test_002( ) {
//	g = new DirectedSparseMultigraph<MyNode, MyLink>();
//	// Create some MyNode objects to use as vertices
//	n1 = new MyNode(1); n2 = new MyNode(2); n3 = new MyNode(3);
//	n4 = new MyNode(4); n5 = new MyNode(5); // note n1-n5 declared elsewhere.
//	// Add some directed edges along with the vertices to the graph
//	g.addEdge(new MyLink(2.0, 48),n1, n2, EdgeType.DIRECTED); // This method
//	g.addEdge(new MyLink(2.0, 48),n2, n3, EdgeType.DIRECTED);
//	g.addEdge(new MyLink(3.0, 192), n3, n5, EdgeType.DIRECTED);
//	g.addEdge(new MyLink(2.0, 48), n5, n4, EdgeType.DIRECTED); // or we can use
//	g.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
//	g.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
//	g.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the destination
//}
private static void test_002( ) {
	System.out.println("test_002. start. ");

	////g = new DirectedSparseMultigraph<MyNode, MyLink>();
	Graph g3 = new DirectedSparseMultigraph<MyNode, MyLink>();
	
	// Create some MyNode objects to use as vertices
	MyNode n1 = new MyNode(1); MyNode n2 = new MyNode(2); MyNode n3 = new MyNode(3);
	MyNode n4 = new MyNode(4); MyNode n5 = new MyNode(5); // note n1-n5 declared elsewhere.
	// Add some directed edges along with the vertices to the graph
	g3.addEdge(new MyLink(2.0, 48),n1, n2); // This method
	g3.addEdge(new MyLink(2.0, 48),n2, n3);
	g3.addEdge(new MyLink(3.0, 192), n3, n5);
	g3.addEdge(new MyLink(2.0, 48), n5, n4); // or we can use
	g3.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
	g3.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
	g3.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the destination
	
	System.out.println("The graph g3 = " + g3.toString());
	
	
	DijkstraShortestPath<MyNode,MyLink> alg = new DijkstraShortestPath(g3);
	List<MyLink> l = alg.getPath(n1, n4);
	System.out.println("The shortest unweighted path from" + n1 +
	" to " + n4 + " is:");
	System.out.println(l.toString());		// Note: path calculated is different if nodes are non-directed. 

}


    
private static void test_001( ) {
	// Graph<V, E> where V is the type of the vertices
	// and E is the type of the edges
	Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
	// Add some vertices. From above we defined these to be type Integer.
	g.addVertex((Integer)1);
	g.addVertex((Integer)2);
	g.addVertex((Integer)3);
	// Add some edges. From above we defined these to be of type String
	// Note that the default is for undirected edges.
	g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives
	g.addEdge("Edge-B", 2, 3);
	// Let's see what we have. Note the nice output from the
	// SparseMultigraph<V,E> toString() method
	System.out.println("The graph g = " + g.toString());
	// Note that we can use the same nodes and edges in two different graphs.
	Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();
	g2.addVertex((Integer)1);
	g2.addVertex((Integer)2);
	g2.addVertex((Integer)3);
	g2.addEdge("Edge-A", 1,3);
	////g2.addEdge("Edge-B", 2,3, EdgeType.DIRECTED);
	g2.addEdge("Edge-B", 2,3);
	////g2.addEdge("Edge-C", 3, 2, EdgeType.DIRECTED);
	g2.addEdge("Edge-C", 3, 2);
	g2.addEdge("Edge-P", 2,3); // A parallel edge
	System.out.println("The graph g2 = " + g2.toString());

}







    }  // End of class