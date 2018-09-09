package server;

import java.io.IOException;

import naming.NamingProxy;
import object.SmartHomeProxy;
import util.Port;

public class SmartHomeServer {
	public static void main(String[] args) throws IOException, Throwable {
		// obtain instance of Naming Service
		NamingProxy namingService = new NamingProxy("localhost", Port.NAMING_PORT);

		SmartHomeProxy smarthomeImpl = new SmartHomeProxy("localhost", Port.SERVER_PORT);

		// register SmartHomer in Naming service
		namingService.bind("Thing", smarthomeImpl);

		// invoker Invoker
		SmartHomeInvoker invoker = new SmartHomeInvoker();
		invoker.invoke(smarthomeImpl);
	}

}
