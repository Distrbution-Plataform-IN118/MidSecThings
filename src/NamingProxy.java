import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
		
		// information received from Client
		String methodName = Local.class.getEnclosingMethod().getName();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(serviceName);
		parameters.add(clientProxy);
		// information sent to Requestor
		Invocation inv = new Invocation();
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		requestor.invoke(inv);
	}

	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable {
		System.out.println("Realizando Lookup...");
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {};
		
		requestor = new Requestor(this.host, this.port);
		
		// information received from Client
		String methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);
		
		// information sent to Requestor
		Invocation inv = new Invocation();
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		// invoke Requestor
		Termination ter = requestor.invoke(inv);
		
		//@ Result sent back to Client
		return (ClientProxy) ter.getResult();
	}
	
}
