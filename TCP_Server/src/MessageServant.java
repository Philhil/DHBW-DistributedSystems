import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;


public class MessageServant extends UnicastRemoteObject implements Message{

	Server ref = null;

	protected MessageServant(Server serverref) throws RemoteException {
		super();
		
		ref = serverref;
	}

	@Override
	public void addMessage(String nickname, String message) throws RemoteException {
		ref.addMessage(nickname, message);
	}

	@Override
	public void registerClient(String username, ClientMsg c) throws RemoteException {
		
		RMIConnection rmic = new RMIConnection(username, c);
		ref.addMessage("Server", "New Connection: " + username);
		ref.addObserver(rmic);
	}
}
