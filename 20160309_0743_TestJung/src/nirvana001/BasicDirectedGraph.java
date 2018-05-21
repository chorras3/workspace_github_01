package nirvana001;

import javax.swing.JPanel;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

public class BasicDirectedGraph {
	
	int edgeCount = 0;  // Own Added
	
	public BasicDirectedGraph() {
		// TODO Auto-generated constructor stub
		crearPanelGrafo();
		
		
	}
	
	private static JPanel crearPanelGrafo(){
		JPanel jPanel = new JPanel();
		
		Graph g = new DirectedSparseMultigraph<MyNode, MyLink>();
		
		// Create some MyNode objects to use as vertices
		MyNode n1 = new MyNode(1);
//		MyNode n1 = new MyNode(1); MyNode n2 = new MyNode(2); MyNode n3 = new MyNode(3);
//		MyNode n4 = new MyNode(4); MyNode n5 = new MyNode(5); // note n1-n5 declared elsewhere.
		
		return jPanel;
	}
	
	

	class MyNode {
		int id; // good coding practice would have this as private

		public MyNode(int id) {
			this.id = id;
		}

		public String toString() { // Always a good idea for debuging
			return "V" + id; // JUNG2 makes good use of these.
		}
		
		
		
	}

	class MyLink {
		double capacity; // should be private
		double weight; // should be private for good practice
		int id;

		public MyLink(double weight, double capacity) {
			this.id = edgeCount++; // This is defined in the outer class.
			this.weight = weight;
			this.capacity = capacity;
		}

		public String toString() { // Always good for debugging
			return "E" + id;
		}
	}

}
