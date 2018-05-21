/*
 *
 * -References: "JUNG 2.0 Tutorial Or how to achieve graph based nirvana in Java", http://www.grotto-networking.com/JUNG/JUNG2-Tutorial.pdf
 */



package simple01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class SimpleGraphView2 {
	public static void main(String[] args) {
	 SimpleGraphView2 sgv = new SimpleGraphView2(); // This builds the graph
	 // Layout<V, E>, BasicVisualizationServer<V,E>
	 ////Layout<Integer, String> layout = new CircleLayout(sgv.g);
	 Layout<Integer, String> layout = new CircleLayout((Graph) sgv);
	 layout.setSize(new Dimension(300,300));
	 BasicVisualizationServer<Integer,String> vv =
	 new BasicVisualizationServer<Integer,String>(layout);
	 vv.setPreferredSize(new Dimension(350,350));
	 // Setup up a new vertex to paint transformer...
	 Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
	 public Paint transform(Integer i) {
	 return Color.GREEN;
	 }
	 };
	 // Set up a new stroke Transformer for the edges
	 float dash[] = {10.0f};
	 final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
	 BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	 Transformer<String, Stroke> edgeStrokeTransformer =
	 new Transformer<String, Stroke>() {
	 public Stroke transform(String s) {
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
}

