import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;


public class NamingRequestor implements Serializable	 {

	private static final long serialVersionUID = 1L;
	private ClientRequestHandler crh;
	
	public NamingRequestor(String host, int port)
	{
		System.out.println("Delegando Comunicacao para ClienteRequestHandler");
		crh = new ClientRequestHandler(host, port);
	}
	
	public Termination invoke(Invocation inv) throws UnknownHostException, IOException, Throwable
	{
		
		//Inicializando do protocol de comunicacao
		RequestHeader requestHeader = new RequestHeader("", 0, true, 0, inv.getMethodName());
		RequestBody requestBody = new RequestBody(inv.getParameters());
		MessageHeader messageHeader = new MessageHeader("MIOP", 0, false, 0, 0);
		MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);
		Message msgToBeMarshalled = new Message(messageHeader,messageBody);
		
		//Realiza Serializacao Messagem->bytes
		byte[] msgMarshalled = Marshaller.marshall(msgToBeMarshalled);
		//Envia para pelo stream do cliente
		crh.send(msgMarshalled);
		//Aguarda o recebimento do stream do Cliente
		byte[] msgToBeUnmarshalled = crh.receive();
		//Realiza Deserializacao de bytes->Message
		Message msgUnmarshalled = Marshaller.unmarshall(msgToBeUnmarshalled);
		//Instancia o resultado recebido
		Termination termination = new Termination();
		termination.setResult(msgUnmarshalled.getBody().getReplyBody().getOperationResult());
		
		return termination;
	}
}
