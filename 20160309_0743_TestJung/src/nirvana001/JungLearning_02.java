package nirvana001;

import java.awt.Dimension;
import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

/*
 * -References:
 *     --"Vainolo's BlogLearning JUNG – Java Universal Network/Graph Framework", https://www.vainolo.com/2011/02/14/learning-jung-java-universal-networkgraph-framework/
 */

public class JungLearning_02 {
  public static void main(String[] args) {
    DirectedSparseGraph g = new DirectedSparseGraph();
    g.addVertex("Vertex1");
    g.addVertex("Vertex2");
    g.addVertex("Vertex3");
    g.addEdge("Edge1", "Vertex1", "Vertex2");
    g.addEdge("Edge2", "Vertex1", "Vertex3");
    g.addEdge("Edge3", "Vertex3", "Vertex1");
    
//    VisualizationImageServer vs =
//      new VisualizationImageServer(
//        new CircleLayout(g), new Dimension(200, 200));
    VisualizationViewer vv =
    	      new VisualizationViewer(
    	        new CircleLayout(g), new Dimension(200, 200));
    Transformer transformer = new Transformer() {
    	  ////@Override public String transform(String arg0) { return arg0; }
    	public String transform(String arg0) { return arg0; }  // [OWN to replace line above, tag error]

    	  // [OWN] OWN ADDED (for Transformer instantiation)
		@Override
		public Object transform(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
    	};
    	vv.getRenderContext().setVertexLabelTransformer(transformer);
    	transformer = new Transformer() {
    	  ////@Override public String transform(String arg0) { return arg0; }
    	  public String transform(String arg0) { return arg0; }


    	  // [OWN] OWN ADDED (for Transformer instantiation)
		@Override
		public Object transform(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}
    	};
    	
    	vv.getRenderContext().setEdgeLabelTransformer(transformer);

    
    
    

    JFrame frame = new JFrame();
    
    ////frame.getContentPane().add(vs);
    frame.getContentPane().add(vv);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}