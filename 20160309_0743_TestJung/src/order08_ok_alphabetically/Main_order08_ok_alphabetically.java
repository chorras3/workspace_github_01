package order08_ok_alphabetically;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

class Main_order08_ok_alphabetically{
    public static void main(String[] args){


    // Is this "set" and "add" part is required?
    	
//    Set<Integer> set0 = new HashSet<Integer>();
//    Set<Integer> set1 = new HashSet<Integer>();
//    Set<Integer> set2 = new HashSet<Integer>();
//
//    set0.add(1);
//
//    set1.add(2);
//    set1.add(3);   
//    set1.add(2);
//    set1.add(4);
//    set1.add(5);
//
//    set2.add(6);
//    set2.add(7);
    	   	
    String sJFrameTitle = "Title for class: "+ Thread.currentThread().getStackTrace()[1].getClassName()+".java";
    JFrame frame = new JFrame(sJFrameTitle);

//    String className = object.getClass.getName();
//    System.out.println( this.getClass() );;
    
//    frame.add(createGraphPanel(set0, set1, set2));
    
    ////frame.add(createGraphPanel( ));
    frame.add(createGraphPanel_c( ));
    frame.pack();
    frame.setVisible(true);


}

    
//private static JPanel createGraphPanel_b( ) {
//	
//    Graph<Integer, String> graph = new DelegateForest();
//    	
//    	
//    Layout<Integer, String> layout = new TreeLayout<Integer, String>((Forest<Integer, String>) graph);
//    VisualizationViewer<Integer, String> vv = new  VisualizationViewer<Integer,String>(layout);
//            
//    graph.addEdge("edge de vértice 1 a 2", 1, 2);
//	graph.addEdge("edge de vértice 1 a 3", 1, 3);
//	graph.addEdge("edge de vértice 1 a 20", 1, 20);  // Since is not a (..., 3), the order of node appear is the last position (up-down, left to right) 
//	graph.addEdge("edge de vértice 1 a 4", 1, 4);
//	graph.addEdge("edge de vértice 1 a 31", 1, 31);  // Similar to second addEdge case
//
//
//	vv.getRenderContext().setVertexLabelTransformer(
//                new ToStringLabeller<Integer>());
//
//    return vv;
//    	
//}


//private static JPanel createGraphPanel( Set<Integer> setZero, Set<Integer> firstSet, Set<Integer> secondSet) {
private static JPanel createGraphPanel_c( ) {
      // create a graph
      ////Graph<Integer, String> graph = new DelegateForest<Integer, String>();
      Graph<Integer, String> graph = new DelegateForest<Integer, String>();
    
      
      graph.addEdge("edge de vértice 1 a 2", 1, 2);
      ////graph.addEdge("edge de vértice 1 a 3", 1, 3);
      graph.addEdge("edge de vértice 1 a 20", 1, 55);  // AT SOME ENVIRONMENTS OCCUR: Alphabetical order (so logically a 22 minor than 4)?!! Maybe to work always?-> so put any starting cipher bigger than 4. Since is not a (..., 3), the order of node appear is the last position (up-down, left to right) 
      graph.addEdge("edge de vértice 1 a 4", 1, 4);
      ////graph.addEdge("edge de vértice 1 a 5", 1, 5);
//      graph.addEdge("edge de vértice 1 a 31", 1, 31);  // Similar to second addEdge case      


      Layout<Integer, String> layout = new TreeLayout<Integer, String>((Forest<Integer, String>) graph);
      VisualizationViewer<Integer, String> vv = new  VisualizationViewer<Integer,String>(layout);

      vv.getRenderContext().setVertexLabelTransformer(
              new ToStringLabeller<Integer>());

      return vv;
  }

    
//    private static JPanel createGraphPanel( Set<Integer> setZero, Set<Integer> firstSet, Set<Integer> secondSet) {
    private static JPanel createGraphPanel( ) {
            // create a graph
            Graph<Integer, String> graph = new DelegateForest<Integer, String>();

//                Integer vertex1 = setZero.iterator().next();
            ////graph.addEdge("a1", 1, 2);
                
            
            //graph.addEdge("A", vertex1);
                
//            for (Integer i : firstSet) {
//                ////graph.addEdge(vertex1+"-"+i, vertex1, i);
//            	String part1 =(vertex1+"-"+i);
//            	Integer part2 = vertex1;
//            	Integer part3 =  i;
//            	graph.addEdge(part1, part2, part3);
//            }
            
            graph.addEdge("edge de vértice 1 a 2", 1, 2);
            ////graph.addEdge("edge de vértice 1 a 3", 1, 3);
            graph.addEdge("edge de vértice 1 a 20", 1, 20);  // Since is not a (..., 3), the order of node appear is the last position (up-down, left to right) 
            graph.addEdge("edge de vértice 1 a 4", 1, 4);
            ////graph.addEdge("edge de vértice 1 a 5", 1, 5);
//            graph.addEdge("edge de vértice 1 a 31", 1, 31);  // Similar to second addEdge case
                
//            Set<Integer> set0 = new HashSet<Integer>();
//            //    Set<Integer> set1 = new HashSet<Integer>();
//            //    Set<Integer> set2 = new HashSet<Integer>();
//
//            set0.add(1);                
//            
//            //graph.addEdge(1, set0);
//            graph.addEdge(vertex1+"-"+1, set0);
            

            Layout<Integer, String> layout = new TreeLayout<Integer, String>((Forest<Integer, String>) graph);
            VisualizationViewer<Integer, String> vv = new  VisualizationViewer<Integer,String>(layout);

            vv.getRenderContext().setVertexLabelTransformer(
                    new ToStringLabeller<Integer>());

            return vv;
        }
    }