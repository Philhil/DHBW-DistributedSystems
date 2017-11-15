import java.net.*;
import java.io.*;

public class Server {

	public static void main (String[] args)
    {
		DatagramSocket aSocket = null;
		try {
			System.out.print("Server gestartet...");
			aSocket = new DatagramSocket(8000);
			byte[] buffer = new byte[1000];
			
			while(true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);

				System.out.println("\nSender: " + request.getAddress() + "\nPort: " + request.getPort());
				System.out.println("Data: " + new String(request.getData()));
				buffer = new byte[1000];
			}
		} catch(SocketException e) {
			System.out.println("Socket:  " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null) 
				aSocket.close();
		}
    
    }
}