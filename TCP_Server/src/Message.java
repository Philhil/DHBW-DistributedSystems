import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote {
	void addMessage(String nickname, String message) throws RemoteException;
	
	void registerClient(String username, ClientMsg c) throws RemoteException;
}