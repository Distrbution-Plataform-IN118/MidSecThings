package naming;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.ClientProxy;
import common.Marshaller;
import common.Termination;
import protocol.Message;
import protocol.MessageBody;
import protocol.MessageHeader;
import protocol.ReplyBody;
import protocol.ReplyHeader;
import server.ServerRequestHandler;
import util.Crypter;
import util.Port;

//Responsaveir pelo instaciacao do servidor de nome
public class NamingInvoker implements Runnable 
{
	private static Logger logger = LoggerFactory.getLogger(NamingInvoker.class);

	public static void main(String[] args) {
		
			try {
				new NamingInvoker().invoke(Port.NAMING_PORT);
				
				/*new Thread(new CalculatorServer()).start();
				
				new Thread(new CalculatorClient()).start();*/
				
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	private ServerRequestHandler srh;
	private NamingImpl namingImpl = new NamingImpl();
	
	public NamingInvoker() {

		logger.info("Instanciando Servidor Nomes Porta 1313");
	}
		
	public NamingInvoker(ServerRequestHandler srh) {
		this.srh = srh;
	}
	
	public void invoke(int port) throws IOException, Throwable{
		logger.info("chamada metodo Invoke()");

		while(true) {

			(new Thread(new NamingInvoker(new ServerRequestHandler(port)))).start();
			
		}	
		
	}

	@Override
	public void run() {
		byte[] rcvBuffer, sndBuffer;
		Message rcvMessage, sndMessage;
		String serviceName;
		ClientProxy clientProxy;
		Termination terminator = new Termination();
		Crypter crypter = new Crypter();
		
		byte[] msgEncripted = null;
		byte[] msgDecripted = null;

		
		try {
			crypter.init(srh);
			while(true)
			{
				try {
					logger.info("Bytes Recebidos Buffer" );
					rcvBuffer = srh.receive();
				} catch (IOException e) {
					break;
				}
				msgDecripted = crypter.decrypt(rcvBuffer);
				rcvMessage = Marshaller.unmarshall(msgDecripted);
				ArrayList<Object> params = rcvMessage.getBody().getRequestBody().getParameters();
				
				switch(rcvMessage.getBody().getRequestHeader().getOperation())
				{
					case "bind":
						logger.info("Opcao Bind" );
						serviceName = params.get(0).toString();
						clientProxy = (ClientProxy) params.get(1);
						
						namingImpl.bind(serviceName, clientProxy);
						terminator.setResult(serviceName);
						
						sndMessage = new Message(
								new MessageHeader("MIOP", 0, false, 0, 0), 
								new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(
										terminator.getResult()))
							);
						
						sndBuffer = Marshaller.marshall(sndMessage);
						msgEncripted = crypter.encrypt(sndBuffer);
						srh.send(msgEncripted);
						
						break;
					
					case "lookup":
						logger.info("Opcao Lookup" );
						serviceName = params.get(0).toString();
						clientProxy = namingImpl.lookup(serviceName);
						
						terminator.setResult(clientProxy);
						
						sndMessage = new Message(
								new MessageHeader("MIOP", 0, false, 0, 0), 
								new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(
										terminator.getResult()))
							);
						
						sndBuffer = Marshaller.marshall(sndMessage);
						msgEncripted = crypter.encrypt(sndBuffer);
						srh.send(msgEncripted);
						break;
						
					case "list":
						terminator.setResult(namingImpl.list());
						
						sndMessage = new Message(
								new MessageHeader("MIOP", 0, false, 0, 0), 
								new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(
										terminator.getResult()))
							);
						
						sndBuffer = Marshaller.marshall(sndMessage);
						
						sndBuffer = Marshaller.marshall(sndMessage);
						msgEncripted = crypter.encrypt(sndBuffer);
						srh.send(msgEncripted);
						break;
						
					default:
						break;
				}
			}
		}catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
