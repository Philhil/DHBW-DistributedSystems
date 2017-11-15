import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientMsg extends Remote {
	void printMsg(String msg) throws RemoteException;
}