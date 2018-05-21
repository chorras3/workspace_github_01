//package tmp_erasable;
//class Grotto_001 {
//	public static void main(String[] args) {
//		String sClassName = Thread.currentThread().getStackTrace()[1].getClassName();
//		System.out.println("This class name: "+sClassName+".java");
//
//		test_section_03_03();
//		System.out.println("end");
//
//	}
//
//	private static void test_section_03_03() {
//
//		String currentMethod = getMethodName(1);
//		System.out.println("method: "+currentMethod);
//
//		
//		String sClassName2 = Thread.currentThread().getStackTrace()[1].getClassName();
//		System.out.println("sClassName2="+sClassName2);
//
//	}
//
//
//	/**
//	 * Get the method name for a depth in call stack. <br />
//	 * Utility function
//	 * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
//	 * @return method name
//	 */
//
//	/*
//	 * References: "Getting the name of the current executing method", "answered Jan 14 '09 at 12:33" "by VonC	589k17316401723", http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method
//	 */	
//	public static String getMethodName(final int depth)
//	{
//	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
//
//	  //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
//	  // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
//	  return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
//	}	
//
//
//
//} // End of class
