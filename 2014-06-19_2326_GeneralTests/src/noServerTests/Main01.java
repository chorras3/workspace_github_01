package noServerTests;

public class Main01 {
	
	
	public static void main(String[] args){
		System.out.println("main(String[] args)");
		Main01 application  = new Main01();
		application.doSomething();
		
		System.exit(0);
	}

	private void doSomething() {
		System.out.println("doSomething()");
		
	}

}
