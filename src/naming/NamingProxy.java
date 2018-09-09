package naming;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import common.ClientProxy;
import common.Invocation;
import common.Requestor;
import common.Termination;
//classe realizado procurador para registro e recebimento do objeto remoto extende cliente proxy e implement os metodos (bind,lookup)
public class NamingProxy extends ClientProxy implements INaming {
	private static final long serialVersionUID = 1L;
	Requestor requestor;
		
	public NamingProxy(String host, int port) {
		
		super(host, port);
		System.out.println("Instanciando NamingProxy...");
	}

	public void bind(String serviceName, ClientProxy clientProxy) throws UnknownHostException, IOException, Throwable {
		class Local {};
		System.out.println("Realizando Bind...");
		requestor = new Requestor(this.host, this.port);
		
		// Informacao enviada para o cliente
		String methodName = Local.class.getEnclosingMethod().getName();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(serviceName);
		parameters.add(clientProxy);
		// Informacao enviado para (Requestor) 
		Invocation inv = new Invocation();
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		// Requestor realiza [invoke]
		requestor.invoke(inv);
	}

	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable {
		System.out.println("Realizando Lookup...");
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {};
		
		requestor = new Requestor(this.host, this.port);
		
		// informaca recebida pelo cliente
		String methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);
		
		// Informacao enviado para (Requestor)
		Invocation inv = new Invocation();
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		// Requestor realiza [invoke]
		Termination ter = requestor.invoke(inv);
		
		//@ Resultado e retornado para Cliente
		return (ClientProxy) ter.getResult();
	}
	
}
