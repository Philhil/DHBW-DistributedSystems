import java.net.*;
import java.util.Observable;
import java.util.Observer;
import java.io.*;

public class Server extends Observable {
	public boolean serverrunning = true;
	private ServerSocket listenSocket = null;
	
	public static void main (String args[]) {
		Server server = new Server();
		
		try{
			int serverPort = 8000; 
			server.listenSocket = new ServerSocket(serverPort);
			while(server.serverrunning) {
				Socket clientSocket = server.listenSocket.accept();
				
				//nickname abfragen
				DataOutputStream out =new DataOutputStream( clientSocket.getOutputStream());
				out.writeBytes("Username:");
				DataInputStream in = new DataInputStream( clientSocket.getInputStream());
				BufferedReader brinp = new BufferedReader(new InputStreamReader(in));
				String username = brinp.readLine();
				
				server.addMessage("Server", "New Connection: " + username +" ("+clientSocket.getPort()+")");
				
				ServerMulti c = new ServerMulti(clientSocket, username, server);
			}
			
		} catch(IOException e) {//System.out.println("Listen  :"+e.getMessage());
		}
		finally {
			server.setChanged();
			server.notifyObservers(".stop");
			
			while(server.countObservers() > 0) {
				System.out.println("observers count: " + server.countObservers());
			}
		}
	}
	
	public void addMessage(String nickname, String message)
	{
		setChanged();
	    notifyObservers(nickname + ": "+ message);
	    
		System.out.println(nickname + ": "+ message);
	}
	
	public void killServer()
	{
		serverrunning = false;
		try {
			listenSocket.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}

class ServerMulti extends Thread implements Observer {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	String user;
	Server serverref;
	BufferedReader brinp = null;
	
	public ServerMulti (Socket aClientSocket, String username, Server serverref) {
		try {
			this.serverref = serverref;
			this.serverref.addObserver(this);
			user = username;
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
	
	public void run(){
		
		brinp = new BufferedReader(new InputStreamReader(in));
		String line;
		while(this.serverref.serverrunning)
		{
			try{
				line = brinp.readLine();
				if((line == null) || line.equalsIgnoreCase(".stop")) {
					this.serverref.killServer();
					return;
				}
				else
				{
					//out.writeBytes(line + "\n");
					this.serverref.addMessage(user, line);
					//out.flush();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		 
	}

	@Override
	public void update(Observable o, Object arg) {
		 try {
			 if(arg.toString().equalsIgnoreCase(".stop")) {
				 out.writeBytes("Server is down\n" );
				 //brinp.close();
				 this.serverref.deleteObserver(this);
				 this.clientSocket.close();				 	 
			 } else {
				 out.writeBytes( arg + "\n" );
				 out.flush();
			 }
		 } catch(IOException e)
		 {
			 //e.printStackTrace(); 
		 }
	}
}