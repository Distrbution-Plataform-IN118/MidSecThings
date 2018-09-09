import java.io.*;
import java.net.*;

public class ClientRequestHandler implements Serializable, Cloneable, INamingRequestHandler
{
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private Socket clientSocket = null;
	private DataOutputStream outToServer = null;
	private DataInputStream inFromServer = null;
	
	public ClientRequestHandler(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	private void init() throws IOException {
		
		System.out.println("Inicializando Socket e Streams no ClienteRequestHandler..");

		this.clientSocket = new Socket(this.host, this.port);
		this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
		this.inFromServer = new DataInputStream(clientSocket.getInputStream());
	}
	
	public void send(byte [] msg) throws IOException
	{	
		if(clientSocket == null || !clientSocket.isConnected()) {
			init();
		}
		
		outToServer.writeInt(msg.length);
		outToServer.write(msg);
		outToServer.flush();
		
		System.out.println("Realizando Serialização no ClienteRequestHandler..");
	}
	
	public byte [] receive() throws IOException, InterruptedException
	{		
		System.out.println("Realizando Deserialização no ClienteRequestHandler");
		int size = inFromServer.readInt();
		byte [] msg = new byte [size];
		inFromServer.read(msg, 0, size);
		
		return msg;
	}
	
	public void close() throws IOException {
		System.out.println("Encerrando Socket e Streams no ClienteRequestHandler..");
		
		outToServer.close();
		inFromServer.close();
		clientSocket.close();
	}
}