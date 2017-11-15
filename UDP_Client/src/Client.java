import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

	public static void main (String[] args)
    {
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 8000;
			
			//Data Input
			String input = "";			
			Scanner sc = new Scanner(System.in);
						
			do {
				System.out.print("Eingabe: ");
				input = sc.nextLine();
				DatagramPacket request = new DatagramPacket(input.getBytes(), input.length(), aHost, serverPort);
				aSocket.send(request);
			} while(input != "");
		
		}catch (SocketException e){
			System.out.println("Socket:  " + e.getMessage());
		}catch (IOException e){
			System.out.println("IO: " + e.getMessage()
		);
		}finally {
			if(aSocket != null) aSocket.close();
		}
    }
}