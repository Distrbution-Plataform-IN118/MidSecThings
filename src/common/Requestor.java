package common;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import client.ClientRequestHandler;
import performance.Metrics;
import protocol.Message;
import protocol.MessageBody;
import protocol.MessageHeader;
import protocol.RequestBody;
import protocol.RequestHeader;
import util.Crypter;

//Classe responvel pelo envio e recebimento da mensagem
public class Requestor implements Serializable	 {

	private static final long serialVersionUID = 1L;
	private ClientRequestHandler crh;
	private Crypter crypter = null;
	private static boolean isClient=false;
	private  static int counter_unmarshall_performance =0;
	public Requestor(String host, int port)
	{
		System.out.println("Delegando Comunicacao para RequestHandler");
		crh = new ClientRequestHandler(host, port);
	}
	
	public Termination invoke(Invocation inv) throws UnknownHostException, IOException, Throwable
	{
		long startTime = System.nanoTime();
		if (crypter == null) {
			crypter = new Crypter();
			try {
				crypter.init(crh);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Inicializando do protocol de comunicacao
		RequestHeader requestHeader = new RequestHeader("", 0, true, 0, inv.getMethodName());
		RequestBody requestBody = new RequestBody(inv.getParameters());
		MessageHeader messageHeader = new MessageHeader("MIOP", 0, false, 0, 0);
		MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);
		Message msgToBeMarshalled = new Message(messageHeader,messageBody);
		
		//Realiza Serializacao Messagem->bytes
		byte[] msgMarshalled = Marshaller.marshall(msgToBeMarshalled);
		byte[] msgEncripted = crypter.encrypt(msgMarshalled);
		//Envia para pelo stream do cliente
		crh.send(msgEncripted);
		//Aguarda o recebimento do stream do Cliente da mensagem criptografada
		byte[] msgToBeDecripted = crh.receive();
		//Descripta a mensagem
		byte[] msgToBeUnmarshalled = crypter.decrypt(msgToBeDecripted);
		//Realiza Deserializacao de bytes->Message
		Message msgUnmarshalled = Marshaller.unmarshall(msgToBeUnmarshalled);
		//Instancia o resultado recebido
		Termination termination = new Termination();
		termination.setResult(msgUnmarshalled.getBody().getReplyBody().getOperationResult());

		if (crh.isClient()) {
		Metrics.UNMARSHALL_CLIENT_DURATION_LIST[counter_unmarshall_performance] = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
		counter_unmarshall_performance++;
		}
		
		return termination;
	}

	public ClientRequestHandler getCrh() {
		return crh;
	}

	
}
