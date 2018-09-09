package naming;

import common.ClientProxy;
//classe que representa Registor de Nome(Servico, referencia proxy client)
public class NamingRecord {
	private String serviceName;
	private ClientProxy clientProxy;
	
	NamingRecord(String serviceName, ClientProxy clientProxy) {
		this.serviceName = serviceName;
		this.clientProxy = clientProxy;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setClientProxy (ClientProxy clientProxy) {
		this.clientProxy = clientProxy;
	}
	
	public ClientProxy getClientProxy() {
		return clientProxy;
	}
}
