package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import naming.NamingInvoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.INamingRequestHandler;

public class ServerRequestHandler implements INamingRequestHandler,Cloneable
{
	private final static Logger logger = LoggerFactory.getLogger(ServerRequestHandler.class);
	private int port;
	private ServerSocket welcomeSocket = null;
	private Socket connectionSocket = null;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
private static Map <Integer, ServerSocket> lookUpMap = new HashMap <>();
	
	public static ServerSocket getSocket (int port) throws IOException {
		
		if(lookUpMap.containsKey(port)) {
			return lookUpMap.get(port);
		}
		
		ServerSocket socket = new ServerSocket(port);
		lookUpMap.put(port, socket);
		return socket;
	}
	
	
	public ServerRequestHandler(int port) throws IOException
	{
		logger.info("Iniciando Servidor na porta " + port);
		this.port = port;
		welcomeSocket = getSocket(port);
		logger.info("Aguardando estabelecer conexao no socket...");
		connectionSocket = welcomeSocket.accept();
		logger.info("Conexao Realizada Socket" );
		logger.info("Acoplando Stream Entrada" );
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
		logger.info("Acoplando Stream Saida" );
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		
	}
	
	public byte [] receive() throws IOException
	{		
		int size = inFromClient.readInt();
		
		byte [] msg = new byte [size];
		inFromClient.read(msg, 0, size);
		
		return msg;
	}
	
	public void send(byte [] msg) throws IOException, InterruptedException
	{	
		
		outToClient.writeInt(msg.length);
		outToClient.write(msg);
		outToClient.flush();
	}
}
