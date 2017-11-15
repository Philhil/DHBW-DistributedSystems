import java.rmi.*;


public class Server {
	 
	 public static void main(String args[]) {
		 try {
			 
			 Hello sampleHello = new HelloServant();
			 
			 Naming.rebind("HelloPrinter", sampleHello);
			 
			 System.out.println("Hello Server gestartet.");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }

}
