import java.net.DatagramPacket;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientMsg {
	
	protected Client() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]){
		
		Message sMessage = null;
		try{
			sMessage = (Message) Naming.lookup("MessagePrinter");
			
			Client cl = new Client();
			sMessage.registerClient("testuser", cl);
			
			//Data Input
			String input = "";			
			Scanner sc = new Scanner(System.in);
						
			do {
				input = sc.nextLine();
				sMessage.addMessage("testuser", input);
			} while(!input.trim().equals("Stopp"));
			
		} catch(RemoteException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("Client:" + e.getMessage());
		}
	}

	@Override
	public void printMsg(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.print(msg);
	}
	
	
}
