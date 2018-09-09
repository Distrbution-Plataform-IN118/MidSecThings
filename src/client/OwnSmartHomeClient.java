package client;

import java.io.IOException;
import java.net.UnknownHostException;

import model.Item;
import naming.NamingProxy;
import object.ISmartHome;
import util.Port;

public class OwnSmartHomeClient {
	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {

		// Cria uma instancia do Servidor de Nome
		NamingProxy namingService = new NamingProxy("localhost", Port.NAMING_PORT);

		// Cria uma instancia do proxy que referencia o objeto remoto
		ISmartHome smarthome = (ISmartHome) namingService.lookup("Thing");

	
		// ********** <<< LOGIN USER >>> **********
		String token = smarthome.login("nelson", "middleware");
		System.out.println("Token Nelson User = " + token);
		System.out.println("Number Attempts = " + smarthome.numberAttempts(token, "nelson"));
		System.out.println("Accessing Update Item = " + smarthome.atualizaItem(token,"Light_GF_Corridor_Ceiling","OFF"));
		smarthome.logout(token);

	}

}
