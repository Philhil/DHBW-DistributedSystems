import java.rmi.*;
import java.rmi.server.*;

public class Client {
	public static void main(String args[]){
		Hello sampleHello = null;
		try{
			sampleHello = (Hello) Naming.lookup("HelloPrinter");
			
			sampleHello.printHello();
		} catch(RemoteException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("Client:" + e.getMessage());
		}
	}
}
