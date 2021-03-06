package simple02;

public class Snippet {
	private Embedded createSampleGraph() {
	Embedded imageComponent = null;
	
	try {
	    final DocumentBuilderFactory docBuilderFactory =
	            DocumentBuilderFactory
	                    .newInstance();
	    final DocumentBuilder docBuilder =
	            docBuilderFactory.newDocumentBuilder();
	    final Document document = docBuilder.newDocument();
	    final Element svgelem = document.createElement("svg");
	    document.appendChild(svgelem);
	
	    final SVGGraphics2D graphic2d = new SVGGraphics2D(document);
	
	    final Graph<String, String> graph = createGraph();
	    final VisualizationImageServer<String, String> server =
	            createServer(graph);
	
	    server.printAll(graphic2d);
	
	    final Element el = graphic2d.getRoot();
	    el.setAttributeNS(null, "viewBox", "0 0 350 350");
	    el.setAttributeNS(null, "style", "width:100%;height:100%;");
	
	    final ByteArrayOutputStream bout = new ByteArrayOutputStream();
	
	    final Writer out = new OutputStreamWriter(bout, "UTF-8");
	    graphic2d.stream(el, out);
	
	    final JungResource source = new JungResource(bout);
	
	    TPTApplication.getCurrentApplication().addResource(source);
	
	    imageComponent = new Embedded("", source);
	
	    imageComponent.setWidth(DEFAULT_WIDTH_PIXELS, UNITS_PIXELS);
	    imageComponent.setHeight(DEFAULT_HEIGHT_PIXELS, UNITS_PIXELS);
	    imageComponent.setMimeType("image/svg+xml");
	    addComponent(imageComponent);
	} catch (final UnsupportedEncodingException exception) {
	    LOGGER.error(ErrorCodes.M_001_UNSUPPORTED_ENCONDING, exception);
	} catch (final SVGGraphics2DIOException exception) {
	    LOGGER.error(ErrorCodes.M_002_SVG_GRAPHICS_2D_IO, exception);
	} catch (final ParserConfigurationException exception) {
	    LOGGER.error(ErrorCodes.M_003_PARSER_CONFIGURATION, exception);
	}
	return imageComponent;
	}
	
	private VisualizationImageServer<String, String> createServer(
	    final Graph<String, String> aGraph) {
	final Layout<String, String> layout = new FRLayout<String, String>(
	        aGraph);
	
	layout.setSize(new Dimension(300, 300));
	final VisualizationImageServer<String, String> vv =
	        new VisualizationImageServer<String, String>(
	                layout, new Dimension(350, 350));
	vv.getRenderContext().setVertexLabelTransformer(
	        new ToStringLabeller<String>());
	return vv;
	}
	
	private Graph<String, String> createGraph() {
	final Graph<String, String> graph =
	        new DirectedSparseMultigraph<String, String>();
	final String vertex1 = "IE";
	final String vertex2 = "P1";
	final String vertex3 = "P2";
	final String vertex4 = "P3";
	final String vertex5 = "FE";
	
	graph.addVertex(vertex1);
	graph.addVertex(vertex2);
	graph.addVertex(vertex3);
	graph.addVertex(vertex4);
	graph.addVertex(vertex5);
	
	graph.addEdge("1", vertex1, vertex2, EdgeType.DIRECTED);
	graph.addEdge("2", vertex2, vertex3, EdgeType.DIRECTED);
	graph.addEdge("3", vertex3, vertex5, EdgeType.DIRECTED);
	graph.addEdge("4", vertex1, vertex4, EdgeType.DIRECTED);
	graph.addEdge("5", vertex4, vertex5, EdgeType.DIRECTED);
	return graph;
	}
}

