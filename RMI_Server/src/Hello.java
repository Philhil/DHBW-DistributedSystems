import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	void printHello() throws RemoteException;
}