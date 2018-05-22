package mainFileDrop;

public class FileAction {
	protected  String action = "";
	protected  String source = "";
	protected  String destination = "";
	
	public static String ACTION_MOVE = "move";
	public static String ACTION_DELETE = "delete";
	
	public void Fileaction(){}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}


}
