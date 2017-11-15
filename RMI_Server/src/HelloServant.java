import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class HelloServant extends UnicastRemoteObject implements Hello {

	protected HelloServant() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printHello() throws RemoteException {
		System.out.println("Hallo verteilte Welt");
		
	}

}
