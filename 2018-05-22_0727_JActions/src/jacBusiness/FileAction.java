package jacBusiness;

import java.io.File;

public class FileAction {

	protected  File theFile;
	protected  String action = "";
	protected  String source = "";
	protected  String destination = "";
	//-----
	protected File FileA;


	protected File FileB;
	
	public static String ACTION_MOVE = "move";
	public static String ACTION_DELETE = "delete";
	
	public void Fileaction(){}
	
	
	public File getTheFile() {
		return theFile;
	}

	public void setTheFile(File theFile) {
		this.theFile = theFile;
	}	
	
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
	public File getFileA() {
		return FileA;
	}
	public void setFileA(File fileA) {
		FileA = fileA;
	}
	public File getFileB() {
		return FileB;
	}
	public void setFileB(File fileB) {
		FileB = fileB;
	}


}
