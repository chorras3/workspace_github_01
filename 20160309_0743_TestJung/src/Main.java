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

class Main{
    public static void main(String[] args){

    Set<Integer> set0 = new HashSet<Integer>();
    Set<Integer> set1 = new HashSet<Integer>();
    Set<Integer> set2 = new HashSet<Integer>();

    set0.add(1);

    set1.add(2);
    set1.add(3);
    set1.add(4);
    set1.add(5);

    set2.add(6);
    set2.add(7);

    JFrame frame = new JFrame();
    frame.add(createGraphPanel(set0, set1, set2));
    frame.pack();
    frame.setVisible(true);


    }

    private static JPanel createGraphPanel( Set<Integer> setZero, Set<Integer> firstSet, Set<Integer> secondSet) {
            // create a graph
            Graph<Integer, String> graph = new DelegateForest<Integer, String>();

                Integer vertex1 = setZero.iterator().next();
            for (Integer i : firstSet) {
                graph.addEdge(vertex1+"-"+i, vertex1, i);
            }

            Layout<Integer, String> layout = new TreeLayout<Integer, String>((Forest<Integer, String>) graph);
            VisualizationViewer<Integer, String> vv = new  VisualizationViewer<Integer,String>(layout);

            vv.getRenderContext().setVertexLabelTransformer(
                    new ToStringLabeller<Integer>());

            return vv;
        }
    }